package com.jogo.ActRaiser.animacoes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimacaoPlayer {
    private Animation<TextureRegion> animacao;
    private float tempo;

    public AnimacaoPlayer(Texture spriteSheet) {
        int colunas = 6;
        int linhas = 6;

        int larguraFrame = spriteSheet.getWidth() / colunas;
        int alturaFrame = spriteSheet.getHeight() / linhas;

        TextureRegion[][] grid = TextureRegion.split(spriteSheet, larguraFrame, alturaFrame);

        // Vamos usar a primeira linha (linha 0) como animação de idle
        TextureRegion[] frames = new TextureRegion[colunas];
        for (int i = 0; i < colunas; i++) {
            frames[i] = grid[0][i];
        }

        animacao = new Animation<>(0.1f, frames); // 10fps
    }

    public void atualizar(float delta) {
        tempo += delta;
    }

    public TextureRegion getFrame() {
        return animacao.getKeyFrame(tempo, true); // loop
    }
}
