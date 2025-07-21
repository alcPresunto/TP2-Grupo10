package com.jogo.ActRaiser.logica;

import com.jogo.ActRaiser.Director;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.*;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.*;

public class CriadorDePersonagens {

    private final Director director = new Director();

    public CriadorDePersonagens() {
    }

    public Player criarPlayer() {
        PlayerBuilder playerBuilder = new PlayerBuilder();
        director.constructorPlayer(playerBuilder);
        return playerBuilder.buildPlayer();
    }

    public Morcego criarMorcego(Player player) {
        MorcegoBuilder builder = new MorcegoBuilder();
        director.constructorMorcego(builder, player);
        return builder.buildMorcego();
    }

    public Dragao criarDragao(Player player) {
        DragaoBuilder builder = new DragaoBuilder();
        director.constructorDragao(builder, player);
        return builder.buildDragao();
    }

    public Diabinho criarDiabinho(Player player) {
        DiabinhoBuilder builder = new DiabinhoBuilder();
        director.constructorDiabinho(builder, player);
        return builder.buildDiabinho();
    }

}