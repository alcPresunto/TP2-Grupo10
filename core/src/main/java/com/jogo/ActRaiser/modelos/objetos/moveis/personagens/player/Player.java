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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionaPontosMagia'");
    }

    @Override
    public void removePontosMagia(int pontosMagia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removePontosMagia'");
    }

    @Override
    public void adicionaPontosDano(int pontosDano) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionaPontosDano'");
    }

}
