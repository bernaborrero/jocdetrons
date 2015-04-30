package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
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

public class MainMenuScreen extends AbstractScreen {

    private Stage stage = new Stage();
    private Table table = new Table();
    private MainScreen.Protagonista protagonista;
    private Skin skin;

    private TextButton buttonPlayWar,buttonPlayLeo, buttonExit;
    private Label title;
    /**
     * Constructor
     *
     * @param joc Classe principal del joc
     */
    public MainMenuScreen(JocDeTrons joc) {
        super(joc);
        skin = joc.getSkin();
        buttonPlayWar = new TextButton("Play with Warrior", skin);
        buttonPlayLeo = new TextButton("Play with Leonidas", skin);
        buttonExit = new TextButton("Exit", skin);
        buttonPlayWar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                protagonista = MainScreen.Protagonista.WARRIOR;
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen(getGame(), Personatge.MAX_LIVES,protagonista));
            }
        });
        buttonPlayLeo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                protagonista = MainScreen.Protagonista.LEONIDAS;
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen(getGame(), Personatge.MAX_LIVES,protagonista));
            }
        });
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                // or System.exit(0);
            }
        });
        title = new Label(joc.getTitol(),skin);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

        float titlePadding = 40 * Gdx.graphics.getDensity();
        float buttonsPaddingX = 150 * Gdx.graphics.getDensity();
        float buttonsPaddingY = 60 * Gdx.graphics.getDensity();
        float buttonsPaddingB = 20 * Gdx.graphics.getDensity();

        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(title).padBottom(titlePadding).row();

        table.add(buttonPlayWar).size(buttonsPaddingX, buttonsPaddingY).padBottom(buttonsPaddingB).row();
        table.add(buttonPlayLeo).size(buttonsPaddingX, buttonsPaddingY).padBottom(buttonsPaddingB).row();
        table.add(buttonExit).size(buttonsPaddingX, buttonsPaddingY).padBottom(buttonsPaddingB).row();
        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}