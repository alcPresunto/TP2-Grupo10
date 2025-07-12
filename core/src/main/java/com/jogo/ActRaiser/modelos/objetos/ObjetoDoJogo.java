package com.jogo.ActRaiser.modelos.objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class ObjetoDoJogo {
    private final float posicaoX, posicaoY;
    private final Texture texture;
    private final Rectangle hitbox;

    public ObjetoDoJogo(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.texture = texture;
        this.hitbox = hitbox;
    }

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

}
