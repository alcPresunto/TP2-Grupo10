package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class HUD {
    private BitmapFont fonte;
    private final float padding = 20f;
    private final float linhaAltura = 30f;

    public HUD() {
        fonte = new BitmapFont();
        fonte.getData().setScale(1.2f);
    }

    public void desenhar(SpriteBatch batch, Player player) {
        if (player == null || batch == null)
            return;

        float yPos = Gdx.graphics.getHeight() - padding;

        fonte.draw(batch, "Vida: " + player.getPontosVida(), padding, yPos);
        fonte.draw(batch, "Magia: " + player.getPontosMagia(), padding, yPos - linhaAltura);
    }

    public void dispose() {
        if (fonte != null) {
            fonte.dispose();
        }
    }
}