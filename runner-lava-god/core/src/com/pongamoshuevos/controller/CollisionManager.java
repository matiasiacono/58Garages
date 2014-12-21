package com.pongamoshuevos.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.pongamoshuevos.model.Level;
import com.pongamoshuevos.model.Player;
import com.pongamoshuevos.model.Player.State;

public class CollisionManager {
	
	private Player player;
	private Array<Rectangle> tiles;
	private Pool<Rectangle> rectPool;
	private Level level;

	public CollisionManager(Player player, Level level, Pool<Rectangle> rectPool) {
		this.player = player;
		this.rectPool = rectPool;
		this.level = level;
		
		tiles = new Array<Rectangle>();
	}

	public void updateCollisions(float delta) {
		if (delta == 0)
			return;

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {

			player.getVelocity().x = -Player.MAX_VELOCITY;
			if (player.isGrounded()) {
				player.setState(State.Walking);
			}
			player.setFacingRight(false);
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {

			player.getVelocity().x = Player.MAX_VELOCITY;
			if (player.isGrounded()) {
				player.setState(State.Walking);
			}
			player.setFacingRight(true);
		}

		if (player.getState() != State.Falling) {
			if (player.getVelocity().y < 0) {
				player.setState(State.Falling);
				player.setGrounded(false);
			}
		}

		player.getAcceleration().y = Player.GRAVITY;
		player.getAcceleration().scl(delta);
		player.getVelocity().add(player.getAcceleration().x, player.getAcceleration().y);

		// clamp the velocity to the maximum, x-axis only
		if (Math.abs(player.getVelocity().x) > Player.MAX_VELOCITY) {
			player.getVelocity().x = Math.signum(player.getVelocity().x) * Player.MAX_VELOCITY;
		}

		// clamp the velocity to 0 if it's < 1, and set the state to standing
		if (Math.abs(player.getVelocity().x) < 1) {
			player.getVelocity().x = 0;
			if (player.isGrounded()) {
				player.setState(State.Standing);
			}
		}

		player.getVelocity().scl(delta);

		// perform collision detection & response, on each axis, separately
		// if the player is moving right, check the tiles to the right of it's
		// right bounding box edge, otherwise check the ones to the left
		Rectangle playerRect = rectPool.obtain();
		playerRect.set(player.getPosition().x, player.getPosition().y, player.getWidth(),
				player.getHeight());

		int startX, startY, endX, endY;
		if (player.getVelocity().x > 0) {
			startX = endX = (int) (player.getPosition().x + player.getWidth() + player.getVelocity().x);
		} else {
			startX = endX = (int) (player.getPosition().x + player.getVelocity().x);
		}

		startY = (int) (player.getPosition().y);
		endY = (int) (player.getPosition().y + player.getHeight());
		getTiles(startX, startY, endX, endY);

		playerRect.x += player.getVelocity().x;

		for (Rectangle tile : tiles) {

			if (playerRect.overlaps(tile)) {
				player.getVelocity().x = 0;
				break;
			}
		}

		playerRect.set(player.getPosition().x, player.getPosition().y, player.getWidth(),
				player.getHeight());

		// if the player is moving upwards, check the tiles to the top of it's
		// top bounding box edge, otherwise check the ones to the bottom
		if (player.getVelocity().y > 0) {
			startY = endY = (int) (player.getPosition().y + player.getHeight() + player.getVelocity().y);
		} else {
			startY = endY = (int) (player.getPosition().y + player.getVelocity().y);
		}

		startX = (int) (player.getPosition().x);
		endX = (int) (player.getPosition().x + player.getWidth());
		getTiles(startX, startY, endX, endY);
		playerRect.y += player.getVelocity().y;
		for (Rectangle tile : tiles) {
			if (playerRect.overlaps(tile)) {
				// we actually reset the player y-position here
				// so it is just below/above the tile we collided with
				// this removes bouncing :)
				if (player.getVelocity().y > 0) {
					player.getVelocity().y = tile.y - player.getHeight();
				} else {
					player.getPosition().y = tile.y + tile.height;
					// if we hit the ground, mark us as grounded so we can jump
					player.setGrounded(true);
				}
				player.getVelocity().y = 0;
				break;
			}
		}

		rectPool.free(playerRect);

		// unscale the velocity by the inverse delta time and set
		// the latest position
		player.getPosition().add(player.getVelocity());

		player.getVelocity().scl(1 / delta);

		player.getVelocity().x *= Player.DAMPING;

		player.setStateTime(player.getStateTime() + delta);

		player.getPosition().y += player.getVelocity().cpy().scl(delta).y;
	}
	
	private void getTiles(int startX, int startY, int endX, int endY) {
		TiledMapTileLayer layer = (TiledMapTileLayer) level.getMap()
				.getLayers().get(0);
		rectPool.freeAll(tiles);
		tiles.clear();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
}
