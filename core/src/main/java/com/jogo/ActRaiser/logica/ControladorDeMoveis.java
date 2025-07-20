package com.jogo.ActRaiser.logica;

import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.Director;
import com.jogo.ActRaiser.modelos.objetos.ObjetoDoJogo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Diabinho;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.DiabinhoBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.DragaoBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Inimigo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.MorcegoBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.PlayerBuilder;

public class ControladorDeMoveis {
    Director director = new Director();

    public Player criaPlayer() {
        PlayerBuilder playerBuilder = new PlayerBuilder();
        director.constructorPlayer(playerBuilder);
        Player player = playerBuilder.buildPlayer();
        return player;
    }

    public Morcego criaMorcego(Player player) {
        MorcegoBuilder morcegoBuilder = new MorcegoBuilder();
        director.constructorMorcego(morcegoBuilder, player);
        Morcego morcego = morcegoBuilder.buildMorcego();
        return morcego;
    }

    public Dragao criaDragao(Player player) {
        DragaoBuilder dragaoBuilder = new DragaoBuilder();
        director.constructorDragao(dragaoBuilder, player);
        Dragao dragao = dragaoBuilder.buildDragao();
        return dragao;
    }

    public Diabinho criaDiabinho(Player player) {
        DiabinhoBuilder diabinhoBuilder = new DiabinhoBuilder();
        director.constructorDiabinho(diabinhoBuilder, player);
        Diabinho diabinho = diabinhoBuilder.buildDiabinho();
        return diabinho;
    }

    public void colisaoPlayer(Player player, ObjetoDoJogo objeto) {
        if (player != objeto && player.getHitbox().overlaps(objeto.getHitbox())) {

            if (objeto instanceof Inimigo) {
                player.removePontosVida(((Inimigo) objeto).getPontosDano());

                Rectangle playerHitbox = player.getHitbox();
                Rectangle objetoHitbox = objeto.getHitbox();
                float overlapX = calculaOverlapX(player, objeto);
                float overlapY = calculaOverlapY(player, objeto);

                if (overlapX < overlapY) {
                    if (playerHitbox.x < objetoHitbox.x) {
                        player.setPosicaoX(player.getPosicaoX() - overlapX);
                    } else {
                        player.setPosicaoX(player.getPosicaoX() + overlapX);
                    }
                } else {
                    if (playerHitbox.y < objetoHitbox.y) {
                        player.setPosicaoY(player.getPosicaoY() - overlapY);
                    } else {
                        player.setPosicaoY(player.getPosicaoY() + overlapY);
                    }
                }
            }
        }
    }

    private float calculaOverlapX(Player player, ObjetoDoJogo objeto) {
        Rectangle playerHitbox = player.getHitbox();
        Rectangle objetoHitbox = objeto.getHitbox();

        float overlapX = Math.min(playerHitbox.x + playerHitbox.width - objetoHitbox.x,
                objetoHitbox.x + objetoHitbox.width - playerHitbox.x);

        return overlapX;
    }

    private float calculaOverlapY(Player player, ObjetoDoJogo objeto) {
        Rectangle playerHitbox = player.getHitbox();
        Rectangle objetoHitbox = objeto.getHitbox();

        float overlapY = Math.min(
                playerHitbox.y + playerHitbox.height - objetoHitbox.y,
                objetoHitbox.y + objetoHitbox.height - playerHitbox.y);

        return overlapY;
    }

    // public void colisaoInimigo(Inimigo inimigo, ObjetoDoJogo objeto){
    // if (inimigo != objeto && inimigo.getHitbox().overlaps(objeto.getHitbox())) {

    // if (objeto instanceof ) {

    // }
    // }
    // }
}
