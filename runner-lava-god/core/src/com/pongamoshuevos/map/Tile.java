package com.pongamoshuevos.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {

	protected Integer x;
	protected Integer height;
	
	protected Boolean hasToApplyEffect(Integer x, Integer y){
		return this.x.equals(x) && this.height >= y;
	}
	
	public abstract void draw(SpriteBatch spriteBatch);
	
	public abstract TileEffect geteffect();
	
}
