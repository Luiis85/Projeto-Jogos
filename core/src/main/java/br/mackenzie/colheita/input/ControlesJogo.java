package br.mackenzie.colheita.input;

/** Input boundary reserved for stage 2. This release contains no serial reader. */
public interface ControlesJogo {
    boolean left();
    boolean right();
    String label();
}
