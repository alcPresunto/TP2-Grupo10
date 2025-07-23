package com.jogo.ActRaiser.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.screens.fases.Fase;

public class HUD {
    private BitmapFont fonte;
    private Texture coracao;
    private Texture magiaIcone;
    private Fase faseAtual;

    private final float padding = 20f;
    private final float linhaAltura = 12f;
    private final float iconeTamanho = 30f;

    private float posicaoY = Gdx.graphics.getHeight() - padding;

    public HUD(GameRunner gameRunner, Fase fase) {
        fonte = gameRunner.font;
        fonte.getData().setScale(1.2f);

        coracao = new Texture(Gdx.files.internal("assets/images/coracao.png"));
        magiaIcone = new Texture(Gdx.files.internal("assets/images/magia.png"));
        this.faseAtual = fase;
    }

    public void desenhar(SpriteBatch batch, Player player) {
        if (player == null || batch == null)
            return;

        desenhaCoracao(batch, player);
        desenhaMagia(batch, player);
        desenhaPontuacao(batch, player, faseAtual);
    }

    public void dispose() {
        if (fonte != null)
            fonte.dispose();
        if (coracao != null)
            coracao.dispose();
        if (magiaIcone != null)
            magiaIcone.dispose();
    }

    private void desenhaCoracao(SpriteBatch batch, Player player) {
        batch.draw(coracao, padding, posicaoY - iconeTamanho + 10, iconeTamanho, iconeTamanho);
        fonte.draw(batch, String.valueOf(player.getPontosVida()), padding + iconeTamanho + 5, posicaoY);
    }

    private void desenhaMagia(SpriteBatch batch, Player player) {
        float magiaY = posicaoY - iconeTamanho - linhaAltura;
        batch.draw(magiaIcone, padding, magiaY - iconeTamanho + 10, iconeTamanho, iconeTamanho);
        fonte.draw(batch, String.valueOf(player.getPontosMagia()), padding + iconeTamanho + 5, magiaY);
    }

    private void desenhaPontuacao(SpriteBatch batch, Player player, Fase fase) {
        String textoPontuacao = "Score: " + player.getPontuacao() + " / " + fase.getPontuacaoParaVencer();

        float posicaoX = padding;
        float posicaoYPontuacao = posicaoY - 2 * (iconeTamanho + linhaAltura);

        fonte.draw(batch, textoPontuacao, posicaoX, posicaoYPontuacao);
    }

}
