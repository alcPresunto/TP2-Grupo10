package com.jogo.ActRaiser;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.jogo.ActRaiser.screens.StartMenu;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameRunner extends Game {
    public SpriteBatch batch;
    public FreeTypeFontGenerator fontGenerator;
    public FreeTypeFontParameter fontParameter;
    public BitmapFont font;
    public OrthographicCamera cameraHUD;

    @Override
    public void create() {
        batch = new SpriteBatch();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fontes/fonteJogo.ttf"));
        fontParameter = new FreeTypeFontParameter();
        fontParameter.size = 24;
        font = fontGenerator.generateFont(fontParameter);
        font.setColor(Color.YELLOW);
        cameraHUD = new OrthographicCamera();
        cameraHUD.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.setScreen(new StartMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        fontGenerator.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        cameraHUD.setToOrtho(false, width, height);
        if (getScreen() != null) {
            getScreen().resize(width, height);
        }
    }
}