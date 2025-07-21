package com.jogo.ActRaiser.logica;

import com.jogo.ActRaiser.modelos.objetos.ObjetoDoJogo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.*;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class ControladorDeMoveis {

    private final CriadorDePersonagens criadorDePersonagens = new CriadorDePersonagens();
    private final GerenciadorDeColisoes gerenciadorDeColisoes = new GerenciadorDeColisoes();

    public Player criarPlayer() {
        return criadorDePersonagens.criarPlayer();
    }

    public Morcego criarMorcego(Player player) {
        return criadorDePersonagens.criarMorcego(player);
    }

    public Dragao criarDragao(Player player) {
        return criadorDePersonagens.criarDragao(player);
    }

    public Diabinho criarDiabinho(Player player) {
        return criadorDePersonagens.criarDiabinho(player);
    }

    public void verificarColisao(Player player, ObjetoDoJogo objeto) {
        gerenciadorDeColisoes.verificarColisao(player, objeto);
    }
}
