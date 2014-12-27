package com.pongamoshuevos.map;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tilemap {
	/*
	 * Class variables
	 */
	public static Integer MAXTILES = 30;
	/*
	 * Instance Variables
	 */
	private SpriteBatch spriteBatch;
	private LinkedList<Tile> tiles;
	private Boolean visible;
	/*
	 * Constructor
	 */
	public Tilemap(SpriteBatch spriteBatch){
		this.spriteBatch = spriteBatch;
		this.tiles = new LinkedList<Tile>();
		this.visible = true;
		for(int i = 0; i < MAXTILES; i++){
			this.tiles.add(TileGenerator.generateNext(tiles));
		}
	}
	
	/*
	 * Private Instance Methods
	 */
	private void addNewTile(){
		removeLastTile();
		Tile newTile = TileGenerator.generateNext(tiles);
		this.tiles.add(newTile);
	}
	
	private void removeLastTile(){
		this.tiles.removeFirst();
	}
	/*
	 * Public instance methods
	 */
	public void setVisible(Boolean visible){
		this.visible = visible;
	}
	
	public void update(){
		addNewTile();
	}
	
	public void draw(){
		if(!visible) return;
		for(Tile tile : this.tiles){
			tile.draw(spriteBatch);
		}
	}
	
	public LinkedList<Tile> getTiles(){
		return this.tiles;
	}
	
	public Boolean visible(){
		return this.visible;
	}
	
	public Tile isOnTile(Integer x){
		if (x > MAXTILES){
			throw new RuntimeException("Esta fuera del rango de tiles existentes");
		}
		return this.tiles.get(x);
	}
}
