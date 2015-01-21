package com.pongamoshuevos.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pongamoshuevos.model.Level;
import com.pongamoshuevos.model.Player;
import com.pongamoshuevos.model.Player.State;

public class WorldRenderer extends ScreenAdapter {

	public static final float WORLD_TO_SCREEN = 1 / 32f;

	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Level level;
	private Player player;
	private Batch spriteBatch;
	
	private CollisionManager cm;
	private AssetLoader loader;
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};

	public WorldRenderer() {
		loader = AssetLoader.getInstance();
		
		player = new Player();		
		level = new Level(loader.get("level1.tmx", TiledMap.class));
		
		cm = new CollisionManager(player, level, rectPool);
		
		Gdx.input.setInputProcessor(new InputManager(player));

		player.setPosition(new Vector2(15, 17));
		player.setWidth(WORLD_TO_SCREEN * player.getPlayerIdleRight()
				.getRegionWidth());
		player.setHeight(WORLD_TO_SCREEN * player.getPlayerIdleRight()
				.getRegionHeight());

		renderer = new OrthogonalTiledMapRenderer(level.getMap(), WORLD_TO_SCREEN);
		spriteBatch = renderer.getBatch();

		camera = new OrthographicCamera();
		viewport = new ExtendViewport(32, 24, camera);
	}

	@Override
	public void render(float delta) {
		renderer.setView(camera);
		renderer.render();
		
		camera.position.x = player.getPosition().x;
		camera.update();
		
		spriteBatch.begin();
			drawPlayer(spriteBatch);
		spriteBatch.end();

		cm.updateCollisions(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	public void drawPlayer(Batch spriteBatch) {
		player.setPlayerFrame(player.isFacingRight() ? 
				player.getPlayerIdleRight() : player.getPlayerIdleLeft());

		if (player.getState() == State.Walking) {

			player.setPlayerFrame(player.isFacingRight() ? 
					player.getWalkRightAnimation().getKeyFrame(
					player.getStateTime(), true) : 
					player.getWalkLeftAnimation().getKeyFrame(
					player.getStateTime(), true));

		} else if (player.getState() == State.Jumping) {

			player.setPlayerFrame(player.isFacingRight() ? 
					player.getPlayerJumpRight() : player.getPlayerJumpLeft());

		} else if (player.getState() == State.Falling) {

			player.setPlayerFrame(player.isFacingRight() ? 
					player.getPlayerJumpRight() : player.getPlayerJumpLeft());

		}

		spriteBatch.draw(player.getPlayerFrame(), 
				player.getPosition().x, 
				player.getPosition().y,
				player.getWidth(), player.getHeight());
	}
}