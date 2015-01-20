package com.pongamoshuevos.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/***
 * Asset loader
 *
 * Usage: Declare and preload every asset needed in the game.
 * In the game's loading and/or before each game's screen,
 * make calls of update() in the main loop. It
 * will return true when the assets are done loading.
 * You can check the progress with getProgress().
 * Get the loaded resources with get().
 *
 * TODO: We should try to modularize loading per screen.
 *
 * @author fmestevez
 */
public class AssetLoader extends AssetManager{

	/***
	 * Singleton
	 */
	private static AssetLoader instance;

	/***
	 * Declaration of every asset path/filename
	 */

	// Textures
	private final String playerTex = "player.pack";

	// Maps
	private final String level1 = "level1.tmx";

	// Sounds

	// Fonts

	// Skins

	// Particles

	// Shaders


	/***
	 * Private constructor for Singleton
	 */
	private AssetLoader() {
		setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		enqueueAssets();
	}

	/***
	 * Preloads every asset
	 */
	private void enqueueAssets() {
		load(level1, TiledMap.class);
		load(playerTex, TextureAtlas.class);
	}

	/***
	 * Get Singleton's instance
	 * @return AssetLoader instance
	 */
	public static AssetLoader getInstance() {
		if(instance == null)
			instance = new AssetLoader();
		return instance;
	}
}
