package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Diabinho extends Inimigo{

    public Diabinho(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int pontosVida,
            int pontosMagia, int pontosDano) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, pontosVida, pontosMagia, pontosDano);
    }
    
}
