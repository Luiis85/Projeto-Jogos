package br.mackenzie.colheita;

import br.mackenzie.colheita.input.ControlesJogo;
import br.mackenzie.colheita.input.ControlesTeclado;
import br.mackenzie.colheita.io.ExportadorCsv;
import br.mackenzie.colheita.model.ModeloJogo;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/** Desktop presentation. Every input here is explicitly a development control. */
public final class JogoColheita extends ApplicationAdapter {
    private enum Page { MENU, HELP, GAME }
    private record Button(Rectangle bounds, String label, Runnable action, boolean primary) { }
    private static final Color INK = color("183C32"), MUTED = color("60796B"), CREAM = color("FAF8EE"),
        PAPER = color("FFFDF7"), GREEN = color("237452"), LIME = color("DCEBBF"), ORANGE = color("E9A04E");
    private ShapeRenderer shapes;
    private SpriteBatch batch;
    private BitmapFont font;
    private FitViewport viewport;
    private final GlyphLayout layout = new GlyphLayout();
    private final ControlesJogo controls = new ControlesTeclado();
    private ModeloJogo model = new ModeloJogo();
    private final List<Button> buttons = new ArrayList<>();
    private Page page = Page.MENU;
    private int focus;
    private float clock;
    private String exportMessage = "", savedPath = "";
    private boolean exported;
    private final String captureFolder;
    private int captureStep, captureFrames;
    private final Vector2 mouse = new Vector2();

    public JogoColheita() { this(null); }
    public JogoColheita(String captureFolder) { this.captureFolder = captureFolder; }
    private static Color color(String hex) { return Color.valueOf(hex); }
    @Override public void create() {
        shapes = new ShapeRenderer(); batch = new SpriteBatch(); font = new BitmapFont();
        font.getRegion().getTexture().setFilter(com.badlogic.gdx.graphics.Texture.TextureFilter.Linear,
            com.badlogic.gdx.graphics.Texture.TextureFilter.Linear);
        viewport = new FitViewport(960, 600); viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
    @Override public void resize(int width, int height) { viewport.update(width, height, true); }
    @Override public void render() {
        float dt = Gdx.graphics.getDeltaTime(); clock += Math.min(dt, .1f);
        if (page == Page.GAME) model.update(dt, controls.left(), controls.right());
        ScreenUtils.clear(CREAM);
        viewport.apply();
        shapes.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        mouse.set(Gdx.input.getX(), Gdx.input.getY()); viewport.unproject(mouse);
        buttons.clear();
        if (page == Page.MENU) drawMenu();
        else if (page == Page.HELP) drawHelp();
        else switch (model.state()) {
            case PLAYING -> drawPlay();
            case PAUSED -> drawPause();
            case PHASE_COMPLETE -> drawSummary(false);
            case FINISHED -> drawSummary(true);
            default -> drawMenu();
        }
        handleInput();
        if (captureFolder != null) captureSequence();
    }
    private void header(String title, String detail) {
        rect(0, 530, 960, 70, PAPER);
        text("CM", 30, 573, 1.5f, GREEN);
        text(title, 89, 576, 1.2f, INK);
        text(detail, 89, 553, .82f, MUTED);
        rect(0, 529, 960, 1, color("E1E7D6"));
    }
    private void footer(String hint) {
        text(controls.label(), 28, 23, .72f, MUTED);
        rightText(hint, 932, 23, .72f, MUTED);
    }
    private void drawMenu() {
        header("COLHEITA EM MOVIMENTO", "Um pomar, tres desafios e uma colheita de cada vez.");
        rect(0, 58, 960, 471, CREAM);
        text("BEM-VINDO AO POMAR", 48, 477, .85f, GREEN);
        text("Cada movimento", 48, 432, 2.45f, INK);
        text("faz a colheita crescer.", 48, 390, 2.0f, INK);
        text("Mova a cesta, pegue as frutas e acompanhe", 48, 334, 1.05f, MUTED);
        text("seu desempenho ao longo das tres fases.", 48, 310, 1.05f, MUTED);
        button(48, 218, 260, 53, "Comecar a colher", this::start, true);
        button(48, 151, 260, 49, "Como jogar", () -> changePage(Page.HELP), false);
        button(48, 84, 260, 49, "Sair", () -> Gdx.app.exit(), false);
        rect(550, 115, 360, 362, color("E6EED8"));
        circle(804, 405, 40, color("F5D58B"));
        tree(635, 281, 1.12f); tree(854, 285, .8f);
        ellipse(560, 127, 340, 110, color("C8DCA7"));
        character(690, 176, 1.25f, (float) Math.sin(clock * 2) * 2);
        fruit(628, 395, 19, 0); fruit(848, 338, 17, 1); fruit(813, 251, 15, 2);
        text("01  APRENDA", 574, 146, .8f, GREEN);
        text("02  PRATIQUE   03  AVANCE", 574, 126, .73f, GREEN);
        footer("Setas / Tab: escolher  |  Enter: confirmar");
    }
    private void drawHelp() {
        header("COMO JOGAR", "Versao digital de teste. Nenhuma placa precisa estar conectada.");
        rect(32, 113, 435, 385, PAPER); rect(487, 113, 441, 385, PAPER);
        text("01  MOVA E COLETE", 56, 469, 1.2f, GREEN);
        text("Seta esquerda / A: ir para a esquerda.", 56, 428, 1f, INK);
        text("Seta direita / D: ir para a direita.", 56, 399, 1f, INK);
        text("Soltou a tecla? O personagem para.", 56, 370, 1f, INK);
        text("As duas direcoes juntas: fica parado.", 56, 341, 1f, INK);
        text("Cada fruta na cesta vale 10 pontos.", 56, 295, 1f, MUTED);
        text("Na fase 3, evite pedras: -15 pontos.", 56, 266, 1f, MUTED);
        text("P ou Esc: pausar. R: reiniciar na pausa.", 56, 218, .95f, INK);
        text("As fases duram 45, 50 e 55 segundos.", 56, 188, .95f, INK);
        text("Nao ha vidas: pratique ate o final.", 56, 158, .95f, INK);
        text("02  RESPONDA A SETA", 511, 469, 1.2f, GREEN);
        text("Quando surgir ESQUERDA ou DIREITA,", 511, 428, 1f, INK);
        text("solte e aperte a direcao indicada.", 511, 399, 1f, INK);
        text("Voce tem 3 segundos para responder.", 511, 370, 1f, INK);
        text("Acerto: de 10 a 30 pontos de bonus,", 511, 324, 1f, MUTED);
        text("conforme o tempo da resposta.", 511, 295, 1f, MUTED);
        text("Erro ou tempo esgotado: sem bonus.", 511, 266, 1f, MUTED);
        text("Os resultados sao dados do jogo.", 511, 218, 1f, INK);
        text("Nao demonstram recuperacao clinica.", 511, 188, 1f, INK);
        button(32, 47, 240, 46, "Voltar ao menu", () -> changePage(Page.MENU), false);
        button(688, 47, 240, 46, "Vamos jogar", this::start, true);
        footer("Tab / setas: escolher  |  Enter: confirmar");
    }
    private void scenery() {
        rect(0, 0, 960, 529, color("EAF2E1"));
        circle(851, 424, 35, color("F3D28D"));
        cloud(115, 420); cloud(465, 389); cloud(748, 463);
        ellipse(-150, 85, 800, 260, color("D5E5BF"));
        ellipse(425, 80, 760, 245, color("C6DBAC"));
        tree(52, 145, .94f); tree(902, 151, 1.16f);
        rect(0, 49, 960, 40, color("A8C77D"));
        rect(0, 0, 960, 49, color("D9C19B"));
        for (int i = 0; i < 16; i++) rect(i * 65 + 11, 61, 19, 3, color("8FAE67"));
    }
    private void drawPlay() {
        scenery();
        for (ModeloJogo.FallingItem item : model.items()) {
            if (item.hazard) stone(item.x, item.y, item.radius);
            else fruit(item.x, item.y, item.radius, ((int) item.x) % 3);
        }
        float bob = controls.left() != controls.right() ? (float) Math.sin(clock * 15) * 2 : 0;
        character(model.playerX(), ModeloJogo.PLAYER_Y, 1, bob);
        header("FASE " + (model.levelIndex() + 1) + " / 3  -  " + model.level().name(), model.level().subtitle());
        rect(560, 546, 155, 39, LIME);
        centerText(model.totalScore() + " PONTOS", 637, 572, 1.1f, GREEN);
        centerText(String.format(Locale.ROOT, "%02d s", (int) Math.ceil(model.remaining())), 770, 573, 1.6f, INK);
        button(843, 544, 91, 42, "Pausar", () -> { model.pause(); focus = 0; }, false);
        rect(0, 522, 960, 6, color("DBE5CF"));
        rect(0, 522, 960 * (1 - model.remaining() / model.level().duration()), 6, GREEN);
        if (model.cueDirection() != 0) {
            rect(264, 441, 432, 65, PAPER);
            String label = model.cueDirection() == -1 ? "<  APERTE ESQUERDA" : "APERTE DIREITA  >";
            centerText(label, 480, 488, 1.2f, GREEN);
            centerText(String.format(Locale.ROOT, "Solte e aperte  |  %.1f s", model.cueRemaining()), 480, 461, .85f, MUTED);
            rect(272, 443, 416 * model.cueRemaining() / ModeloJogo.RESPONSE_LIMIT, 3, ORANGE);
        }
        if (!model.feedback().isEmpty()) {
            rect(238, 182, 484, 35, PAPER);
            centerText(model.feedback(), 480, 207, .97f, GREEN);
        }
        ModeloJogo.Stats s = model.current();
        text("COLETADAS  " + s.collected + "     PERDIDAS  " + s.missed, 26, 31, .88f, INK);
        rightText("A / D ou setas: mover   |   P: pausa", 934, 31, .88f, INK);
        text("TECLADO DE TESTE", 28, 504, .7f, MUTED);
    }
    private void drawPause() {
        scenery();
        character(model.playerX(), ModeloJogo.PLAYER_Y, 1, 0);
        rect(228, 100, 504, 407, PAPER);
        centerText("Uma pausa no pomar", 480, 467, 1.95f, INK);
        centerText("O tempo e os objetos estao parados.", 480, 428, 1f, MUTED);
        centerText("Fase " + (model.levelIndex() + 1) + "  /  " + model.totalScore() + " pontos", 480, 393, 1f, GREEN);
        button(304, 302, 352, 52, "Continuar", () -> { model.resume(); focus = 0; }, true);
        button(304, 235, 352, 49, "Reiniciar partida", this::start, false);
        button(304, 168, 352, 49, "Voltar ao menu", () -> changePage(Page.MENU), false);
        footer("P / Esc: continuar   |   R: reiniciar");
    }
    private void drawSummary(boolean finished) {
        header(finished ? "COLHEITA CONCLUIDA" : "FASE CONCLUIDA", finished
            ? "Voce passou pelos tres desafios do pomar." : "Confira a colheita antes de seguir para a proxima fase.");
        text(finished ? "Seu movimento virou colheita." : "Bom trabalho. Vamos continuar?", 32, 493, 1.8f, INK);
        rect(32, 387, 896, 73, GREEN);
        text("PONTUACAO TOTAL", 52, 435, .9f, color("DCEBBF"));
        text(Integer.toString(model.totalScore()), 52, 414, 1.55f, Color.WHITE);
        text("Frutas + respostas aos comandos - pedras", 282, 432, 1f, Color.WHITE);
        text("Indicadores simulados pelo teclado nesta etapa", 282, 409, .88f, color("DCEBBF"));
        String[] labels = { "FASE", "COLETADAS", "PERDIDAS", "COLETA", "RESPOSTA", "PONTOS" };
        float[] xs = { 48, 245, 389, 529, 672, 835 };
        rect(32, 339, 896, 32, color("DFE8D3"));
        for (int i = 0; i < labels.length; i++) text(labels[i], xs[i], 361, .8f, GREEN);
        int count = finished ? 3 : model.levelIndex() + 1;
        for (int i = 0; i < count; i++) {
            ModeloJogo.Stats s = model.stats().get(i); float y = 328 - i * 44;
            rect(32, y - 32, 896, 42, i % 2 == 0 ? PAPER : color("F0F1E6"));
            text((i + 1) + "  " + shortName(i), xs[0], y - 4, .95f, INK);
            text(Integer.toString(s.collected), xs[1], y - 4, 1f, INK);
            text(Integer.toString(s.missed), xs[2], y - 4, 1f, INK);
            text(percent(s.accuracy()), xs[3], y - 4, 1f, INK);
            text(seconds(s.meanResponse()), xs[4], y - 4, 1f, INK);
            text(Integer.toString(s.score()), xs[5], y - 4, 1f, GREEN);
        }
        ModeloJogo.Stats s = model.current();
        text("Nesta fase: " + s.presses + " acionamentos  |  " + s.correct + "/" + s.cues
            + " comandos corretos  |  " + s.collisions + " pedras", 48, 180, .94f, MUTED);
        text("Sem dados: --. Resultados do jogo, sem avaliacao de recuperacao clinica.", 48, 151, .85f, MUTED);
        if (finished) {
            button(32, 77, 278, 49, "Jogar novamente", this::start, true);
            button(330, 77, 278, 49, exported ? "Exportar outra copia" : "Exportar resultados CSV", this::export, false);
            button(628, 77, 300, 49, "Voltar ao menu", () -> changePage(Page.MENU), false);
            text(exportMessage.isEmpty() ? "Exporte para comparar partidas. Modo registrado: teclado_teste." : exportMessage,
                32, 54, .77f, MUTED);
        } else {
            button(32, 77, 360, 49, "Ir para a fase " + (model.levelIndex() + 2), () -> { model.nextLevel(); focus = 0; }, true);
            button(628, 77, 300, 49, "Voltar ao menu", () -> changePage(Page.MENU), false);
        }
        footer("Enter: confirmar   |   Tab / setas: escolher");
    }
    private String shortName(int i) { return new String[] { "Tranquilo", "Atenta", "Desafio" }[i]; }
    private String percent(double n) { return Double.isNaN(n) ? "--" : String.format(Locale.ROOT, "%.1f%%", n); }
    private String seconds(double n) { return Double.isNaN(n) ? "--" : String.format(Locale.ROOT, "%.2f s", n); }
    private void export() {
        try {
            Path dir = Path.of(System.getProperty("user.home"), "ColheitaEmMovimento", "resultados");
            Path saved = ExportadorCsv.save(model, dir);
            exported = true; savedPath = saved.toString();
            exportMessage = "CSV salvo em: " + dir;
            Gdx.app.log("Resultados", savedPath);
        } catch (Exception e) {
            exportMessage = "Nao foi possivel salvar o CSV. Verifique a permissao da pasta pessoal.";
            Gdx.app.error("Resultados", "Falha ao salvar", e);
        }
    }
    private void start() {
        model.start(); page = Page.GAME; focus = 0;
        exportMessage = ""; savedPath = ""; exported = false;
    }
    private void changePage(Page value) { page = value; focus = 0; }
    private void handleInput() {
        boolean playing = page == Page.GAME && model.state() == ModeloJogo.State.PLAYING;
        boolean paused = page == Page.GAME && model.state() == ModeloJogo.State.PAUSED;
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (playing) { model.pause(); focus = 0; return; }
            if (paused) { model.resume(); focus = 0; return; }
            if (page == Page.HELP) { changePage(Page.MENU); return; }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R) && paused) { start(); return; }
        if (!playing && !buttons.isEmpty()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.TAB) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) focus = (focus + 1) % buttons.size();
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
                focus = (focus - 1 + buttons.size()) % buttons.size();
            focus = Math.min(focus, buttons.size() - 1);
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                buttons.get(focus).action.run(); return;
            }
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            for (Button b : buttons) if (b.bounds.contains(mouse)) { b.action.run(); break; }
        }
    }
    @Override public void pause() { model.pause(); }
    @Override public void dispose() { shapes.dispose(); batch.dispose(); font.dispose(); }

    // Drawn in code: original art, without external image or font downloads.
    private void character(float x, float y, float scale, float bob) {
        float cx = x + 43 * scale;
        ellipse(x + 2 * scale, y - 15 * scale, 82 * scale, 17 * scale, color("93AE72"));
        rect(cx - 20 * scale, y - 3 * scale + bob, 15 * scale, 25 * scale, color("294A43"));
        rect(cx + 7 * scale, y - 3 * scale - bob, 15 * scale, 25 * scale, color("294A43"));
        ellipse(cx - 32 * scale, y + 4 * scale, 64 * scale, 66 * scale, GREEN);
        circle(cx, y + 67 * scale, 24 * scale, color("E4AE7F"));
        circle(cx - 8 * scale, y + 70 * scale, 2 * scale, INK);
        circle(cx + 8 * scale, y + 70 * scale, 2 * scale, INK);
        rect(cx - 5 * scale, y + 58 * scale, 10 * scale, 2 * scale, color("A86952"));
        ellipse(cx - 28 * scale, y + 80 * scale, 56 * scale, 29 * scale, ORANGE);
        rect(cx - 37 * scale, y + 81 * scale, 74 * scale, 7 * scale, color("D08C3C"));
        circle(x + 4 * scale, y + 30 * scale, 9 * scale, color("E4AE7F"));
        circle(x + 82 * scale, y + 30 * scale, 9 * scale, color("E4AE7F"));
        rect(x, y, 86 * scale, 31 * scale, color("B97742"));
        rect(x - 3 * scale, y + 27 * scale, 92 * scale, 7 * scale, color("D49A61"));
        for (int i = 0; i < 5; i++) rect(x + (10 + i * 16) * scale, y + 4 * scale, 3 * scale, 20 * scale, color("D49A61"));
    }
    private void fruit(float x, float y, float r, int kind) {
        Color c = kind == 0 ? color("D9664D") : kind == 1 ? ORANGE : color("96B94A");
        circle(x, y, r, c); circle(x - r * .3f, y + r * .25f, r * .24f,
            kind == 0 ? color("EB9275") : color("F2CD7A"));
        rect(x - 1.5f, y + r - 3, 3, 10, color("76583C"));
        ellipse(x + 1, y + r + 2, r * .6f, r * .24f, GREEN);
    }
    private void stone(float x, float y, float r) {
        ellipse(x - r, y - r * .8f, r * 2, r * 1.6f, color("7B8583"));
        ellipse(x - r * .65f, y, r, r * .5f, color("A4ADAA"));
    }
    private void tree(float x, float y, float s) {
        rect(x - 8 * s, y, 16 * s, 125 * s, color("AF8054"));
        circle(x, y + 133 * s, 58 * s, color("8EAF6B"));
        circle(x - 27 * s, y + 101 * s, 44 * s, color("9DBE79"));
        circle(x + 30 * s, y + 105 * s, 45 * s, color("9DBE79"));
    }
    private void cloud(float x, float y) {
        ellipse(x, y, 110, 28, color("F8FAF0"));
        circle(x + 38, y + 21, 23, color("F8FAF0"));
        circle(x + 66, y + 23, 29, color("F8FAF0"));
    }
    private void button(float x, float y, float w, float h, String label, Runnable action, boolean primary) {
        int index = buttons.size(); Rectangle bounds = new Rectangle(x, y, w, h);
        boolean selected = index == focus, hover = bounds.contains(mouse);
        if (selected || hover) rect(x - 3, y - 3, w + 6, h + 6, ORANGE);
        rect(x, y, w, h, primary ? GREEN : PAPER);
        if (!primary) { rect(x, y, w, 1, color("CDD9C3")); rect(x, y + h - 1, w, 1, color("CDD9C3")); }
        centerText(label, x + w / 2, y + h / 2 + 7, w < 110 ? .85f : 1.03f, primary ? Color.WHITE : INK);
        buttons.add(new Button(bounds, label, action, primary));
    }
    private void rect(float x, float y, float w, float h, Color c) {
        shapes.begin(ShapeRenderer.ShapeType.Filled); shapes.setColor(c); shapes.rect(x, y, w, h); shapes.end();
    }
    private void circle(float x, float y, float r, Color c) {
        shapes.begin(ShapeRenderer.ShapeType.Filled); shapes.setColor(c); shapes.circle(x, y, r, 40); shapes.end();
    }
    private void ellipse(float x, float y, float w, float h, Color c) {
        shapes.begin(ShapeRenderer.ShapeType.Filled); shapes.setColor(c); shapes.ellipse(x, y, w, h, 48); shapes.end();
    }
    private void text(String s, float x, float y, float size, Color c) {
        batch.begin(); font.getData().setScale(size); font.setColor(c); font.draw(batch, s, x, y); batch.end();
    }
    private void centerText(String s, float x, float y, float size, Color c) {
        font.getData().setScale(size); layout.setText(font, s); text(s, x - layout.width / 2, y, size, c);
    }
    private void rightText(String s, float x, float y, float size, Color c) {
        font.getData().setScale(size); layout.setText(font, s); text(s, x - layout.width, y, size, c);
    }

    /** Reproducible screenshots of the actual renderer; never included as patient data. */
    private void captureSequence() {
        if (++captureFrames < 4) return;
        captureFrames = 0;
        String[] names = { "01-menu", "02-como-jogar", "03-fase-1", "04-pausa", "05-fase-2", "06-fase-3", "07-resultados" };
        Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        FileHandle target = Gdx.files.absolute(captureFolder).child(names[captureStep] + ".png");
        target.parent().mkdirs(); PixmapIO.writePNG(target, pixmap, -1, true); pixmap.dispose();
        captureStep++;
        switch (captureStep) {
            case 1 -> page = Page.HELP;
            case 2 -> { model = new ModeloJogo(new Random(42)); start(); advance(8); }
            case 3 -> model.pause();
            case 4 -> { model.resume(); advance(60); model.nextLevel(); advance(8); }
            case 5 -> { advance(60); model.nextLevel(); advance(8); }
            case 6 -> advance(60);
            default -> Gdx.app.exit();
        }
    }
    private void advance(int seconds) {
        for (int i = 0; i < seconds * 60 && model.state() == ModeloJogo.State.PLAYING; i++) {
            boolean movingLeft = (i / 120) % 2 == 0;
            model.update(1f / 60, movingLeft, !movingLeft);
        }
    }
}
