package com.rptr.bradgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rptr.bradgame.BradGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config =
				new LwjglApplicationConfiguration();
		config.title = "board game simulator";
		config.width = 1200;
		config.height = 700;
		new LwjglApplication(new BradGame(), config);
	}
}
