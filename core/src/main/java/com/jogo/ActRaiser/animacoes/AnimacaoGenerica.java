package com.jogo.ActRaiser.animacoes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AnimacaoGenerica {
    protected Animation<TextureRegion> animacao;
    protected float tempo = 0;

    public AnimacaoGenerica(Texture spriteSheet, int colunas, int linhas, int linhaAnimacao, int quantidadeFrames,
            float frameDuration) {

        animacao = criarAnimacao(spriteSheet, colunas, linhas, linhaAnimacao, quantidadeFrames, frameDuration);
    }

    protected Animation<TextureRegion> criarAnimacao(Texture spriteSheet, int colunas, int linhas, int linhaAnimacao,
            int quantidadeFrames, float frameDuration) {
        TextureRegion[][] grid = dividirSpriteSheet(spriteSheet, colunas, linhas);
        TextureRegion[] frames = extrairFrames(grid, linhaAnimacao, quantidadeFrames);
        return new Animation<>(frameDuration, frames);
    }

    protected TextureRegion[][] dividirSpriteSheet(Texture spriteSheet, int colunas, int linhas) {
        int larguraFrame = spriteSheet.getWidth() / colunas;
        int alturaFrame = spriteSheet.getHeight() / linhas;
        return TextureRegion.split(spriteSheet, larguraFrame, alturaFrame);
    }

    protected TextureRegion[] extrairFrames(TextureRegion[][] grid, int linha, int quantidadeFrames) {
        TextureRegion[] frames = new TextureRegion[quantidadeFrames];
        for (int i = 0; i < quantidadeFrames; i++) {
            frames[i] = grid[linha][i];
        }
        return frames;
    }

    public void atualizar(float delta) {
        tempo += delta;
    }

    public TextureRegion getFrame() {
        return animacao.getKeyFrame(tempo, true);
    }
}
