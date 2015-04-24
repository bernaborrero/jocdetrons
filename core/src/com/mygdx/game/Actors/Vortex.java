package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
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
 * Vortex on es guanya el joc
 * Created by Bernabé Borrero on 24/04/15.
 */
public class Vortex {

    public static final int FRAME_COLS = 4;
    public static final int FRAME_ROWS = 2;

    private World world;
    private Texture animatedTexture;
    private Texture stoppedTexture;
    private Sprite spriteVortex;
    private AnimatedSprite spriteAnimatedVortex;
    private Body body;

    public Vortex(World world) {
        this.world = world;
        loadTextures();
        createKey();
    }

    private void loadTextures() {
        animatedTexture = new Texture(Gdx.files.internal("imatges/vortexSpriteSheet.png"));
        animatedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        stoppedTexture = new Texture(Gdx.files.internal("imatges/vortex.png"));
        stoppedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    private void createKey() {
        spriteVortex = new Sprite(animatedTexture);
        spriteAnimatedVortex = new AnimatedSprite(spriteVortex, FRAME_COLS, FRAME_ROWS, stoppedTexture);

        BodyDef defCos = new BodyDef();
        defCos.type = BodyDef.BodyType.StaticBody;
        defCos.position.set(20.0f, 3.0f);

        body = world.createBody(defCos);
        body.setUserData("Key");

        /**
         * Definir les vores de l'sprite
         */
        PolygonShape requadre = new PolygonShape();
        requadre.setAsBox((spriteVortex.getWidth() / FRAME_COLS) / (2 * JocDeTrons.PIXELS_PER_METRE),
                (spriteVortex.getHeight() / FRAME_ROWS) / (2 * JocDeTrons.PIXELS_PER_METRE));

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
        spriteAnimatedVortex.setDirection(AnimatedSprite.Direction.STOPPED);
    }

    public void updatePosition() {
        spriteVortex.setPosition(
                JocDeTrons.PIXELS_PER_METRE * body.getPosition().x
                        - spriteVortex.getWidth() / FRAME_COLS / 2,
                JocDeTrons.PIXELS_PER_METRE * body.getPosition().y
                        - spriteVortex.getHeight() / FRAME_ROWS / 2);
        spriteAnimatedVortex.setPosition(spriteVortex.getX(), spriteVortex.getY());
    }

    public void draw(SpriteBatch batch) {
        spriteAnimatedVortex.draw(batch);
    }

    public void dispose() {
        animatedTexture.dispose();
        stoppedTexture.dispose();
    }

}
