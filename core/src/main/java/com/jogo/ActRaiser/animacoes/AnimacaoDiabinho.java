package com.jogo.ActRaiser.animacoes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimacaoDiabinho extends AnimacaoGenerica {

    public AnimacaoDiabinho(Texture spriteSheet, int colunas, int linhas, int linhaAnimacao, int quantidadeFrames,
            float frameDuration) {
        super(spriteSheet, colunas, linhas, linhaAnimacao, quantidadeFrames, frameDuration);
    }

    @Override
    protected TextureRegion[][] dividirSpriteSheet(Texture spriteSheet, int colunas, int linhas) {
        int larguraFrame = spriteSheet.getWidth() / colunas;
        int alturaFrame = spriteSheet.getHeight() / linhas;
        return TextureRegion.split(spriteSheet, larguraFrame, alturaFrame);
    }
}
