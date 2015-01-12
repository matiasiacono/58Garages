package com.pongamoshuevos.controller;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.pongamoshuevos.model.Player;

public class AssetLoader {
	
	private AssetManager manager;

	public AssetLoader() {
		this.manager = new AssetManager();
	}
	
	public void loadAssets() {
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load("level1.tmx", TiledMap.class);
		manager.load("player.pack", TextureAtlas.class);
		
		// TODO: remove this line and use lazy loading.
		manager.finishLoading();
	}

	public void loadPlayerTextures(Player player) {
		TextureAtlas atlas = manager.get("player.pack", 
						TextureAtlas.class);

		/* Standing */
		player.setPlayerIdleLeft(atlas.findRegion("1"));

		player.setPlayerIdleRight(new TextureRegion(player.getPlayerIdleLeft()));
		player.getPlayerIdleRight().flip(true, false);

		TextureRegion[] walkLeftFrames = new TextureRegion[6];
		for (int i = 0; i < 6; i++) {
			walkLeftFrames[i] = atlas.findRegion(((i + 6) + ""));
		}

		player.setWalkLeftAnimation(new Animation(Player.RUNNING_FRAME_DURATION,
				walkLeftFrames));

		TextureRegion[] walkRightFrames = new TextureRegion[6];
		for (int i = 0; i < 6; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}

		player.setWalkRightAnimation(new Animation(Player.RUNNING_FRAME_DURATION,
				walkRightFrames));

		player.setPlayerJumpLeft(atlas.findRegion("3"));
		player.setPlayerJumpRight(new TextureRegion(player.getPlayerJumpLeft()));
		player.getPlayerJumpRight().flip(true, false);

	}

	public AssetManager getManager() {
		return manager;
	}
}
