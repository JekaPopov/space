package ru.dravn.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Space extends ApplicationAdapter {

	private SpriteBatch batch;
	private Background background;
	private Hero hero;

	static boolean isAndroid = false;
	static int ScreenHeight = 720;
	static int ScreenWidth = 1280;


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		hero =new Hero();
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();

		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		background.render(batch);
		hero.render(batch);
		BulletEmitter.getInstance().render(batch);

		batch.end();
	}

	private void update(float dt) {

		background.update(hero, dt);
		hero.update(dt);
		BulletEmitter.getInstance().update(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
