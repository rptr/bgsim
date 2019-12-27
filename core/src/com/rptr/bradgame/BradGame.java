package com.rptr.bradgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONObject;

import java.util.ArrayList;

public class BradGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;

	GUI gui;

	// TEMP
	Session session;
	
	@Override
	public void create () {
		font = new BitmapFont();
		gui = new GUI();
		gui.setup();

		GameType mars = GameLoader.load("mars2.bge");

		session = new Session(mars);
		gui.setSession(session);

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		// FOOTER
		String footer = session.getGame().getSettingsValue("name") +
				" | " +
				session.getState() +
				" | " +
				"turn: "+Integer.toString(session.getTurn());

		font.draw(batch, footer, 10, 20);

		// STATIC BOARD
		int i = 0;
		for (Piece p : session.pieces)
		{
			String global = p.toString();
			font.draw(batch, global, 10, 700 - i * 20);
			i++;

			String file = p.getTexture();

			if (!file.equals(""))
			{
				Texture tex = Gfx.getTexture(file);
				batch.draw(tex, 0, 0);
			}
		}

		// PLAYER BOARD
		i = 0;
		for (Player p : session.players)
		{
			String global = p.toString();
			font.draw(batch, global, 200, 700 - i * 20);
			i++;

			for (Piece piece : p.pieces)
			{
				if (piece.getType().getType().equals("number"))
				{
					String info = piece.toString();
					font.draw(batch, info, 200, 700 - i * 20);
					i++;

				}
			}

			i ++;
		}

		// TURN
		font.draw(batch, session.getTurnInfo(), 400, 700);

		batch.end();

		gui.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gui.dispose();
	}
}
