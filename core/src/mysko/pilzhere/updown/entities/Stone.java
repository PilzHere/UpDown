package mysko.pilzhere.updown.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mysko.pilzhere.updown.screens.GameScreen;

public class Stone extends Entity {

	private TextureRegion texReg;
	public Sprite sprite;
	private int posX;
	private int posY;

	public Stone(GameScreen screen, float posX, float posY) {
		super(screen);

		texReg = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 0, 0, 16, 16);

		sprite = new Sprite(texReg);
		sprite.setX(posX);
		sprite.setY(posY);

		rect = new Rectangle();
		rect.width = 16;
		rect.height = 16;
		rect.x = sprite.getX();
		rect.y = sprite.getY();

	}

	public boolean stuck = false;

	@Override
	public void tick(float delta) {
		super.tick(delta);

		stuck = false;

		for (Entity entity : screen.entities) {
			if (entity.rect != null) {
				if (entity != this) {
					if (rect.overlaps(entity.rect)) {

						if (entity instanceof Stone || entity instanceof Wall || entity instanceof Box) {
							stuck = true;
							System.err.println("stone stuck");
						}

//					overlapX = true;
					}
				}
			}
		}

		sprite.setX(rect.getX());
		sprite.setY(rect.getY());
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		sprite.draw(screen.game.spriteBatch);

	}

}
