package mysko.pilzhere.updown.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

import mysko.pilzhere.updown.Updown;
import mysko.pilzhere.updown.entities.Entity;
import mysko.pilzhere.updown.entities.player.Player;
import mysko.pilzhere.updown.level.Level;

public class GameScreen implements Screen {
	public final Updown game;

	private Camera cam;

	private Player player;

	public List<Entity> entities = new ArrayList<Entity>();
	public List<Sprite> backgroundTiles = new ArrayList<Sprite>();

	public AssetManager assMan = new AssetManager();

	public Sprite tileSet;

	Level level;

	public GameScreen(Updown game) {
		this.game = game;

		assMan.load("tiles01.png", Texture.class);
//		assMan.load("rock01.png", Texture.class);
//		assMan.load("player.png", Texture.class);
		assMan.finishLoading();

		tileSet = new Sprite(assMan.get("tiles01.png", Texture.class));
		tileSet.setPosition(0 - tileSet.getWidth() / 2, 0 - tileSet.getHeight() / 2);

		level = new Level(this);

		player = new Player(this, level.spawnPos);
		
//		System.out.println(level.spawnPos);
//		System.out.println(player.posX);
//		System.out.println(player.posY);

		cam = new OrthographicCamera(Gdx.graphics.getWidth() / Updown.WINDOW_SCALE,
				Gdx.graphics.getHeight() / Updown.WINDOW_SCALE);
		cam.near = 0.001f;
		cam.far = 100f;
		cam.position.set(new Vector3(0, 0, 1));
		cam.update();
	}

	@Override
	public void show() {

	}

	private void handleInput(float delta) {
		player.handleInput(delta);
	}

	private void removeEntities() {
		for (int i = 0; i < entities.size();) {
			Entity entity = entities.get(i);
			if (entity.remove) {
				entity.remove(); // 0

				int lastEntity = entities.size() - 1;

				entities.set(i, entities.get(lastEntity));
				entities.remove(lastEntity);
//				totalEntities--;
			} else {
				i++;
			}
		}
	}

	private void tick(float delta) {
		cam.update();

		for (Entity entity : entities) {
			entity.tick(delta);
		}

		player.tick(delta);

		removeEntities();
	}

	@Override
	public void render(float delta) {
		handleInput(delta);
		tick(delta);

		Gdx.gl.glClearColor(0, 0.25f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.spriteBatch.setProjectionMatrix(cam.combined);
		game.spriteBatch.begin();

		tileSet.draw(game.spriteBatch);
		
		for (Sprite sprite : backgroundTiles) {
			sprite.draw(game.spriteBatch);
		}

//		for (Sprite sprite : level.sprites) {
//			sprite.draw(game.spriteBatch);
//		}

		for (Entity entity : entities) {
			entity.render(delta);
		}

		player.render(delta);

		game.spriteBatch.end();

//		---------------------------

//		game.shapeRenderer.setProjectionMatrix(cam.combined);
//		game.shapeRenderer.begin(ShapeType.Line);
//		game.shapeRenderer.setColor(Color.WHITE);
//		for (Entity entity : entities) {
//			if (entity.rect != null) {
//				game.shapeRenderer.rect(entity.rect.x, entity.rect.y, entity.rect.width,
//						entity.rect.height);
//				;
//			}
//
//		}
//
//		game.shapeRenderer.end();
//		
//		game.shapeRenderer.setProjectionMatrix(cam.combined);
//		game.shapeRenderer.begin(ShapeType.Line);
//		game.shapeRenderer.setColor(Color.BLUE);
//		game.shapeRenderer.rect(player.rect.x, player.rect.y, player.rect.width, player.rect.height);
//		
//
//		game.shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
//		cam.resize(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		assMan.dispose();
	}

}
