package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class HUD {
    private BitmapFont fonte;
    private Texture coracao;
    private Texture magiaIcone;
    private final float padding = 20f;
    private final float linhaAltura = 12f;
    private final float iconeTamanho = 30f;

    public HUD(GameRunner gameRunner) {
        fonte = gameRunner.font;
        fonte.getData().setScale(1.2f);

        coracao = new Texture(Gdx.files.internal("assets/images/coracao.png"));
        magiaIcone = new Texture(Gdx.files.internal("assets/images/magia.png"));
    }

    public void desenhar(SpriteBatch batch, Player player) {
        if (player == null || batch == null)
            return;

        float yPos = Gdx.graphics.getHeight() - padding;

        batch.draw(coracao, padding, yPos - iconeTamanho + 10, iconeTamanho, iconeTamanho);
        fonte.draw(batch, String.valueOf(player.getPontosVida()), padding + iconeTamanho + 5, yPos);

        float magiaY = yPos - iconeTamanho - linhaAltura;
        batch.draw(magiaIcone, padding, magiaY - iconeTamanho + 10, iconeTamanho, iconeTamanho);
        fonte.draw(batch, String.valueOf(player.getPontosMagia()), padding + iconeTamanho + 5, magiaY);
    }

    public void dispose() {
        if (fonte != null) fonte.dispose();
        if (coracao != null) coracao.dispose();
        if (magiaIcone != null) magiaIcone.dispose();
    }
}
