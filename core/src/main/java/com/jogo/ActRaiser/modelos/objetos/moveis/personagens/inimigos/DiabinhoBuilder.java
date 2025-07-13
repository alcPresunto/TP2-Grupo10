package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class DiabinhoBuilder implements ObjetosBuilder {
    protected float posicaoX, posicaoY, velocidade;
    protected int pontosVida, pontosMagia, pontosDano;
    protected Texture texture;
    protected Rectangle hitbox;
    protected Player player;

    @Override
    public void setPosicaoX(float posicaoX) {
        this.posicaoX = posicaoX;
    }

    @Override
    public void setPosicaoY(float posicaoY) {
        this.posicaoY = posicaoY;
    }

    @Override
    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    @Override
    public void setPontosMagia(int pontosMagia) {
        this.pontosMagia = pontosMagia;
    }

    @Override
    public void setPontosDano(int pontosDano) {
        this.pontosDano = pontosDano;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Diabinho buildDiabinho() {
        return new Diabinho(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano,
                player);
    }
}
