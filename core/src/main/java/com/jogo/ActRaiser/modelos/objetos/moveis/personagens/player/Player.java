package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.Director;
import com.jogo.ActRaiser.animacoes.AnimacaoPlayer;
import com.jogo.ActRaiser.modelos.objetos.moveis.Projetil;
import com.jogo.ActRaiser.modelos.objetos.moveis.ProjetilBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;

public class Player extends Personagem {

    private AnimacaoPlayer animacaoPlayer;

    private float direcaoX, direcaoY, tempoEntreTiros, timerTiro;
    private ArrayList<Projetil> projeteis;

    public Player(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano, int pontuacao) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano, pontuacao);
        this.animacaoPlayer = new AnimacaoPlayer(texture);
        this.direcaoX = 0;
        this.direcaoY = 1;
        this.tempoEntreTiros = 0.5f;
        this.timerTiro = 0f;
        projeteis = new ArrayList<Projetil>();
    }

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

    public TextureRegion getFrameAtual() {
        return animacaoPlayer.getFrame();
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
    public void desenha(SpriteBatch batch) {
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
        TextureRegion frame = animacaoPlayer.getFrame();
        float frameWidth = frame.getRegionWidth();
        float frameHeight = frame.getRegionHeight();

        float frameX = posicaoX;
        float frameY = posicaoY;

        float hitboxX = frameX + (frameWidth - hitbox.getWidth()) / 2f;
        float hitboxY = frameY + (frameHeight - hitbox.getHeight()) / 2f;

        hitbox.setPosition(hitboxX, hitboxY);
    }

    private void atirar() {
        Director director = new Director();
        ProjetilBuilder projetilBuilder = new ProjetilBuilder();
        director.constructorProjetil(projetilBuilder, this);

        Projetil novoProjetil = projetilBuilder.buildProjetil();
        projeteis.add(novoProjetil);
    }

    public void atualizarProjeteis() {
        for (int i = projeteis.size() - 1; i >= 0; i--) {
            Projetil p = projeteis.get(i);
            p.mover();
            if (!p.estaAtivo()) {
                projeteis.remove(i);
            }
        }
    }

    public void desenharProjeteis(SpriteBatch batch) {
        for (Projetil p : projeteis) {
            p.desenha(batch);
        }
    }

}
