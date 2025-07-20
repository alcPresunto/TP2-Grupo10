package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.logica.ControladorDeMoveis;
import com.jogo.ActRaiser.modelos.mapas.MapaPrimeiraFase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class PrimeiraFase implements Screen {
    private final GameRunner gameRunner;
    private MapaPrimeiraFase mapaPrimeiraFase;
    private Player player;
    private OrthographicCamera camera;
    private Vector2 cameraFoco;
    private ControladorDeMoveis controladorDeMoveis;

    public PrimeiraFase(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    @Override
    public void show() {
        controladorDeMoveis = new ControladorDeMoveis();
        mapaPrimeiraFase = new MapaPrimeiraFase();
        player = controladorDeMoveis.criaPlayer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cameraFoco = new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cameraFoco.set(player.getPosicaoX(), player.getPosicaoY());
        camera.position.set(cameraFoco, 0);camera.update();
        mapaPrimeiraFase.render(camera);
        gameRunner.batch.begin();
        gameRunner.batch.draw(player.getTexture(), player.getPosicaoX(), player.getPosicaoY());
        gameRunner.batch.end();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hide'");
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

}
