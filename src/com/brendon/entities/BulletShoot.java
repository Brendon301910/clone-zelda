package com.brendon.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.brendon.main.Game;

public class BulletShoot extends Entity{
	private double dx;
	private double dy;
	private double spd = 4;
	private int life = 20, curlife = 0;

	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	}
	
	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		curlife++;
		if(curlife == life) {
			Game.bullets.remove(this);
		}
	
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(this.getX(), this.getY(),width, height);
	}
	
}
