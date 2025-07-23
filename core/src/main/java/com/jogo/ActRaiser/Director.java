package com.jogo.ActRaiser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.interfaces.ObjetosBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Director {

    public void constructorPlayer(ObjetosBuilder builder) {
        builder.setPosicaoX(100);
        builder.setPosicaoY(200);
        builder.setVelocidade(125);
        builder.setTexture(new Texture(Gdx.files.internal("assets/images/angel.png")));
        builder.setHitbox(new Rectangle(0, 0, 16, 24));
        builder.setPontosVida(10);
        builder.setPontosDano(1);
        builder.setPontosMagia(0);
        builder.setPontuacao(0);
    }

    public void constructorProjetil(ObjetosBuilder objetosBuilder, Player player) {
        float posicaoInicialX = player.getCentroX();
        float posicaoInicialY = player.getCentroY();

        objetosBuilder.setPosicaoX(posicaoInicialX);
        objetosBuilder.setPosicaoY(posicaoInicialY);
        objetosBuilder.setDirecao(player.getDirecaoX(), player.getDirecaoY());
        objetosBuilder.setVelocidade(400);
        objetosBuilder.setTexture(new Texture(Gdx.files.internal("assets/images/projetil.png")));
        objetosBuilder.setHitbox(new Rectangle(posicaoInicialX, posicaoInicialY, 16, 16));
        objetosBuilder.setPlayer(player);
    }

    public void constructorMorcego(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(criaPosicaoAleatoriaX());
        builder.setPosicaoY(criaPosicaoAleatoriaY());
        builder.setVelocidade(70);
        builder.setTexture(new Texture(Gdx.files.internal("assets/images/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 32, 24));
        builder.setPlayer(player);
        builder.setPontosVida(1);
        builder.setPontosDano(1);
        builder.setPontosMagia(10);
        builder.setPontuacao(5);
    }

    public void constructorDragao(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(criaPosicaoAleatoriaX());
        builder.setPosicaoY(criaPosicaoAleatoriaY());
        builder.setVelocidade(30);
        builder.setTexture(new Texture(Gdx.files.internal("assets/images/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 32, 24));
        builder.setPlayer(player);
        builder.setPontosVida(4);
        builder.setPontosDano(2);
        builder.setPontosMagia(20);
        builder.setPontuacao(10);
    }

    public void constructorDiabinho(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(criaPosicaoAleatoriaX());
        builder.setPosicaoY(criaPosicaoAleatoriaY());
        builder.setVelocidade(50);
        builder.setTexture(new Texture(Gdx.files.internal("assets/images/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 16, 24));
        builder.setPlayer(player);
        builder.setPontosVida(3);
        builder.setPontosDano(3);
        builder.setPontosMagia(50);
        builder.setPontuacao(30);
    }

    private float criaPosicaoAleatoriaX() {
        return MathUtils.random(50, Gdx.graphics.getWidth() - 50);
    }

    private float criaPosicaoAleatoriaY() {
        return MathUtils.random(50, Gdx.graphics.getHeight() - 50);
    }
}
