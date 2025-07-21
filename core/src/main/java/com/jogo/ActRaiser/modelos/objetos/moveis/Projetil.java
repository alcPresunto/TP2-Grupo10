package com.jogo.ActRaiser.modelos.objetos.moveis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Projetil extends Movel {
    private boolean ativo = true;
    private float direcaoX, direcaoY;

    public Projetil(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade,
            float direcaoX, float direcaoY) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade);
        this.direcaoX = direcaoX;
        this.direcaoY = direcaoY;
    }

    @Override
    public void mover() {
        posicaoX += direcaoX * velocidade * Gdx.graphics.getDeltaTime();
        posicaoY += direcaoY * velocidade * Gdx.graphics.getDeltaTime();
        hitbox.setPosition(posicaoX, posicaoY);

        if (posicaoX < -50 || posicaoX > Gdx.graphics.getWidth() + 50 ||
                posicaoY < -50 || posicaoY > Gdx.graphics.getHeight() + 50) {
            ativo = false;
        }
    }

    public boolean estaAtivo() {
        return ativo;
    }

    public void destruir() {
        ativo = false;
    }

    public void desenha(SpriteBatch batch) {
        if (ativo && texture != null) {
            batch.draw(texture, posicaoX, posicaoY);
        }
    }
}
