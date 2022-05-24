package com.brendon.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.brendon.main.Game;
import com.brendon.world.Camera;
import com.brendon.world.Node;
import com.brendon.world.Vector2i;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(7*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(7*16, 16, 16, 16);
	public static BufferedImage ENEMY_FEEDBACK = Game.spritesheet.getSprite(144, 16, 16, 16);
	public static BufferedImage GUN_RIGHT = Game.spritesheet.getSprite(128, 0, 16, 16);
	public static BufferedImage GUN_LEFT = Game.spritesheet.getSprite(128+16, 0, 16, 16);

	protected List<Node> path;
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	protected BufferedImage sprite;
	
	public int maskx, masky, mwidth, mheight;
	
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
		
		
	}
	
	
	public void setX(int newX) {
		this.x = newX;
	}
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	
	
	public void tick() {
		
	}
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
				//xprev = x;
				//yprev = y;
				if(x < target.x * 16) {
					x++;
				}else if(x > target.x * 16) {
					x--;
				}
				
				if(y < target.y * 16) {
					y++;
				}else if(y > target.y * 16) {
					y--;
				}
				
				if(x == target.x * 16 && y == target.y * 16) {
					path.remove(path.size() - 1);
				}
			}
		}
	}
	
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, mwidth, mheight);
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if( e == this) 
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX()+ maskx, e.getY()+masky, mwidth, mheight);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheight);
		Rectangle e2mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheight);
		
		return e1mask.intersects(e2mask);
	}
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX()- Camera.x, this.getY()- Camera.y, null);
		
	}
}	
