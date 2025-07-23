package com.jogo.ActRaiser.modelos.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoDoJogo {
    protected float posicaoX, posicaoY;
    protected Texture texture;
    protected Rectangle hitbox;

    public ObjetoDoJogo(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.texture = texture;
        this.hitbox = hitbox;
        this.hitbox.x = posicaoX;
        this.hitbox.y = posicaoY;
    }

    public void desenha(SpriteBatch batch){
        batch.draw(texture, posicaoX, posicaoY);
    }
    public abstract void atualiza();

    public float getPosicaoX() {
        return posicaoX;
    }

    public float getPosicaoY() {
        return posicaoY;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setPosicaoX(float posicaoX) {
        this.posicaoX = posicaoX;
    }

    public void setPosicaoY(float posicaoY) {
        this.posicaoY = posicaoY;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
