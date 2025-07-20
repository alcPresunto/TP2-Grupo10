package com.jogo.ActRaiser.logica;

import com.jogo.ActRaiser.Director;
import com.jogo.ActRaiser.modelos.objetos.moveis.Movel;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Diabinho;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.DiabinhoBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.DragaoBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.MorcegoBuilder;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.PlayerBuilder;

public class ControladorDeMoveis {
    Director director = new Director();

    public Player criaPlayer(){
        PlayerBuilder playerBuilder = new PlayerBuilder();
        director.constructorPlayer(playerBuilder);
        Player player = playerBuilder.buildPlayer();
        return player;
    }

    public Morcego criaMorcego(Player player){
        MorcegoBuilder morcegoBuilder = new MorcegoBuilder();
        director.constructorMorcego(morcegoBuilder, player);
        Morcego morcego = morcegoBuilder.buildMorcego();
        return morcego;
    }

    public Dragao criaDragao(Player player){
        DragaoBuilder dragaoBuilder = new DragaoBuilder();
        director.constructorDragao(dragaoBuilder, player);
        Dragao dragao = dragaoBuilder.buildDragao();
        return dragao;
    }

    public Diabinho criaDiabinho(Player player){
        DiabinhoBuilder diabinhoBuilder = new DiabinhoBuilder();
        director.constructorDiabinho(diabinhoBuilder, player);
        Diabinho diabinho = diabinhoBuilder.buildDiabinho();
        return diabinho;
    }

    public void colisao(Movel objetoMovel, Movel outroObjetoMovel){
        if (objetoMovel != outroObjetoMovel && objetoMovel.getHitbox().overlaps(outroObjetoMovel.getHitbox())) {
        }
    }
}
