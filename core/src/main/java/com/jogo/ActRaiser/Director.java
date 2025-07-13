package com.jogo.ActRaiser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Director {
    public void constructorPlayer(ObjetosBuilder builder) {
        builder.setPosicaoX(300);
        builder.setPosicaoY(400);
        builder.setVelocidade(175);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(300, 400, 32, 32));
    }

    public void constructorMorcego(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(600);
        builder.setPosicaoY(400);
        builder.setVelocidade(125);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(600, 400, 32, 32));
        builder.setPlayer(player);
    }

    public void constructorDiabinho(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(0);
        builder.setPosicaoY(0);
        builder.setVelocidade(0);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(0, 0, 0, 0));
        builder.setPlayer(player);
    }

    public void constructorDragao(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(0);
        builder.setPosicaoY(0);
        builder.setVelocidade(0);
        builder.setTexture(new Texture(Gdx.files.internal("assets/libgdx.png")));
        builder.setHitbox(new Rectangle(0, 0, 0, 0));
        builder.setPlayer(player);
    }
}
