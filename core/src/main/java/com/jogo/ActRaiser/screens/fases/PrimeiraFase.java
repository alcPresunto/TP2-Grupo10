package com.jogo.ActRaiser.screens.fases;

import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaPrimeiraFase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.screens.intermediarias.PrimeiraFaseConcluida;

public class PrimeiraFase extends Fase {

    private static final int LIMITE_INIMIGOS = 7;
    private static final float TEMPO_PARA_SPAWN_MORCEGO = 5f;
    private static final float TEMPO_PARA_SPAWN_DRAGAO = 10f;

    private float tempoAcumuladoParaMorcego = 0f;
    private float tempoAcumuladoParaDragao = 0f;

    public PrimeiraFase(GameRunner gameRunner) {
        super(gameRunner, 350);
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
    protected void verificaSpawnInimigos(float tempoDecorrido) {
        if (inimigos == null || personagens == null)
            return;

        tempoAcumuladoParaMorcego += tempoDecorrido;
        tempoAcumuladoParaDragao += tempoDecorrido;

        if (inimigos.size() >= LIMITE_INIMIGOS)
            return;

        if (tempoAcumuladoParaMorcego >= TEMPO_PARA_SPAWN_MORCEGO) {
            Morcego novoMorcego = criadorDePersonagens.criarMorcego(jogador);
            inimigos.add(novoMorcego);
            personagens.add(novoMorcego);
            tempoAcumuladoParaMorcego = 0f;
        }

        if (tempoAcumuladoParaDragao >= TEMPO_PARA_SPAWN_DRAGAO) {
            Dragao novoDragao = criadorDePersonagens.criarDragao(jogador);
            inimigos.add(novoDragao);
            personagens.add(novoDragao);
            tempoAcumuladoParaDragao = 0f;
        }
    }

    @Override
    protected void fimDeJogo() {
        gameRunner.setScreen(new PrimeiraFaseConcluida(gameRunner));
    }
}
