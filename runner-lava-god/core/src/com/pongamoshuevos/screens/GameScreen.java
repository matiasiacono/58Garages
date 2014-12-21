package com.pongamoshuevos.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.pongamoshuevos.controller.WorldRenderer;

public class GameScreen extends ScreenAdapter {
	
	private WorldRenderer renderer;
	
	public GameScreen() {
		this.renderer = new WorldRenderer();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 1f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}
}
