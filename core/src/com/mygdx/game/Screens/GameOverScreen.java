package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.JocDeTrons;
import com.mygdx.game.Actors.Personatge;

/**
 * Screen to display the Game Over message
 * Created by Bernabé Borrero on 23/04/15.
 */
public class GameOverScreen extends AbstractScreen {

    private Stage stage;
    private Table table;

    private Skin skin;
    private Label gameOverLabel;
    private TextButton playAgainButton, exitButton;

    /**
     * Constructor
     *
     * @param joc Classe principal del joc
     */
    public GameOverScreen(JocDeTrons joc) {
        super(joc);

        stage = new Stage();
        table = new Table();

        skin = new Skin(Gdx.files.internal("skins/skin.json"));

        gameOverLabel = new Label("Game Over!", skin, "red");
        playAgainButton = new TextButton("Play Again?", skin);
        exitButton = new TextButton("Exit", skin);

        setUpListeners();
    }

    private void setUpListeners() {
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainScreen(getGame(), Personatge.MAX_LIVES));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        table.add(gameOverLabel).padBottom(40).row();

        table.add(playAgainButton).size(150,60).padBottom(20).row();
        table.add(exitButton).size(150, 60).padBottom(20).row();
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
