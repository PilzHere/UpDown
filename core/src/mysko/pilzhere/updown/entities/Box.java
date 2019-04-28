package mysko.pilzhere.updown.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mysko.pilzhere.updown.screens.GameScreen;

public class Box extends Entity {

	private TextureRegion texReg;
	public Sprite sprite;
	private int posX;
	private int posY;
	
	public Box(GameScreen screen, float posX, float posY) {
		super(screen);
		
		texReg = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 16, 0, 16, 16);
		
		sprite = new Sprite(texReg);
		sprite.setX(posX);
		sprite.setY(posY);
		
		rect = new Rectangle();
		rect.width = 16;
		rect.height = 16;
		rect.x = sprite.getX();
		rect.y = sprite.getY();
		
	}
	
	@Override
	public void tick(float delta) {
		super.tick(delta);
		
		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		sprite.draw(screen.game.spriteBatch);
		
		
	}
	

}