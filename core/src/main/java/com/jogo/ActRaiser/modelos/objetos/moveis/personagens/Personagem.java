package com.jogo.ActRaiser.modelos.objetos.moveis.personagens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.moveis.Movel;

public abstract class Personagem extends Movel {
    protected final int pontosVida, pontosMagia, pontosDano;

    public Personagem(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade);
        this.pontosVida = pontosVida;
        this.pontosMagia = pontosMagia;
        this.pontosDano = pontosDano;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public int getPontosMagia() {
        return pontosMagia;
    }

    public int getPontosDano() {
        return pontosDano;
    }

    public abstract void adicionaPontosVida(int pontosVida);

    public abstract void removePontosVida(int pontosDano);

    public abstract void adicionaPontosMagia(int pontosMagia);

    public abstract void removePontosMagia(int pontosMagia);

    public abstract void adicionaPontosDano(int pontosDano);
}
