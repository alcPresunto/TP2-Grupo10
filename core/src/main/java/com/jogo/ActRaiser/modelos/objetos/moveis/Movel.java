package com.jogo.ActRaiser.modelos.objetos.moveis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.ObjetoDoJogo;

public abstract class Movel extends ObjetoDoJogo {
    protected final float velocidade;

    public Movel(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade) {
        super(posicaoX, posicaoY, texture, hitbox);
        this.velocidade = velocidade;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public abstract void mover();

    public abstract void colisao();
}
