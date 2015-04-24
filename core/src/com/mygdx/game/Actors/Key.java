package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.AnimatedSprite;
import com.mygdx.game.JocDeTrons;

/**
 * Clau per desbloquejar el premi
 * Created by Bernabé Borrero on 24/04/15.
 */
public class Key {

    public static final int FRAME_COLS = 12;
    public static final int FRAME_ROWS = 2;

    private World world;
    private Texture animatedTexture;
    private Texture stoppedTexture;
    private Sprite spriteKey;
    private AnimatedSprite spriteAnimatedKey;
    private Body body;

    private Sound soPicked;


    public Key(World world) {
        this.world = world;
        loadTextures();
        loadSounds();
        createKey();
    }

    private void loadTextures() {
        animatedTexture = new Texture(Gdx.files.internal("imatges/keySpriteSheet.png"));
        animatedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        stoppedTexture = new Texture(Gdx.files.internal("imatges/key.png"));
        stoppedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private void loadSounds() {
        // soPicked = Gdx.audio.newSound(Gdx.files.internal("sons/"));
    }

    private void createKey() {
        spriteKey = new Sprite(animatedTexture);
        spriteAnimatedKey = new AnimatedSprite(spriteKey, FRAME_COLS, FRAME_ROWS, stoppedTexture);

        BodyDef defCos = new BodyDef();
        defCos.type = BodyDef.BodyType.StaticBody;
        defCos.position.set(6.0f, 3.0f);

        body = world.createBody(defCos);
        body.setUserData("Key");

        /**
         * Definir les vores de l'sprite
         */
        PolygonShape requadre = new PolygonShape();
        requadre.setAsBox((spriteKey.getWidth() / FRAME_COLS) / (2 * JocDeTrons.PIXELS_PER_METRE),
                (spriteKey.getHeight() / FRAME_ROWS) / (2 * JocDeTrons.PIXELS_PER_METRE));

        /**
         * La densitat i fricció del protagonista. Si es modifiquen aquests
         * valor anirà més ràpid o més lent.
         */
        FixtureDef propietats = new FixtureDef();
        propietats.shape = requadre;
        propietats.density = 1.0f;
        propietats.friction = 3.0f;

        body.setFixedRotation(true);
        body.createFixture(propietats);
        requadre.dispose();
    }

    public void initialize() {
        spriteAnimatedKey.setDirection(AnimatedSprite.Direction.RIGHT);
    }

    public void updatePosition() {
        spriteKey.setPosition(
                JocDeTrons.PIXELS_PER_METRE * body.getPosition().x
                        - spriteKey.getWidth() / FRAME_COLS / 2,
                JocDeTrons.PIXELS_PER_METRE * body.getPosition().y
                        - spriteKey.getHeight() / FRAME_ROWS / 2);
        spriteAnimatedKey.setPosition(spriteKey.getX(), spriteKey.getY());
    }

    public void draw(SpriteBatch batch) {
        spriteAnimatedKey.draw(batch);
    }

    public void dispose() {
        animatedTexture.dispose();
        stoppedTexture.dispose();
        soPicked.dispose();
    }

}
