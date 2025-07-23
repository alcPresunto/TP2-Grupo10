package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.screens.fases.PrimeiraFase;

public class TelaTutorial implements Screen {

    private final GameRunner gameRunner;
    private OrthographicCamera camera;

    public TelaTutorial(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
        inicializaCamera();
    }

    private void inicializaCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        limparTela();
        atualizarCamera();
        desenharMensagem();
        verificarEntrada();
    }

    private void limparTela() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.4f, 1); // azul escuro
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void atualizarCamera() {
        camera.update();
        gameRunner.batch.setProjectionMatrix(camera.combined);
    }

    private void desenharMensagem() {
        float centroX = Gdx.graphics.getWidth() / 2f;
        float centroY = Gdx.graphics.getHeight() / 2f;

        gameRunner.batch.begin();

        gameRunner.font.draw(gameRunner.batch, "TUTORIAL DE CONTROLES", centroX - 200, centroY + 120);
        gameRunner.font.draw(gameRunner.batch, "Movimentação do personagem:", centroX - 250, centroY + 80);
        gameRunner.font.draw(gameRunner.batch, "W - Cima", centroX - 180, centroY + 40);
        gameRunner.font.draw(gameRunner.batch, "S - Baixo", centroX - 180, centroY + 10);
        gameRunner.font.draw(gameRunner.batch, "A - Esquerda", centroX - 180, centroY - 30);
        gameRunner.font.draw(gameRunner.batch, "D - Direita", centroX - 180, centroY - 60);

        gameRunner.font.draw(gameRunner.batch, "ESPACO - Atirar magia", centroX - 180, centroY - 90);
        gameRunner.font.draw(gameRunner.batch, "E - Recuperar vida (custa 250 de magia)", centroX - 180, centroY - 120);

        gameRunner.font.draw(gameRunner.batch, "Pressione ENTER para começar!", centroX - 260, centroY - 150);

        gameRunner.batch.end();
    }

    private void verificarEntrada() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameRunner.setScreen(new PrimeiraFase(gameRunner));
        }
    }

    @Override
    public void resize(int width, int height) {
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
    public void dispose() {
    }
}
