package com.jogo.ActRaiser.modelos.objetos.moveis;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class ProjetilBuilder implements ObjetosBuilder {
    private float posicaoX, posicaoY, velocidade;
    private float direcaoX, direcaoY;
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
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    @Override
    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public void setDirecao(float direcaoX, float direcaoY) {
        this.direcaoX = direcaoX;
        this.direcaoY = direcaoY;
    }

    public Projetil buildProjetil() {
        return new Projetil(posicaoX, posicaoY, texture, hitbox, velocidade, direcaoX, direcaoY);
    }

    @Override
    public void setPontosVida(int pontosVida) {
    }

    @Override
    public void setPontosMagia(int pontosMagia) {
    }

    @Override
    public void setPontosDano(int pontosDano) {
    }

    @Override
    public void setPlayer(Player player) {
    }
}
