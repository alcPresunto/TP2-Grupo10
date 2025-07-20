package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;

public class Player extends Personagem {

    public Player(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
    }

    @Override
    public void mover() {
        // Movimento do jogador
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            posicaoX += velocidade * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            posicaoX -= velocidade * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            posicaoY += velocidade * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            posicaoY -= velocidade * Gdx.graphics.getDeltaTime();
        }

        // Atualiza hitbox com nova posição
        hitbox.setPosition(posicaoX, posicaoY);
    }
}
