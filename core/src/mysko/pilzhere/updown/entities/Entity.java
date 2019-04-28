package mysko.pilzhere.updown.entities;

import com.badlogic.gdx.math.Rectangle;

import mysko.pilzhere.updown.screens.GameScreen;

public class Entity {
	protected GameScreen screen;
	
	public Rectangle rect = null;
	
	public boolean remove = false;
	
	public Entity(GameScreen screen) {
		this.screen = screen;
	}
	
	public void handleInput(float delta) {
		
	}
	
	public void tick(float delta) {
		
	}
	
	public void render(float delta) {

	}
	
	public void remove() {

	}
}
