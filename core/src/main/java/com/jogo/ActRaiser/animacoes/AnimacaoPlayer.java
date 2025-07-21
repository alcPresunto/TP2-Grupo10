package com.jogo.ActRaiser.animacoes;

import com.badlogic.gdx.graphics.Texture;

public class AnimacaoPlayer extends AnimacaoGenerica {

    public AnimacaoPlayer(Texture spriteSheet) {
        // Exemplo: 8 colunas, 4 linhas, pegando 2 frames da linha 0 (idle)
        super(spriteSheet, 8, 4, 0, 2, 0.1f);
    }
}
