package br.mackenzie.colheita.desktop;

import br.mackenzie.colheita.JogoColheita;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public final class InicializadorDesktop {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Colheita em Movimento | Arduino");
        config.setWindowedMode(1152, 720);
        config.setWindowSizeLimits(768, 480, -1, -1);
        config.useVsync(true); config.setForegroundFPS(60);
        config.disableAudio(true);
        String capture = args.length == 2 && args[0].equals("--capture") ? args[1] : null;
        String porta = args.length == 2 && args[0].equals("--porta") ? args[1] : "COM3";
        if (capture != null) {
            new Lwjgl3Application(new JogoColheita(capture), config);
        } else {
            try (var controles = new ControlesArduino(porta)) {
                new Lwjgl3Application(new JogoColheita(controles), config);
            }
        }
    }
}
