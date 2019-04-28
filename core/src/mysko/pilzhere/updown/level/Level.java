package mysko.pilzhere.updown.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import mysko.pilzhere.updown.entities.Box;
import mysko.pilzhere.updown.entities.Stone;
import mysko.pilzhere.updown.entities.Wall;
import mysko.pilzhere.updown.screens.GameScreen;

public class Level {

	private final char stone = '0';
	private final char wall = 'W';
	private final char empty = '#';
	private final char playerSpawn = 'S';
	private final char box = 'B';

	private GameScreen screen;

	private TextureRegion texRegBG;

	public final Vector2 spawnPos = new Vector2();

	public Level(GameScreen screen) {
		this.screen = screen;

		texRegBG = new TextureRegion(screen.assMan.get("tiles01.png", Texture.class), 32, 0, 16, 16);
		
		
		
		FileHandle file = Gdx.files.internal("level01.txt");
		String text = file.readString();
		String lines[] = text.split("\n");

		for (int i = 0; i < lines.length; i++) {
//			System.out.println(lines[i]);
			for (int j = 0; j < lines[i].toCharArray().length; j++) {
				char tempChar = lines[i].charAt(j);

				float x = ((j * 16) - screen.tileSet.getWidth() / 2);
				float y = -(((i * 16) - screen.tileSet.getHeight() / 2) + 16);

				switch (tempChar) {
				case stone:
					screen.entities.add(new Stone(screen, x, y));
					break;
				case playerSpawn:
					spawnPos.set(new Vector2(x, y));
					break;
				case wall:
					screen.entities.add(new Wall(screen, x, y));
					break;
				case box:
					screen.entities.add(new Box(screen, x, y));
					break;
				} 
				
				screen.backgroundTiles.add(new Sprite(texRegBG));
				screen.backgroundTiles.get(screen.backgroundTiles.size() - 1).setX(x);
				screen.backgroundTiles.get(screen.backgroundTiles.size() - 1).setY(y);
			}
		}
	}

}
