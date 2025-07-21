package com.jogo.ActRaiser.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaPrimeiraFase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Dragao;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Morcego;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.logica.CriadorDePersonagens;
import com.jogo.ActRaiser.logica.GerenciadorDeColisoes;

public class PrimeiraFase implements Screen {
    private final GameRunner gameRunner;

    private float tempoAcumuladoMorcego, tempoAcumuladoDragao;

    private MapaPrimeiraFase mapaPrimeiraFase;
    private OrthographicCamera camera;

    private CriadorDePersonagens criadorDePersonagens;
    private GerenciadorDeColisoes gerenciadorDeColisoes;

    private ArrayList<Personagem> personagens;
    private ArrayList<Personagem> inimigos;
    private Player player;

    public PrimeiraFase(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    private void inicializaElementos() {
        mapaPrimeiraFase = new MapaPrimeiraFase();
        criadorDePersonagens = new CriadorDePersonagens();
        gerenciadorDeColisoes = new GerenciadorDeColisoes();
        camera = new OrthographicCamera();
        personagens = new ArrayList<Personagem>();
        inimigos = new ArrayList<Personagem>();

        tempoAcumuladoMorcego = 0f;
        tempoAcumuladoDragao = 0f;

    }

    private void verificaSpawnMorcego(float delta){
        float intervaloSpawnMorcego = 5f;
        tempoAcumuladoMorcego += delta;

        if (tempoAcumuladoMorcego >= intervaloSpawnMorcego) {
    
            Morcego novoInimigo = criadorDePersonagens.criarMorcego(player);
            inimigos.add(novoInimigo);
            personagens.add(novoInimigo); // também adiciona na lista geral para renderizar
    
            tempoAcumuladoMorcego = 0f;
        }
        
    }
    
    private void verificaSpawnDragao(float delta){
        float intervaloSpawnDragao = 7f;
        tempoAcumuladoDragao += delta;

        if (tempoAcumuladoDragao >= intervaloSpawnDragao) {
    
            Dragao novoInimigo = criadorDePersonagens.criarDragao(player);
            inimigos.add(novoInimigo);
            personagens.add(novoInimigo); // também adiciona na lista geral para renderizar
    
            tempoAcumuladoDragao = 0f;
        }
    }

    private void verificaSpawnInimigo(float delta) {
        verificaSpawnMorcego(delta);
        verificaSpawnDragao(delta);
    }

    private void configuraCamera() {
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    }

    private void atualizaCamera() {
        camera.position.set(
                player.getPosicaoX() + player.getTexture().getWidth() / 2f,
                player.getPosicaoY() + player.getTexture().getHeight() / 2f,
                0);
        camera.update();
    }

    private void atualizaPersonagens() {
        for (Personagem personagem : personagens) {
            personagem.atualiza();
        }
    }

    private void desenhaPersonagens(SpriteBatch batch) {
        for (Personagem personagem : personagens) {
            personagem.desenha(batch);
        }
    }

    @Override
    public void show() {
        inicializaElementos();
        configuraCamera();
        player = criadorDePersonagens.criarPlayer();
        personagens.add(player);
    }
    
    private void limpaTela() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    private void atualizaColisoes() {
        for (Personagem personagem : personagens) {
            if (personagem != player) {
                gerenciadorDeColisoes.verificarColisao(player, personagem);
            }
        }
    }
    
    @Override
    public void render(float delta) {
        limpaTela();
        atualizaCamera();
        
        mapaPrimeiraFase.render(camera);
        verificaSpawnInimigo(delta);
        atualizaPersonagens();
        atualizaColisoes();
        // Garante que o batch use a matriz de projeção da câmera
        gameRunner.batch.setProjectionMatrix(camera.combined);
        gameRunner.batch.begin();
        desenhaPersonagens(gameRunner.batch);
        player.atualizarProjeteis(gameRunner.batch);
        gameRunner.batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

}
