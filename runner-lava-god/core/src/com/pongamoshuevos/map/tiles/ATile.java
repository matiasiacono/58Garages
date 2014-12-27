package com.pongamoshuevos.map.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pongamoshuevos.map.Tile;
import com.pongamoshuevos.map.TileEffect;

public class ATile extends Tile {
	
	public ATile(Integer x){
		this.height =  2;
		this.x = x;
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		System.out.println("need an imageeee");
		
	}

	@Override
	public TileEffect geteffect() {
		throw new RuntimeException("Soy un tile colisionable");
	}

}
