package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Actors.Personatge;

import java.util.ArrayList;
/**
 * Classe que implementa la interface de gesti� de contactes
 * 
 * @author Marc
 *
 */
public class GestorContactes implements ContactListener {

    private Personatge personatge;

	// de moment, no implementat
	private ArrayList<Body> bodyDestroyList;

	public GestorContactes(Personatge personatge) {
		this.personatge = personatge;
	}
	
	public GestorContactes(ArrayList<Body> bodyDestroyList) {
		this.bodyDestroyList = bodyDestroyList;
	}
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		Gdx.app.log("beginContact", "entre " + fixtureA.getBody().getUserData() + " i "
				+ fixtureB.getBody().getUserData());

		if (fixtureA.getBody().getUserData() == null
				|| fixtureB.getBody().getUserData() == null) {
			return;
		}

        if (fixtureA.getBody().getUserData().equals("Personatge") && fixtureB.getBody().getUserData().equals("death")
                || fixtureA.getBody().getUserData().equals("death") && fixtureB.getBody().getUserData().equals("Personatge")) {
            personatge.setLives(personatge.getLives() - 1);
        }

		if (fixtureA.getBody().getUserData().equals("stark")
				&& fixtureB.getBody().getUserData().equals("primerObjecte")
				|| fixtureA.getBody().getUserData().equals("primerObjecte")
				&& fixtureB.getBody().getUserData().equals("stark")) {
			Gdx.app.log("HIT", "stark ha topat amb el primer objecte");
			/*
			 * Afegir cos a destruir
			 * 
			 * if(!fixtureA.getBody().getUserData().equals("stark")) {
				bodyDestroyList.add(fixtureA.getBody());
			} else {
				bodyDestroyList.add(fixtureB.getBody());
			}*/
		}


	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
