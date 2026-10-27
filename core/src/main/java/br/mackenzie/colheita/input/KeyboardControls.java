package br.mackenzie.colheita.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public final class KeyboardControls implements GameControls {
    public boolean left() { return Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A); }
    public boolean right() { return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D); }
    public String label() { return "TECLADO DE TESTE  /  ARDUINO NA ETAPA 2"; }
}
