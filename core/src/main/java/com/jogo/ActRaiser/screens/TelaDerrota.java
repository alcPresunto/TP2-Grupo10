package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.screens.fases.PrimeiraFase;

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        gameRunner.batch.setProjectionMatrix(camera.combined);

        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        gameRunner.batch.begin();
        gameRunner.font.draw(gameRunner.batch, "Você morreu!", centerX - 50, centerY + 20);
        gameRunner.font.draw(gameRunner.batch, "Pressione ENTER para tentar novamente", centerX - 180, centerY - 20);
        gameRunner.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameRunner.setScreen(new PrimeiraFase(gameRunner));
        }
    }

    @Override
    public void dispose() {
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
}