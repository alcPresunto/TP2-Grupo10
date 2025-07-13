package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Inimigo extends Personagem {
    protected final Player player;

    public Inimigo(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano, Player player) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
        this.player = player;
    }

    @Override
    public void mover() {
        // TODO Auto-generated method stub
        System.out.println("TesteInimigo");
    }

    @Override
    public void colisao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colisao'");
    }

    @Override
    public void adicionaPontosVida(int pontosVida) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionaPontosVida'");
    }

    @Override
    public void removePontosVida(int pontosDano) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePontosVida'");
    }

    @Override
    public void adicionaPontosMagia(int pontosMagia) {
        throw new UnsupportedOperationException("Unimplemented method 'adicionaPontosMagia'");
    }

    @Override
    public void removePontosMagia(int pontosMagia) {
        throw new UnsupportedOperationException("Unimplemented method 'removePontosMagia'");
    }

    @Override
    public void adicionaPontosDano(int pontosDano) {
        throw new UnsupportedOperationException("Unimplemented method 'adicionaPontosDano'");
    }

}
