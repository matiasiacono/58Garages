package com.pongamoshuevos.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author fmestevez
 *
 */
public class Player {

	public static final float MAX_VELOCITY = 10f;
	public static final float DAMPING = 0.87f;
	public static final float GRAVITY = -22.0f;
	public static final float MAX_JUMP_SPEED = 10f;
	public static final long LONG_JUMP_PRESS = 150l;
	public static final float RUNNING_FRAME_DURATION = 0.09f;

	public enum State {
		Standing, Walking, Jumping, Falling
	}

	private State state;
	private boolean facingRight;
	private boolean grounded;
	private float stateTime;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	private float width;
	private float height;

	/* Textures for Player */
	private TextureRegion playerIdleLeft;
	private TextureRegion playerIdleRight;
	private TextureRegion playerJumpLeft;
	private TextureRegion playerJumpRight;

	private TextureRegion playerFrame;

	/* Animations for Player */
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;

	public Player() {
		position = new Vector2();
		velocity = new Vector2();
		acceleration = new Vector2();
		state = State.Standing;
		facingRight = true;
		stateTime = 0;
		grounded = true;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public boolean isGrounded() {
		return grounded;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public TextureRegion getPlayerIdleLeft() {
		return playerIdleLeft;
	}

	public void setPlayerIdleLeft(TextureRegion playerIdleLeft) {
		this.playerIdleLeft = playerIdleLeft;
	}

	public TextureRegion getPlayerIdleRight() {
		return playerIdleRight;
	}

	public void setPlayerIdleRight(TextureRegion playerIdleRight) {
		this.playerIdleRight = playerIdleRight;
	}

	public TextureRegion getPlayerJumpLeft() {
		return playerJumpLeft;
	}

	public void setPlayerJumpLeft(TextureRegion playerJumpLeft) {
		this.playerJumpLeft = playerJumpLeft;
	}

	public TextureRegion getPlayerJumpRight() {
		return playerJumpRight;
	}

	public void setPlayerJumpRight(TextureRegion playerJumpRight) {
		this.playerJumpRight = playerJumpRight;
	}

	public TextureRegion getPlayerFrame() {
		return playerFrame;
	}

	public void setPlayerFrame(TextureRegion playerFrame) {
		this.playerFrame = playerFrame;
	}

	public Animation getWalkLeftAnimation() {
		return walkLeftAnimation;
	}

	public void setWalkLeftAnimation(Animation walkLeftAnimation) {
		this.walkLeftAnimation = walkLeftAnimation;
	}

	public Animation getWalkRightAnimation() {
		return walkRightAnimation;
	}

	public void setWalkRightAnimation(Animation walkRightAnimation) {
		this.walkRightAnimation = walkRightAnimation;
	}
}
