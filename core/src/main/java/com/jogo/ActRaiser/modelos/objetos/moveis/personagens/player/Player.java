package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.Director;
import com.jogo.ActRaiser.animacoes.AnimacaoPlayer;
import com.jogo.ActRaiser.modelos.objetos.moveis.Projetil;
import com.jogo.ActRaiser.modelos.objetos.moveis.ProjetilBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;

public class Player extends Personagem {
    private AnimacaoPlayer animacaoPlayer;

    private float direcaoX = 0;
    private float direcaoY = 1;
    private float tempoEntreTiros = 0.5f;
    private float timerTiro = 0f;
    private ArrayList<Projetil> projeteis = new ArrayList<Projetil>();

    public Player(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade,
            int pontosVida, int pontosMagia, int pontosDano) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
        this.animacaoPlayer = new AnimacaoPlayer(texture);
    }

    @Override
    public void mover() {
        float[] input = capturarEntrada();
        atualizarDirecao(input[0], input[1]);
        atualizarPosicao(input[0], input[1]);
        atualizarHitbox();

        timerTiro += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timerTiro >= tempoEntreTiros) {
            atirar();
            timerTiro = 0;
        }
    }

    @Override
    public void desenha(SpriteBatch batch){
        animacaoPlayer.atualizar(Gdx.graphics.getDeltaTime());
        batch.draw(animacaoPlayer.getFrame(), posicaoX, posicaoY);
    }

    private float[] capturarEntrada() {
        float deltaX = 0;
        float deltaY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            deltaX += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            deltaX -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            deltaY += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            deltaY -= 1;

        return new float[] { deltaX, deltaY };
    }

    private void atualizarDirecao(float deltaX, float deltaY) {
        if (deltaX != 0 || deltaY != 0) {
            float magnitude = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            direcaoX = deltaX / magnitude;
            direcaoY = deltaY / magnitude;
        }
    }

    private void atualizarPosicao(float deltaX, float deltaY) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        posicaoX += deltaX * velocidade * deltaTime;
        posicaoY += deltaY * velocidade * deltaTime;
    }

    private void atualizarHitbox() {
        hitbox.setPosition(posicaoX, posicaoY);
    }

    private void atirar() {
        Director director = new Director();
        ProjetilBuilder projetilBuilder = new ProjetilBuilder();
        director.constructorProjetil(projetilBuilder, this);

        Projetil novoProjetil = projetilBuilder.buildProjetil();
        projeteis.add(novoProjetil);
    }

    public void atualizarProjeteis(SpriteBatch batch) {
        ArrayList<Projetil> projeteisRemovidos = new ArrayList<Projetil>();

        for (Projetil projetil : projeteis) {
            projetil.mover();
            if (projetil.estaAtivo()) {
                projetil.desenha(batch);
                System.out.println("HEHEHEHE");
            } else {
                projeteisRemovidos.add(projetil);
            }
        }

        projeteis.removeAll(projeteisRemovidos);
    }

    // Getters
    public float getDirecaoX() {
        return direcaoX;
    }

    public float getDirecaoY() {
        return direcaoY;
    }

    public float getCentroX() {
        return posicaoX + hitbox.getWidth() / 2;
    }

    public float getCentroY() {
        return posicaoY + hitbox.getHeight() / 2;
    }

    public ArrayList<Projetil> getProjeteis() {
        return projeteis;
    }

}
