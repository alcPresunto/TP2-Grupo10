package com.jogo.ActRaiser.screens.fases;

import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaPrimeiraFase;
// import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class PrimeiraFase extends Fase {

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
        tempoAcumuladoMorcego += delta;
        tempoAcumuladoDragao += delta;

        if (tempoAcumuladoMorcego >= 5f) {
            Morcego novoMorcego = criadorDePersonagens.criarMorcego(player);
            inimigos.add(novoMorcego);
            personagens.add(novoMorcego);
            tempoAcumuladoMorcego = 0f;
        }

        if (tempoAcumuladoDragao >= 7f) {
            Dragao novoDragao = criadorDePersonagens.criarDragao(player);
            inimigos.add(novoDragao);
            personagens.add(novoDragao);
            tempoAcumuladoDragao = 0f;
        }
    }
}
