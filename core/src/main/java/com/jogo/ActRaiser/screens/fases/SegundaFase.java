package com.jogo.ActRaiser.screens.fases;

import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaSegundaFase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Diabinho;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class SegundaFase extends Fase {
    private static final int LIMITE_INIMIGOS = 10;
    private static final float TEMPO_PARA_SPAWN_MORCEGO = 5f;
    private static final float TEMPO_PARA_SPAWN_DRAGAO = 7f;
    private static final float TEMPO_PARA_SPAWN_DIABINHO = 9f;

    private float tempoAcumuladoParaMorcego = 0f;
    private float tempoAcumuladoParaDragao = 0f;
    private float tempoAcumuladoParaDiabinho = 0f;

    public SegundaFase(GameRunner gameRunner) {
        super(gameRunner);
    }

    @Override
    protected MapaSegundaFase criaMapa() {
        return new MapaSegundaFase();
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
        tempoAcumuladoParaDiabinho += tempoDecorrido;

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

        if (tempoAcumuladoParaDiabinho >= TEMPO_PARA_SPAWN_DIABINHO) {
            Diabinho novoDiabinho = criadorDePersonagens.criarDiabinho(jogador);
            inimigos.add(novoDiabinho);
            personagens.add(novoDiabinho);
            tempoAcumuladoParaDiabinho = 0f;
        }
    }
}
