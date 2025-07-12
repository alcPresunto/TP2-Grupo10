package com.jogo.ActRaiser.interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface Builder {
    public void setPosicaoX(float posicaoX);

    public void setPosicaoY(float posicaoY);

    public void setTexture(Texture texture);

    public void setHitbox(Rectangle hitbox);

    public void setVelocidade(float velocidade);

    public void setPontosVida(int pontosVida);

    public void setPontosMagia(int pontosMagia);

    public void setPontosDano(int pontosDano);

}
