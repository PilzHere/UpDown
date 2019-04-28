package mysko.pilzhere.updown.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

import mysko.pilzhere.updown.Updown;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "UpDown";
		config.width = 256 * Updown.WINDOW_SCALE; // SNES width
		config.height = 224 * Updown.WINDOW_SCALE; // SNES height
		config.fullscreen = false;
		config.resizable = false;
//		config.addIcon(path, fileType);

		config.initialBackgroundColor = Color.WHITE;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.samples = 0;
		config.vSyncEnabled = false;

		new LwjglApplication(new Updown(), config);
	}
}
