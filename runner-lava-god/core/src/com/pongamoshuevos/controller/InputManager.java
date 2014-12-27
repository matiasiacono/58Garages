package com.pongamoshuevos.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.pongamoshuevos.model.Player;

public class InputManager implements InputProcessor {
	
	Player player;
	private boolean jumpingPressed;
	private long jumpPressedTime;

	public InputManager(Player player) {
		this.player = player;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.SPACE && player.isGrounded()
				&& player.getState() != Player.State.Falling) {
			if (!player.getState().equals(Player.State.Jumping)) {
				jumpingPressed = true;
				player.setGrounded(false);
				jumpPressedTime = System.currentTimeMillis();
				player.setState(Player.State.Jumping);
				player.getVelocity().y = Player.MAX_JUMP_SPEED;
			} else {
				if ((jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) 
						>= Player.LONG_JUMP_PRESS))) {
					jumpingPressed = false;
				} else {
					if (jumpingPressed) {
						player.getVelocity().y = Player.MAX_JUMP_SPEED;
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.SPACE && player.getState() == Player.State.Jumping) {
			player.getAcceleration().y = Player.GRAVITY;
			player.getAcceleration().scl(Gdx.graphics.getDeltaTime());
			player.getVelocity().add(player.getAcceleration().x,
					player.getAcceleration().y);
			player.setState(Player.State.Falling);
			jumpingPressed = false;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
