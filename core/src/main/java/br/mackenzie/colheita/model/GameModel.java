package br.mackenzie.colheita.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Deterministic game rules. No graphics, keyboard or hardware dependencies. */
public final class GameModel {
    public static final float WIDTH = 960, HEIGHT = 600;
    public static final float PLAYER_Y = 90, PLAYER_W = 86, PLAYER_H = 34;
    public static final float PLAYER_SPEED = 340, RESPONSE_LIMIT = 3;
    public enum State { READY, PLAYING, PAUSED, PHASE_COMPLETE, FINISHED }
    public record Level(String name, String subtitle, float duration, float dropSpeed,
                        float spawnInterval, float fruitRadius, float hazardChance) { }
    public static final List<Level> LEVELS = List.of(
        new Level("Pomar tranquilo", "Frutas grandes e tempo para aprender", 45, 88, 1.20f, 25, 0),
        new Level("Colheita atenta", "Frutas menores e um ritmo mais vivo", 50, 118, 1.00f, 20, 0),
        new Level("Desafio do pomar", "Colete frutas e desvie das pedras", 55, 148, .88f, 18, .24f));

    public static final class FallingItem {
        public final float x, radius;
        public final boolean hazard;
        public float y;
        public FallingItem(float x, float y, float radius, boolean hazard) {
            this.x = x; this.y = y; this.radius = radius; this.hazard = hazard;
        }
    }
    public static final class Stats {
        public int collected, missed, collisions, presses, cues, correct, wrong, timedOut;
        public int fruitPoints, responsePoints, penalty;
        public double responseSeconds, elapsed;
        public int score() { return Math.max(0, fruitPoints + responsePoints - penalty); }
        public int evaluatedFruit() { return collected + missed; }
        public double accuracy() { return evaluatedFruit() == 0 ? Double.NaN : 100.0 * collected / evaluatedFruit(); }
        public double meanResponse() { return correct == 0 ? Double.NaN : responseSeconds / correct; }
    }
    private final Random random;
    private final List<FallingItem> items = new ArrayList<>();
    private final List<Stats> stats = new ArrayList<>();
    private State state = State.READY;
    private int levelIndex, cueDirection;
    private float playerX, spawnTimer, cueCooldown, cueElapsed;
    private boolean previousLeft, previousRight;
    private String feedback = "";
    private float feedbackTime;

    public GameModel() { this(new Random()); }
    public GameModel(Random random) { this.random = random; }
    public void start() {
        stats.clear();
        for (int i = 0; i < LEVELS.size(); i++) stats.add(new Stats());
        levelIndex = 0; beginLevel();
    }
    private void beginLevel() {
        state = State.PLAYING; playerX = (WIDTH - PLAYER_W) / 2;
        items.clear(); spawnTimer = .5f; cueCooldown = 2;
        cueDirection = 0; cueElapsed = 0;
        previousLeft = previousRight = false; feedback = ""; feedbackTime = 0;
    }
    public void nextLevel() {
        if (state != State.PHASE_COMPLETE) return;
        levelIndex++; beginLevel();
    }
    public void pause() { if (state == State.PLAYING) state = State.PAUSED; }
    public void resume() { if (state == State.PAUSED) state = State.PLAYING; }
    public State state() { return state; }
    public int levelIndex() { return levelIndex; }
    public Level level() { return LEVELS.get(levelIndex); }
    public Stats current() { return stats.get(levelIndex); }
    public List<Stats> stats() { return Collections.unmodifiableList(stats); }
    public List<FallingItem> items() { return Collections.unmodifiableList(items); }
    public float playerX() { return playerX; }
    public int cueDirection() { return cueDirection; }
    public float cueRemaining() { return cueDirection == 0 ? 0 : Math.max(0, RESPONSE_LIMIT - cueElapsed); }
    public float remaining() { return Math.max(0, level().duration - (float) current().elapsed); }
    public String feedback() { return feedbackTime > 0 ? feedback : ""; }
    public int totalScore() { return stats.stream().mapToInt(Stats::score).sum(); }

    /** Feed held states on every frame, including while paused; only rising edges count. */
    public void update(float dt, boolean left, boolean right) {
        boolean leftEdge = left && !previousLeft, rightEdge = right && !previousRight;
        previousLeft = left; previousRight = right;
        if (state != State.PLAYING || !Float.isFinite(dt) || dt <= 0) return;
        // A delayed render must not teleport objects through the basket.
        float remainingDelta = Math.min(dt, .25f);
        Stats s = current();
        if (leftEdge) s.presses++;
        if (rightEdge) s.presses++;
        if (cueDirection != 0 && left != right && (leftEdge || rightEdge)) {
            if ((leftEdge && cueDirection == -1) || (rightEdge && cueDirection == 1)) {
                int bonus = 10 + Math.round(20 * Math.max(0, 1 - cueElapsed / RESPONSE_LIMIT));
                s.correct++; s.responseSeconds += cueElapsed; s.responsePoints += bonus;
                showFeedback("Resposta certa! +" + bonus);
            } else { s.wrong++; showFeedback("Observe a seta no proximo comando"); }
            cueDirection = 0; cueCooldown = 4.5f;
        }
        while (remainingDelta > .00001f && state == State.PLAYING) {
            float step = Math.min(remainingDelta, 1f / 120f);
            step = Math.min(step, (float) Math.max(0, level().duration - s.elapsed));
            if (step <= .000001f) { finishLevel(); break; }
            simulate(step, left, right);
            remainingDelta -= step;
        }
    }
    private void simulate(float dt, boolean left, boolean right) {
        Stats s = current(); s.elapsed += dt; feedbackTime -= dt;
        int direction = left == right ? 0 : left ? -1 : 1;
        playerX = Math.max(0, Math.min(WIDTH - PLAYER_W, playerX + direction * PLAYER_SPEED * dt));
        if (cueDirection == 0) {
            cueCooldown -= dt;
            if (cueCooldown <= 0) { cueDirection = random.nextBoolean() ? 1 : -1; cueElapsed = 0; s.cues++; }
        } else {
            cueElapsed += dt;
            if (cueElapsed >= RESPONSE_LIMIT) {
                s.timedOut++; cueDirection = 0; cueCooldown = 4.5f;
                showFeedback("Tudo bem. Espere o proximo comando");
            }
        }
        spawnTimer -= dt;
        if (spawnTimer <= 0) {
            float r = level().fruitRadius;
            items.add(new FallingItem(35 + random.nextFloat() * (WIDTH - 70), HEIGHT - 132,
                r, random.nextFloat() < level().hazardChance));
            spawnTimer += level().spawnInterval;
        }
        for (int i = items.size() - 1; i >= 0; i--) {
            FallingItem item = items.get(i); item.y -= level().dropSpeed * dt;
            if (overlapsBasket(item)) {
                if (item.hazard) { s.collisions++; s.penalty += 15; showFeedback("Pedra na cesta: -15"); }
                else { s.collected++; s.fruitPoints += 10; }
                items.remove(i);
            } else if (item.y + item.radius < PLAYER_Y) {
                if (!item.hazard) s.missed++;
                items.remove(i);
            }
        }
        if (s.elapsed >= level().duration - .00001) finishLevel();
    }
    private boolean overlapsBasket(FallingItem item) {
        float nearX = Math.max(playerX, Math.min(item.x, playerX + PLAYER_W));
        float nearY = Math.max(PLAYER_Y, Math.min(item.y, PLAYER_Y + PLAYER_H));
        return (item.x - nearX) * (item.x - nearX) + (item.y - nearY) * (item.y - nearY) <= item.radius * item.radius;
    }
    private void finishLevel() {
        current().elapsed = level().duration;
        state = levelIndex == LEVELS.size() - 1 ? State.FINISHED : State.PHASE_COMPLETE;
        items.clear(); cueDirection = 0;
    }
    private void showFeedback(String value) { feedback = value; feedbackTime = 2; }
}
