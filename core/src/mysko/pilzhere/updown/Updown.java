package mysko.pilzhere.updown;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import mysko.pilzhere.updown.screens.GameScreen;

public class Updown extends Game {
	public SpriteBatch spriteBatch;
	public ShapeRenderer shapeRenderer;
	
	public static final int WINDOW_SCALE = 4;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		setScreen(new GameScreen(this));
	}

	@Override
	public void render() {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		shapeRenderer.dispose();
	}
}
