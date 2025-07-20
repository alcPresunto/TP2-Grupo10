package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Inimigo extends Personagem {
    protected Player player;

    public Inimigo(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano, Player player) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
        this.player = player;
    }

    @Override
    public void mover() {
        hitbox.x = posicaoX;
        hitbox.y = posicaoY;
        if (posicaoX < player.getPosicaoX()) {
            posicaoX += velocidade * Gdx.graphics.getDeltaTime();
        } else if (posicaoX > player.getPosicaoX()) {
            posicaoX -= velocidade * Gdx.graphics.getDeltaTime();
        }

        if (getPosicaoY() < player.getPosicaoY()) {
            this.posicaoY += velocidade * Gdx.graphics.getDeltaTime();
        } else if (getPosicaoY() > player.getPosicaoY()) {
            this.posicaoY -= velocidade * Gdx.graphics.getDeltaTime();
        }
    }

}
