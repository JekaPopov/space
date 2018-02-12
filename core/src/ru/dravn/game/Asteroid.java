package ru.dravn.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.toDegrees;


public class Asteroid {

    static Texture asteroidTexture;
    Vector2 position;
    Vector2 velosity;
    float scl;
    float angle;

    int hp;
    int hpMax;

    Circle hitArea;

    public Asteroid(Vector2 position, Vector2 velosity, float scl, int hpMax)
    {
        if(asteroidTexture==null)
        {
            asteroidTexture = new Texture("asteroid.png");
        }

        this.position = position;
        this.velosity = velosity;
        this.scl = scl;
        this.angle = 0.0f;

        this.hpMax = hpMax;
        this.hp = hpMax;

        this.hitArea = new Circle(position.x, position.y, asteroidTexture.getHeight()/2*scl );
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(asteroidTexture,
                position.x-asteroidTexture.getWidth()/2,
                position.y-asteroidTexture.getHeight()/2,
                asteroidTexture.getWidth()/2,asteroidTexture.getHeight()/2,
                asteroidTexture.getWidth(), asteroidTexture.getHeight(),
                1, 1,
                (float)toDegrees(angle),0,0,
                asteroidTexture.getWidth(), asteroidTexture.getHeight(),
                false,false);
    }

}
