package com.pongamoshuevos;

import com.badlogic.gdx.Game;
import com.pongamoshuevos.screens.GameScreen;

public class RunnerLavaGodMain extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
