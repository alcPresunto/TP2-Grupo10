package com.jogo.ActRaiser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Director {
    public void constructorPlayer(ObjetosBuilder builder) {
        builder.setPosicaoX(100);
        builder.setPosicaoY(200);
        builder.setVelocidade(175);
        builder.setTexture(new Texture(Gdx.files.internal("assets/angel.png")));
        builder.setHitbox(new Rectangle(0, 0, 20, 20));
        builder.setPontosVida(5);
        builder.setPontosDano(1);
        builder.setPontosMagia(0);
    }

    public void constructorProjetil(ObjetosBuilder objetosBuilder, Player player) {
        float posicaoInicialX = player.getCentroX();
        float posicaoInicialY = player.getCentroY();

        objetosBuilder.setPosicaoX(posicaoInicialX);
        objetosBuilder.setPosicaoY(posicaoInicialY);
        objetosBuilder.setDirecao(player.getDirecaoX(), player.getDirecaoY());
        objetosBuilder.setVelocidade(400);
        objetosBuilder.setTexture(new Texture(Gdx.files.internal("assets/angel.png")));
        objetosBuilder.setHitbox(new Rectangle(posicaoInicialX, posicaoInicialY, 16, 16));
        objetosBuilder.setPlayer(player);
    }

    public void constructorMorcego(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(300);
        builder.setPosicaoY(50);
        builder.setVelocidade(125);
        builder.setTexture(new Texture(Gdx.files.internal("assets/enemies.png")));
        builder.setHitbox(new Rectangle(300, 50, 32, 32));
        builder.setPlayer(player);
    }

    public void constructorDragao(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(0);
        builder.setPosicaoY(0);
        builder.setVelocidade(100);
        builder.setTexture(new Texture(Gdx.files.internal("assets/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 32, 32));
        builder.setPlayer(player);
    }

    public void constructorDiabinho(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(0);
        builder.setPosicaoY(0);
        builder.setVelocidade(0);
        builder.setTexture(new Texture(Gdx.files.internal("assets/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 0, 0));
        builder.setPlayer(player);
    }

}
