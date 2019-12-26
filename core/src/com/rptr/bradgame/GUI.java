package com.rptr.bradgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

import javax.xml.soap.Text;

public class GUI
{
    private Stage stage;
    private Table table;
    private Skin skin;
    private Session currentSession;

    private Group layoutTable;

    void setup ()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        skin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Texture tex = new Texture(Gdx.files.internal("card.png"));
        TextureRegion region = new TextureRegion(tex);
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = new TextureRegionDrawable(region);
        skin.add("default", imageButtonStyle);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        layoutTable = new Group();
        stage.addActor(layoutTable);

        table.setDebug(true);
        layoutTable.setDebug(true);

        final TextButton readyButton = new TextButton("Click when ready", skin);
        table.add(readyButton);

        readyButton.addListener(new ChangeListener() {
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
            readyButton.setText("Ready!");
            boolean set = true;
            currentSession.playerReady(set);
            }
        });

        final TextButton turnButton = new TextButton("Turn done", skin);
        table.add(turnButton);

        turnButton.addListener(new ChangeListener() {
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
            currentSession.passTurn();
            }
        });

//        table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
    }

    void setSession (Session session)
    {
        currentSession = session;
    }

    void updateLayout ()
    {
        layoutTable.clearChildren();

        // WIDGETS
        ArrayList<Widget> layout = currentSession.getGame().getPersonalLayout();
        ArrayList<Piece> pieces = currentSession.getPlayer().getAllPieces();

        for (Widget widget : layout)
        {
            final AccordionGroup hand = new AccordionGroup();
            // stack them
            hand.space(-110);
            hand.setPosition(widget.x, widget.y);

            for (Piece p : pieces) {
                if (!p.getType().getCategory().equals(widget.category))
                    continue;

                final ImageButton butt = new ImageButton(skin);
                hand.addActor(butt);

                butt.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {

                    }
                });
            }

            layoutTable.addActor(hand);
        }

        // XXX temp
        currentSession.guiUpdated = false;
    }

    void draw ()
    {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // XXX temp
        if (currentSession.guiUpdated)
        {
            updateLayout();
        }
    }

    void dispose ()
    {
        stage.dispose();
        skin.dispose();
    }
}
