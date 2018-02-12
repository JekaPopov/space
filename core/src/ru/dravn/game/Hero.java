package ru.dravn.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.*;

public class Hero {


    Texture shipTexture;
    Vector2 position;
    Vector2 velocity;
    float angle;

    int hp;
    int hpMax;

    float lowEnginePower;
    float currentEnginePower;
    float maxEnginePower;

    float rotationSpeed;

    float fireRate;
    float fireCounter;

    Circle hitArea;


    public Hero()
    {
        this.shipTexture = new Texture("ship.png");
        this.position = new Vector2(640,360);
        this.velocity = new Vector2(0,0);
        this.maxEnginePower = 400.0f;
        this.lowEnginePower = 200.0f;
        this.rotationSpeed = 3.14f;
        this.hpMax = 100;
        this.hp =hpMax;
        this.hitArea = new Circle(position.x, position.y, 25);
        this.fireCounter = 0;
        fireRate = 0.25f;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(shipTexture,
                position.x-shipTexture.getWidth()/2,
                position.y-shipTexture.getHeight()/2,
                shipTexture.getWidth()/2,shipTexture.getHeight()/2,
                shipTexture.getWidth(), shipTexture.getHeight(),
                1, 1,
                (float)toDegrees(angle),0,0,
                shipTexture.getWidth(), shipTexture.getHeight(),
                false,false);
    }

    public void update(float dt)
    {
        position.mulAdd(velocity, dt);
        velocity.scl(0.97f);

        if (Space.isAndroid) {
            if (InputHandler.isJustTouched())
            {
                currentEnginePower = lowEnginePower;
            }

            if (InputHandler.isTouched())
            {
                float tx = InputHandler.getX();
                float ty = InputHandler.getY();

                float tempAng = (float) atan2(ty - position.y, tx - position.x);

                if (angle > tempAng)
                {
                    if (angle - tempAng < PI)
                    {
                        angle -= rotationSpeed * dt;
                    }
                    else
                    {
                        angle += rotationSpeed * dt;
                    }
                }
                if (angle < tempAng)
                {
                    if (angle - tempAng < PI)
                    {
                        angle += rotationSpeed * dt;
                    }
                    else
                    {
                        angle -= rotationSpeed * dt;
                    }
                }
                currentEnginePower += 100 * dt;

                if (currentEnginePower > maxEnginePower)
                {
                    currentEnginePower = maxEnginePower;
                }
                velocity.add((float) (currentEnginePower * cos(angle) * dt), (float) (currentEnginePower * sin(angle) * dt));
            }
        }

        if (!Space.isAndroid)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)
                    || Gdx.input.isKeyJustPressed(Input.Keys.UP))
            {
                currentEnginePower = lowEnginePower;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)
                    || Gdx.input.isKeyPressed(Input.Keys.UP))
            {
                if (currentEnginePower > maxEnginePower)
                {
                    currentEnginePower = maxEnginePower;
                }
                velocity.add((float) (currentEnginePower * cos(angle) * dt),
                        (float) (currentEnginePower * sin(angle) * dt));
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A) ||
                    (Gdx.input.isKeyPressed(Input.Keys.LEFT)))
            {
                angle += rotationSpeed * dt;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) ||
                    (Gdx.input.isKeyPressed(Input.Keys.RIGHT)))
            {
                angle -= rotationSpeed * dt;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                fireCounter += dt;

                if (fireCounter > fireRate)
                {
                    fireCounter = 0;
                    fire();
                }
            }

            //держим угол от -PI до PI
            if (angle < -PI) angle += 2 * PI;
            if (angle > PI) angle -= 2 * PI;

            if (position.y > Space.ScreenHeight + shipTexture.getHeight() / 2)
            {
                position.y = -shipTexture.getHeight() / 2;
            }
            if (position.y < -shipTexture.getHeight() / 2)
            {
                position.y = Space.ScreenHeight + shipTexture.getHeight() / 2;
            }

            if (position.x > Space.ScreenWidth + shipTexture.getWidth() / 2)
            {
                position.x = -shipTexture.getWidth() / 2;
            }
            if (position.x < -shipTexture.getWidth() / 2)
            {
                position.x = Space.ScreenWidth + shipTexture.getWidth() / 2;
            }

            hitArea.x = position.x;
            hitArea.y = position.y;
        }
    }
    public void fire()
    {
        Bullet[] bullets =BulletEmitter.getInstance().bullets;
        for (int i = 0; i <bullets.length; i++)
        {
            if(!bullets[i].active)
            {
                bullets[i].setup(position.x,position.y,
                        400*(float)cos(angle), 400*(float)sin(angle));
                break;
            }
        }

    }
}


