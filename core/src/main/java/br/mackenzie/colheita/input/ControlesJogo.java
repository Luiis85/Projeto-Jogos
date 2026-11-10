package br.mackenzie.colheita.input;

/** Fonte de comandos consultada uma vez por quadro. */
public interface ControlesJogo {
    boolean left();
    boolean right();
    String label();
    default void poll() {}
    default boolean connected() { return true; }
}
