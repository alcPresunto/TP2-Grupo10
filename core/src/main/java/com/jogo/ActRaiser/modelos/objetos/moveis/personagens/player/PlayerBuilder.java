package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.Builder;

public class PlayerBuilder implements Builder {
    protected float posicaoX, posicaoY, velocidade;
    protected int pontosVida, pontosMagia, pontosDano;
    protected Texture texture;
    protected Rectangle hitbox;

    public void setPosicaoX(float posicaoX) {
        this.posicaoX = posicaoX;
    }

    public void setPosicaoY(float posicaoY) {
        this.posicaoY = posicaoY;
    }

    @Override
    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    public void setPontosMagia(int pontosMagia) {
        this.pontosMagia = pontosMagia;
    }

    public void setPontosDano(int pontosDano) {
        this.pontosDano = pontosDano;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Player buildPlayer() {
        return new Player(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
    }

}