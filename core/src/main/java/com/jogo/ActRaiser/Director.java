package com.jogo.ActRaiser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;

public class Director {
    public void constructorPlayer(ObjetosBuilder builder) {
        builder.setPosicaoX(300);
        builder.setPosicaoY(400);
        builder.setVelocidade(5);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(300, 400, 32, 32));
    }

    public void constructorMorcego(ObjetosBuilder builder) {
        builder.setPosicaoX(600);
        builder.setPosicaoY(400);
        builder.setVelocidade(2);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(600, 400, 32, 32));
    }

    public void constructorDiabinho(ObjetosBuilder builder) {
        builder.setPosicaoX(0);
        builder.setPosicaoY(0);
        builder.setVelocidade(0);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(0, 0, 0, 0));
    }
}
