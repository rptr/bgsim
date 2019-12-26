package com.rptr.bradgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONObject;

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

		GameType mars = new GameType();
		String data = "{" +
				"name : 'Terraforming Mars'," +
				"minPlayers : 1," +
				"maxPlayers : 5," +
				"startState : 'pregame'," +
				"finalState : 'count score'," +

				"states : [" +
					"{name : 'pregame'," +
					" events : [" +
					"{reward : [{piece : card, amount : 10}]}" +
					"]}," +
					"{name : 'game'}," +
					"{name : 'production'," +
					" events : [" +
					"{reward : [{piece : card, amount : 4}]}" +
					"]}," +
					"{name : 'count score'}" +
				"]," + // states

				"transitions : [" +
					"{'from' : 'pregame', " +
					"'to' : 'game'," +
					"'conditions' : [ALL_READY]}," +
					"{'from' : 'game', " +
					"'to' : 'production'," +
					"'conditions' : [ALL_READY]}," +
					"{'from' : 'production', " +
					"'to' : 'game'," +
					"'conditions' : [ALL_READY]}," +
					"{'from' : 'production', " +
					"'to' : 'count score'," +
					"'conditions' : [ALL_READY, GAME_OVER]}" +
				"]," + // transitions

				"board : [" +
					"{id : 'ocean stack', type : 'ocean stack', value : 9}," +
					"{id : temperature, type : temperature, value : -28}," +
					"{id : oxygen, type : oxygen, value : 0}," +

					"{id : tiles}," +
					"{id : deck}," +
					"{id : discard}" +
				"]," + // board

				"playerSetup : [" +
					"{type : 'rating', value : 20}," +
					"{type : 'money'}," +
					"{type : 'steel'}," +
					"{type : 'titanium'}," +
					"{type : 'plant'}," +
					"{type : 'energy'}," +
					"{type : 'heat'}" +
				"]," + // player

				"privateLayout : [" +
				"" +
				"]" +

				"" +
				"}";
		mars.parseSettings(data);


		mars.parsePieceType("{id : 'ocean', category : 'tile'}");
		mars.parsePieceType("{id : 'city', category : 'tile'}");
		mars.parsePieceType("{id : 'forest', category : 'tile'}");
		mars.parsePieceType("{id : 'dust', category : 'tile'}");

		mars.parsePieceType("{id : 'ocean stack', type: number}");
		mars.parsePieceType("{id : 'oxygen', type : number}");
		mars.parsePieceType("{id : 'temperature', type : number}");

		mars.parsePieceType("{id : 'rating', type : number}");
		mars.parsePieceType("{id : 'money', type : number}");
		mars.parsePieceType("{id : 'steel', type : number}");
		mars.parsePieceType("{id : 'titanium', type : number}");
		mars.parsePieceType("{id : 'plant', type : number}");
		mars.parsePieceType("{id : 'energy', type : number}");
		mars.parsePieceType("{id : 'heat', type : number}");

		mars.parsePieceType("{id : 'score guy', file : 'gem.png'}");
		mars.parsePieceType("{id : 'player marker', file : 'gem.png'}");

		mars.parsePieceType("{category : 'project cards'," +
				"cost : [{money : 7}]," +
				"name : 'SF Memorial'," +
				"reward : [{piece : card, amount : 1}]}");
		mars.parsePieceType("{category : 'prelude cards'," +
				"name : 'Allied Banks'," +
				"reward : [{piece : money, amount : 3}]}");
		mars.parsePieceType("{category : 'corporation cards', " +
				"name : 'Beginner Corporation'," +
				"reward : [{piece : money, amount : 42}]}");

		session = new Session(mars);
		gui.setSession(session);

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		// game info
		String footer = session.getGame().getSettingsValue("name") +
				" | " +
				session.getState() +
				" | " +
				"turn: "+Integer.toString(session.getTurn());

		font.draw(batch, footer, 10, 20);

		// game state

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

		// Player state
		i = 0;
		for (Player p : session.players)
		{
			String global = p.toString();
			font.draw(batch, global, 200, 700 - i * 20);
			i++;

			for (Piece piece : p.pieces)
			{
				String info = piece.toString();
				font.draw(batch, info, 200, 700 - i * 20);
				i++;
			}

			i ++;
		}

		// Turn
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
