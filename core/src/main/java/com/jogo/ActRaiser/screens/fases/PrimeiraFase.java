package com.jogo.ActRaiser.screens.fases;

import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaPrimeiraFase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class PrimeiraFase extends Fase {
    private static final int LIMITE_INIMIGOS = 10;
    private static final float TEMPO_SPAWN_MORCEGO = 5f;
    private static final float TEMPO_SPAWN_DRAGAO = 7f;

    private float tempoAcumuladoMorcego = 0f;
    private float tempoAcumuladoDragao = 0f;

    public PrimeiraFase(GameRunner gameRunner) {
        super(gameRunner);
    }

    @Override
    protected MapaPrimeiraFase criaMapa() {
        return new MapaPrimeiraFase();
    }

    @Override
    protected Player criaPlayer() {
        return criadorDePersonagens.criarPlayer();
    }

    @Override
    protected void verificaSpawnInimigos(float delta) {
        if (inimigos == null || personagens == null)
            return;

        tempoAcumuladoMorcego += delta;
        tempoAcumuladoDragao += delta;

        if (inimigos.size() >= LIMITE_INIMIGOS) {
            return;
        }

        if (tempoAcumuladoMorcego >= TEMPO_SPAWN_MORCEGO) {
            Morcego novoMorcego = criadorDePersonagens.criarMorcego(player);
            inimigos.add(novoMorcego);
            personagens.add(novoMorcego);
            tempoAcumuladoMorcego = 0f;
        }

        if (tempoAcumuladoDragao >= TEMPO_SPAWN_DRAGAO) {
            Dragao novoDragao = criadorDePersonagens.criarDragao(player);
            inimigos.add(novoDragao);
            personagens.add(novoDragao);
            tempoAcumuladoDragao = 0f;
        }
    }
}