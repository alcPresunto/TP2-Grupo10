package com.jogo.ActRaiser.screens.intermediarias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.screens.StartMenu;

public class TelaDerrota implements Screen {

    private final GameRunner gameRunner;
    private OrthographicCamera camera;

    public TelaDerrota(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        limparTela();
        atualizarCamera();
        desenharMensagem();
        verificarEntrada();
    }

    private void limparTela() {
        Gdx.gl.glClearColor(0.90f, 0.22f, 0.27f, 1);
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
        gameRunner.font.draw(gameRunner.batch, "Você morreu!", centroX - 50, centroY + 20);
        gameRunner.font.draw(gameRunner.batch, "Pressione ENTER para tentar novamente", centroX - 180, centroY - 20);
        gameRunner.batch.end();
    }

    private void verificarEntrada() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameRunner.setScreen(new StartMenu(gameRunner));
        }
    }

    @Override
    public void show() {
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
