package mysko.pilzhere.updown.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import mysko.pilzhere.updown.entities.Entity;
import mysko.pilzhere.updown.entities.Stone;
import mysko.pilzhere.updown.entities.Wall;
import mysko.pilzhere.updown.screens.GameScreen;

public class Player extends Entity {
	private TextureRegion texRegIdleDown;
	private TextureRegion texRegWalkDown1;
	private TextureRegion texRegWalkDown2;
	
	private TextureRegion texRegIdleUp;
	private TextureRegion texRegWalkUp1;
	private TextureRegion texRegWalkUp2;
	
	private TextureRegion texRegIdleRight;
	private TextureRegion texRegWalkRight1;
	private TextureRegion texRegWalkRight2;
	
	private Animation<TextureRegion> animWalkDown;
	private TextureRegion[] walkDownRegs;
	
	private Animation<TextureRegion> animWalkUp;
	private TextureRegion[] walkUpRegs;
	
	private Animation<TextureRegion> animWalkRight;
	private TextureRegion[] walkRightRegs;
	
	private TextureRegion currentFrame;
	
	public Sprite sprite;

	public Rectangle rect;

	public int posX = 0;
	public int posY = 0;
	private int newPosX = 0; // next move X
	private int newPosY = 0; // next move Y
	
	private boolean moveObjectUp = false;
	
	public Player(GameScreen screen, Vector2 spawnPos) {
		super(screen);

		this.posX = (int) spawnPos.x;
		this.posY = (int) spawnPos.y;
		
		rect = new Rectangle();
		rect.width = 8;
		rect.height = 8;
		rect.x = posX;
		rect.y = posY;
	
		texRegIdleDown = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 0, 16, 16, 16);
		texRegWalkDown1 = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 16, 16, 16, 16);
		texRegWalkDown2 = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 32, 16, 16, 16);
	
		texRegIdleUp = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 0, 32, 16, 16);
		texRegWalkUp1 = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 16, 32, 16, 16);
		texRegWalkUp2 = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 32, 32, 16, 16);
		
		texRegIdleRight = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 0, 48, 16, 16);
		texRegWalkRight1 = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 16, 48, 16, 16);
		texRegWalkRight2 = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 32, 48, 16, 16);
		
		walkDownRegs = new TextureRegion[4];
		walkDownRegs[0] = texRegWalkDown1;
		walkDownRegs[1] = texRegIdleDown;
		walkDownRegs[2] = texRegWalkDown2;
		walkDownRegs[3] = texRegIdleDown;
		
		animWalkDown = new Animation<TextureRegion>(0.1f, walkDownRegs);
		
		walkUpRegs = new TextureRegion[4];
		walkUpRegs[0] = texRegWalkUp1;
		walkUpRegs[1] = texRegIdleUp;
		walkUpRegs[2] = texRegWalkUp2;
		walkUpRegs[3] = texRegIdleUp;
		
		animWalkUp = new Animation<TextureRegion>(0.1f, walkUpRegs);
		
		walkRightRegs = new TextureRegion[4];
		walkRightRegs[0] = texRegWalkRight1;
		walkRightRegs[1] = texRegIdleRight;
		walkRightRegs[2] = texRegWalkRight2;
		walkRightRegs[3] = texRegIdleRight;
		
		animWalkRight = new Animation<TextureRegion>(0.1f, walkRightRegs);
		
		currentFrame = texRegIdleDown;
		
		sprite = new Sprite(currentFrame);
		sprite.setX(rect.getX() - sprite.getRegionWidth() / 4);
		sprite.setY(rect.getY());

		newPosX = posX;
		newPosY = posY;
	}

	private float stateTime = 0f;
	
	private boolean spriteFlipX = false;
	
	@Override
	public void handleInput(float delta) {
//		super.handleInput(delta);

		if (Gdx.input.isKeyPressed(Keys.W)) {
			newPosY++;
			
			moveObjectUp = true;
			
			currentFrame = animWalkUp.getKeyFrame(stateTime, true);
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			newPosY--;
			
			moveObjectUp = false;
			
			currentFrame = animWalkDown.getKeyFrame(stateTime, true);
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			newPosX--;
		
			currentFrame = animWalkRight.getKeyFrame(stateTime, true);
			spriteFlipX = true;
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			newPosX++;
				
			currentFrame = animWalkRight.getKeyFrame(stateTime, true);
			spriteFlipX = false;
			
		}
		
		

//		if (Gdx.input.isKeyJustPressed(Keys.R)) {
//			remove = true;
//		}
	}

	boolean overlapX = false;
	boolean overlapY = false;
	
//	boolean isGrounded = false;
	
//	int velY;
//	int velYJump = 60;
	
	@Override
	public void tick(float delta) {		
		stateTime += delta;
		
		rect.x = newPosX;

		for (Entity entity : screen.entities) {
			if (entity.rect != null) {
				if (rect.overlaps(entity.rect)) {
					
					if (entity instanceof Stone) {
//						System.out.println("this is a stone");
						if (spriteFlipX)
							((Stone) entity).rect.setX(newPosX - (rect.width * 2));
						else
							((Stone) entity).rect.setX(newPosX + rect.width);
					} else {
						overlapX = true;
					}
					
//					overlapX = true;
				}
			}
		}

		if (overlapX) {
			newPosX = posX;
		} else {
			posX = newPosX;
		}

		rect.x = posX;
		sprite.setX(rect.x - sprite.getRegionWidth() / 4);
		overlapX = false;

//		if (!isGrounded)
//			newPosY = newPosY - 1;
		
		rect.y = newPosY;

		for (Entity entity : screen.entities) {
			if (entity.rect != null) {
				if (rect.overlaps(entity.rect)) {
					if (entity instanceof Stone) {
//						System.out.println("this is a stone");
						if (moveObjectUp)
							((Stone) entity).rect.setY(newPosY + rect.height);
						else
							((Stone) entity).rect.setY(newPosY - (rect.height * 2));
					} else {
						overlapY = true;
					}
					
					
//					overlapY = true;
				}
			}
		}

		if (overlapY) {
//			isGrounded = true;
			newPosY = posY;
		} else {
//			isGrounded = false;
			posY = newPosY;
		}
		
		

		rect.y = posY;
		sprite.setY(rect.y);
		overlapY = false;
	}

	@Override
	public void render(float delta) {
//		super.render(delta);
		
		sprite.setRegion(currentFrame);
		sprite.setFlip(spriteFlipX, false);
		
		sprite.draw(screen.game.spriteBatch);

	}

	@Override
	public void remove() {
//		super.remove();

//		playerTex.dispose();
	}
}
