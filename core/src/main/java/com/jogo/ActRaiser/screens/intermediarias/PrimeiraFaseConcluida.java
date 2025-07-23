package com.jogo.ActRaiser.screens.intermediarias;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.logica.ControladorDeSons;
import com.jogo.ActRaiser.screens.fases.SegundaFase;

public class PrimeiraFaseConcluida implements Screen {

    private final GameRunner gameRunner;
    private OrthographicCamera camera;

    public PrimeiraFaseConcluida(GameRunner gameRunner) {
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
        tocarMusicaVitoria();
    }

    private void limparTela() {
        Gdx.gl.glClearColor(0.12f, 0.73f, 0.35f, 1);
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
        gameRunner.font.draw(gameRunner.batch, "Fase concluída!", centroX - 60, centroY + 20);
        gameRunner.font.draw(gameRunner.batch, "Pressione ENTER para continuar", centroX - 150, centroY - 20);
        gameRunner.batch.end();
    }

    private void verificarEntrada() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameRunner.setScreen(new SegundaFase(gameRunner));
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

    private void tocarMusicaVitoria() {
        Music musicaVitoria = ControladorDeSons.getTelaVitoriaMusic();
        musicaVitoria.setVolume(0.25f);
        musicaVitoria.play();
    }
}
