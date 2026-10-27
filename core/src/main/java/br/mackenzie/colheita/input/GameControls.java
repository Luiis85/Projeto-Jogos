package br.mackenzie.colheita.input;

/** Input boundary reserved for stage 2. This release contains no serial reader. */
public interface GameControls {
    boolean left();
    boolean right();
    String label();
}
