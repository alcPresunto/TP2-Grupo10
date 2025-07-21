package com.jogo.ActRaiser.animacoes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AnimacaoGenerica {
    protected Animation<TextureRegion> animacao;
    protected float tempo = 0;

    public AnimacaoGenerica(Texture spriteSheet, int colunas, int linhas, int linhaAnimacao, int quantidadeFrames,
            float frameDuration) {
        int larguraFrame = spriteSheet.getWidth() / colunas;
        int alturaFrame = spriteSheet.getHeight() / linhas;

        TextureRegion[][] grid = TextureRegion.split(spriteSheet, larguraFrame, alturaFrame);
        TextureRegion[] frames = new TextureRegion[quantidadeFrames];

        for (int i = 0; i < quantidadeFrames; i++) {
            frames[i] = grid[linhaAnimacao][i];
        }

        animacao = new Animation<>(frameDuration, frames);
    }

    public void atualizar(float delta) {
        tempo += delta;
    }

    public TextureRegion getFrame() {
        return animacao.getKeyFrame(tempo, true);
    }
}
