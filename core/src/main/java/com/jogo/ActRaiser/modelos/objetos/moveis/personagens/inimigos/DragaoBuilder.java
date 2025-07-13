package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;

public class DragaoBuilder implements ObjetosBuilder {
    private float posicaoX, posicaoY, velocidade;
    private int pontosVida, pontosMagia, pontosDano;
    private Texture texture;
    private Rectangle hitbox;

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

    public Dragao buildDragao() {
        return new Dragao(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
    }
}
