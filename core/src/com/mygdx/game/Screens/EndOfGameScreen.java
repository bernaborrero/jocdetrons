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
import com.mygdx.game.Actors.Personatge;
import com.mygdx.game.JocDeTrons;

/**
 * Screen to display the victory message
 * Created by Bernabé Borrero on 30/04/15.
 */
public class EndOfGameScreen extends AbstractScreen {

    private Stage stage;
    private Table table;

    private Skin skin;
    private Label gameOverLabel, creditsLabel;
    private TextButton playAgainButton, exitButton;
    private MainScreen.Protagonista protagonista;
    private boolean victory;

    /**
     * Constructor
     *
     * @param joc Classe principal del joc
     */
    public EndOfGameScreen(JocDeTrons joc,MainScreen.Protagonista protagonista, boolean victory) {
        super(joc);
        this.protagonista = protagonista;
        this.victory = victory;
        stage = new Stage();
        table = new Table();

        skin = joc.getSkin();

        String message = (victory) ? "Congratulations!" : "Game Over!";
        gameOverLabel = new Label(message, skin, "red");
        creditsLabel = new Label("Fet per l Aitor i en Berna", skin, "groc");
        playAgainButton = new TextButton("Play Again?", skin);
        exitButton = new TextButton("Exit", skin);

        setUpListeners();
    }

    private void setUpListeners() {
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MainScreen(getGame(), Personatge.MAX_LIVES,protagonista));
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

        float titlePadding = 40 * Gdx.graphics.getDensity();
        float buttonsPaddingX = 150 * Gdx.graphics.getDensity();
        float buttonsPaddingY = 60 * Gdx.graphics.getDensity();
        float buttonsPaddingB = 20 * Gdx.graphics.getDensity();

        table.add(gameOverLabel).padBottom(titlePadding).row();

        table.add(playAgainButton).size(buttonsPaddingX, buttonsPaddingY).padBottom(buttonsPaddingB).row();
        table.add(exitButton).size(buttonsPaddingX, buttonsPaddingY).padBottom(buttonsPaddingB).row();

        table.add(creditsLabel).padBottom(10).row();
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

}
