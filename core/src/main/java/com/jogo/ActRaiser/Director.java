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
        builder.setVelocidade(150);
        builder.setTexture(new Texture(Gdx.files.internal("assets/angel.png")));
        builder.setHitbox(new Rectangle(0, 0, 20, 20));
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
        objetosBuilder.setTexture(new Texture(Gdx.files.internal("assets/projetil.png")));
        objetosBuilder.setHitbox(new Rectangle(posicaoInicialX, posicaoInicialY, 16, 16));
        objetosBuilder.setPlayer(player);
    }

    public void constructorMorcego(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(criaPosicaoAleatoriaX());
        builder.setPosicaoY(criaPosicaoAleatoriaY());
        builder.setVelocidade(80);
        builder.setTexture(new Texture(Gdx.files.internal("assets/enemies.png")));
        builder.setHitbox(new Rectangle(300, 50, 32, 32));
        builder.setPlayer(player);
        builder.setPontosVida(2);
        builder.setPontosDano(1);
        builder.setPontosMagia(20);
        builder.setPontuacao(20);
    }

    public void constructorDragao(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(criaPosicaoAleatoriaX());
        builder.setPosicaoY(criaPosicaoAleatoriaY());
        builder.setVelocidade(50);
        builder.setTexture(new Texture(Gdx.files.internal("assets/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 32, 32));
        builder.setPlayer(player);
        builder.setPontosVida(5);
        builder.setPontosDano(2);
        builder.setPontosMagia(50);
        builder.setPontuacao(100);
    }

    public void constructorDiabinho(ObjetosBuilder builder, Player player) {
        builder.setPosicaoX(criaPosicaoAleatoriaX());
        builder.setPosicaoY(criaPosicaoAleatoriaY());
        builder.setVelocidade(65);
        builder.setTexture(new Texture(Gdx.files.internal("assets/enemies.png")));
        builder.setHitbox(new Rectangle(0, 0, 0, 0));
        builder.setPlayer(player);
        builder.setPontosVida(3);
        builder.setPontosDano(3);
        builder.setPontosMagia(30);
        builder.setPontuacao(35);
    }

    private float criaPosicaoAleatoriaX() {
        return MathUtils.random(50, Gdx.graphics.getWidth() - 50);
    }

    private float criaPosicaoAleatoriaY() {
        return MathUtils.random(50, Gdx.graphics.getHeight() - 50);
    }
}
