package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.animacoes.AnimacaoGenerica;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Dragao extends Inimigo{

    public Dragao(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano, int pontuacao, Player player, AnimacaoGenerica animacao) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano, pontuacao, player,
                animacao);
    }
    
}
