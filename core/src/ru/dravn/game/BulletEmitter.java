package ru.dravn.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletEmitter
{
    private static final BulletEmitter ourInstance = new BulletEmitter();


    public static BulletEmitter getInstance()
    {
        return ourInstance;
    }

    private Texture bulletTexture;
    Bullet[] bullets;
    private BulletEmitter()
    {
        bulletTexture = new Texture("bullet.png");
        bullets = new Bullet[200];
        for (int i = 0; i < bullets.length; i++)
        {
            bullets[i] = new Bullet();
        }
    }
    public void update(float dt)
    {
        for (Bullet o : bullets)
        {
            if (o.active)
            {
                o.update(dt);
            }
        }
    }
    public void render(SpriteBatch batch)
    {
        for (Bullet o : bullets)
        {
            if (o.active)
            {
                batch.draw(bulletTexture,
                        o.position.x - bulletTexture.getWidth()/2,
                        o.position.y - bulletTexture.getHeight()/2);
            }
        }
    }
}
