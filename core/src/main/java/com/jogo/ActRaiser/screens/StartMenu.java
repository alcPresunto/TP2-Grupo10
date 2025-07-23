package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.screens.fases.PrimeiraFase;

public class StartMenu implements Screen {

    private final GameRunner gameRunner;
    private Texture botaoJogar;
    private Texture botaoJogarHover;
    private float botaoX, botaoY, botaoWidth, botaoHeight;

    public StartMenu(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
        botaoJogar = new Texture(Gdx.files.internal("assets/images/botaoJogar.png"));
        botaoJogarHover = new Texture(Gdx.files.internal("assets/images/botaoJogarHover.png"));

        gameRunner.font.getData().setScale(1.5f);

        botaoWidth = botaoJogar.getWidth();
        botaoHeight = botaoJogar.getHeight();
        botaoX = (Gdx.graphics.getWidth() - botaoWidth) / 2;
        botaoY = (Gdx.graphics.getHeight() - botaoHeight) / 3;
    }

    @Override
    public void render(float delta) {
        limpaTela();

        if (Gdx.input.justTouched() && isHovering()) {
            dispose();
            gameRunner.setScreen(new PrimeiraFase(gameRunner));
        }

        gameRunner.batch.begin();
        escreve();
        Texture botaoAtual = isHovering() ? botaoJogarHover : botaoJogar;
        gameRunner.batch.draw(botaoAtual, botaoX, botaoY);
        gameRunner.batch.end();
    }

    @Override
    public void dispose() {
        if (botaoJogar != null) {
            botaoJogar.dispose();
        }
        if (botaoJogarHover != null) {
            botaoJogarHover.dispose();
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

    private void limpaTela() {
        Gdx.gl.glClearColor(0.27f, 0.48f, 0.62f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private float getMouseX() {
        return Gdx.input.getX();
    }

    private float getMouseY() {
        return Gdx.input.getY();
    }

    private boolean isHovering() {
        return getMouseX() >= botaoX && getMouseX() <= botaoX + botaoWidth &&
                getMouseY() >= botaoY && getMouseY() <= botaoY + botaoHeight;
    }

    private void escreve(){
        gameRunner.font.draw(gameRunner.batch, "ActRaiser - Modo Cidade", Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight() - 50f);
    }
}