package com.jogo.ActRaiser.modelos.objetos.moveis.personagens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.moveis.Movel;

public abstract class Personagem extends Movel {
    protected int pontosVida, pontosMagia, pontosDano, pontuacao;
    protected boolean ativo = true;

    public Personagem(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade,
            int pontosVida, int pontosMagia, int pontosDano, int pontuacao) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade);
        this.pontosVida = pontosVida;
        this.pontosMagia = pontosMagia;
        this.pontosDano = pontosDano;
        this.pontuacao = pontuacao;
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

    public int getPontuacao() {
        return pontuacao;
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

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void adicionaPontosVida(int pontosVida) {
        this.pontosVida += pontosVida;
    }

    public void removePontosVida(int pontosDano) {
        this.pontosVida -= pontosDano;
    }

    public void adicionaPontosMagia(int pontosMagia) {
        this.pontosMagia += pontosMagia;
    }

    public void removePontosMagia(int pontosMagia) {
        this.pontosMagia -= pontosMagia;
    }

    public void adicionaPontosDano(int pontosDano) {
        this.pontosDano += pontosDano;
    }

    public void adicionaPontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
    }

    public void removePontuacao(int pontuacao) {
        this.pontuacao -= pontuacao;
    }

    public boolean estaAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
