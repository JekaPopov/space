package ru.dravn.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {

    class Star {
        Vector2 position;
        Vector2 velocity;
        float scl;

        Star()
        {
            this.position = new Vector2((float) Math.random() * 1280, (float) Math.random() * 720);
            this.velocity = new Vector2((float) (Math.random() - 0.5) * 5.0f, (float) (Math.random() - 0.5) * 5.0f);
            scl = 0.5f + (float) Math.random() / 4.0f;
        }

        void update(Hero hero, float dt)
        {
            position.mulAdd(velocity, dt);
            position.mulAdd(hero.velocity, -0.001f);
            float half = textureStar.getWidth() * scl;
            if (position.x < -half) position.x = 1280 + half;
            if (position.x > 1280 + half) position.x = -half;
        }
    }



    private Texture background, textureStar;
    private Star[] stars;


    Background()
    {
        this.background = new Texture ("background.png");
        this.textureStar = new Texture("star.png");
        stars = new Star[250];
        for (int i = 0; i <stars.length ; i++) {
            stars[i]=new Star();
        }
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(background, 0, 0 );

        for (int i = 0; i <stars.length ; i++)
        {
            batch.draw(textureStar,
                    stars[i].position.x-textureStar.getWidth()/2,
                    stars[i].position.y-textureStar.getHeight()/2,
                    textureStar.getWidth()/2,textureStar.getHeight()/2,
                    textureStar.getWidth(), textureStar.getHeight(),
                    stars[i].scl, stars[i].scl,
                    0,0,0,textureStar.getWidth(), textureStar.getHeight(),
                    false,false);
        }
    }


    public void update (Hero hero, float dt)
    {
        for (int i = 0; i <stars.length ; i++)
        {
            stars[i].update(hero, dt);
        }
    }


    public  void dispose()
    {
        textureStar.dispose();
        background.dispose();
    }

}
