package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaPrimeiraFase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.logica.CriadorDePersonagens;
import com.jogo.ActRaiser.logica.GerenciadorDeColisoes;

public class PrimeiraFase implements Screen {
    private final GameRunner gameRunner;
    private MapaPrimeiraFase mapaPrimeiraFase;
    private OrthographicCamera camera;
    private CriadorDePersonagens criadorDePersonagens;
    private GerenciadorDeColisoes gerenciadorDeColisoes;
    private Morcego morcego;
    private Player player;

    public PrimeiraFase(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    @Override
    public void show() {
        mapaPrimeiraFase = new MapaPrimeiraFase();
        criadorDePersonagens = new CriadorDePersonagens();
        gerenciadorDeColisoes = new GerenciadorDeColisoes();

        player = criadorDePersonagens.criarPlayer();
        morcego = criadorDePersonagens.criarMorcego(player);

        camera = new OrthographicCamera();
        // Zoom de 2x: diminui a viewport pela metade
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza a posição da câmera para o centro do player
        camera.position.set(
                player.getPosicaoX() + player.getTexture().getWidth() / 2f,
                player.getPosicaoY() + player.getTexture().getHeight() / 2f,
                0);
        camera.update();

        // Renderiza o mapa com a câmera atualizada
        mapaPrimeiraFase.render(camera);
        player.atualiza();
        morcego.atualiza();
        gerenciadorDeColisoes.verificarColisao(player, morcego);

        // Garante que o batch use a matriz de projeção da câmera
        gameRunner.batch.setProjectionMatrix(camera.combined);
        gameRunner.batch.begin();
        player.desenha(gameRunner.batch);
        morcego.desenha(gameRunner.batch);
        gameRunner.batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

}
