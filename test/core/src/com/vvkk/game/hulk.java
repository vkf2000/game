package com.vvkk.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vvkk.game.Screen.PlayScreen;

import java.awt.image.ImageProducer;

public class hulk extends Game {
	public static final float V_WIDTH=1920;
	public static final float V_HEIGHT=1080;
	public static final float PPM=100;
	public SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));


	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
