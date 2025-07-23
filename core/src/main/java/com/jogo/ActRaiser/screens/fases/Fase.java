package com.jogo.ActRaiser.screens.fases;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.logica.CriadorDePersonagens;
import com.jogo.ActRaiser.logica.GerenciadorDeColisoes;
import com.jogo.ActRaiser.modelos.mapas.MapaBase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Inimigo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.screens.HUD;
import com.jogo.ActRaiser.screens.intermediarias.TelaDerrota;

public abstract class Fase implements Screen {
    protected final GameRunner gameRunner;

    protected MapaBase mapa;
    protected OrthographicCamera camera;
    protected HUD hud;
    protected int pontuacaoParaVencer;

    protected CriadorDePersonagens criadorDePersonagens;
    protected GerenciadorDeColisoes gerenciadorDeColisoes;

    protected ArrayList<Personagem> personagens;
    protected ArrayList<Inimigo> inimigos;
    protected Player jogador;

    protected ShapeRenderer desenhadorDeFormas;

    public Fase(GameRunner gameRunner, int pontuacaoParaVencer) {
        this.gameRunner = gameRunner;
        this.pontuacaoParaVencer = pontuacaoParaVencer;
    }

    @Override
    public void show() {
        inicializaBase();
        configuraCamera();
        jogador = criaPlayer();
        if (jogador != null) {
            personagens.add(jogador);
        }
    }

    @Override
    public void render(float tempoDecorrido) {
        limpaTela();
        atualizaCamera();
        if (mapa != null) {
            mapa.render(camera);
        }
        verificaSpawnInimigos(tempoDecorrido);
        atualizaPersonagens();
        atualizaColisoes();
        removerInimigosMortos();
        checaFimDeJogo();
        if (gameRunner.batch != null) {
            renderizaJogo(gameRunner.batch);
            renderizaHUD(gameRunner.batch);
        }
        desenhaHitboxes();
    }

    @Override
    public void dispose() {
        if (hud != null)
            hud.dispose();
        if (mapa != null)
            mapa.dispose();
        if (desenhadorDeFormas != null)
            desenhadorDeFormas.dispose();
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
    public void resize(int largura, int altura) {
    }

    protected void inicializaBase() {
        mapa = criaMapa();
        camera = new OrthographicCamera();
        hud = new HUD(gameRunner);
        criadorDePersonagens = new CriadorDePersonagens();
        gerenciadorDeColisoes = new GerenciadorDeColisoes();
        personagens = new ArrayList<>();
        inimigos = new ArrayList<>();
        desenhadorDeFormas = new ShapeRenderer();
    }

    protected abstract MapaBase criaMapa();

    protected abstract Player criaPlayer();

    protected abstract void verificaSpawnInimigos(float delta);

    protected abstract void fimDeJogo();

    protected void checaFimDeJogo() {
        if (jogador == null)
            return;
        if (jogador.getPontosVida() <= 0) {
            gameRunner.setScreen(new TelaDerrota(gameRunner));
        } else if (jogador.getPontuacao() >= getPontuacaoParaVencer()) {
            fimDeJogo();
        }
    }

    protected int getPontuacaoParaVencer() {
        return pontuacaoParaVencer;
    }

    protected void configuraCamera() {
        float largura = Gdx.graphics.getWidth() / 2f;
        float altura = Gdx.graphics.getHeight() / 2f;
        camera.setToOrtho(false, largura, altura);
    }

    protected void atualizaCamera() {
        if (jogador == null || camera == null)
            return;
        float posicaoX = calculaPosicaoCameraX();
        float posicaoY = calculaPosicaoCameraY();
        camera.position.set(posicaoX, posicaoY, 0);
        camera.update();
    }

    protected float calculaPosicaoCameraX() {
        if (jogador == null || mapa == null)
            return 0;
        TextureRegion quadroAtual = jogador.getFrameAtual();
        if (quadroAtual == null)
            return 0;
        float centroX = jogador.getPosicaoX() + quadroAtual.getRegionWidth() / 2f;
        float minimo = camera.viewportWidth / 2f;
        float maximo = mapa.getLarguraMapa() - minimo;
        return Math.max(minimo, Math.min(centroX, maximo));
    }

    protected float calculaPosicaoCameraY() {
        if (jogador == null || mapa == null)
            return 0;
        TextureRegion quadroAtual = jogador.getFrameAtual();
        if (quadroAtual == null)
            return 0;
        float centroY = jogador.getPosicaoY() + quadroAtual.getRegionHeight() / 2f;
        float minimo = camera.viewportHeight / 2f;
        float maximo = mapa.getAlturaMapa() - minimo;
        return Math.max(minimo, Math.min(centroY, maximo));
    }

    protected void atualizaPersonagens() {
        if (personagens == null)
            return;
        for (Personagem personagem : personagens) {
            if (personagem != null) {
                personagem.atualiza();
                limitarPosicaoNoMapa(personagem);
            }
        }
    }

    protected void limitarPosicaoNoMapa(Personagem personagem) {
        if (personagem == null || mapa == null)
            return;
        float posicaoX = personagem.getPosicaoX();
        float posicaoY = personagem.getPosicaoY();
        float largura = personagem.getHitbox() != null ? personagem.getHitbox().getWidth() : 0;
        float altura = personagem.getHitbox() != null ? personagem.getHitbox().getHeight() : 0;
        if (largura <= 0 || altura <= 0)
            return;
        personagem.setPosicaoX(Math.max(0, Math.min(posicaoX, mapa.getLarguraMapa() - largura)));
        personagem.setPosicaoY(Math.max(0, Math.min(posicaoY, mapa.getAlturaMapa() - altura)));
    }

    protected void atualizaColisoes() {
        if (jogador == null || inimigos == null || gerenciadorDeColisoes == null)
            return;
        for (Inimigo inimigo : inimigos) {
            if (inimigo != null) {
                gerenciadorDeColisoes.verificarColisaoJogadorComObjeto(jogador, inimigo);
                gerenciadorDeColisoes.verificarColisaoProjetilComInimigo(jogador, inimigo);
            }
        }
    }

    protected void removerInimigosMortos() {
        if (inimigos == null || personagens == null)
            return;
        inimigos.removeIf(inimigo -> inimigo == null || !inimigo.estaAtivo());
        personagens.removeIf(personagem -> personagem instanceof Inimigo && !((Inimigo) personagem).estaAtivo());
    }

    protected void desenhaPersonagens(SpriteBatch batch) {
        if (personagens == null || batch == null)
            return;
        for (Personagem personagem : personagens) {
            if (personagem != null) {
                personagem.desenha(batch);
            }
        }
    }

    protected void desenhaHitboxes() {
        if (desenhadorDeFormas == null || camera == null || jogador == null || inimigos == null)
            return;
        desenhadorDeFormas.setProjectionMatrix(camera.combined);
        desenhadorDeFormas.begin(ShapeRenderer.ShapeType.Line);
        desenhadorDeFormas.setColor(Color.RED);
        desenhaHitbox(jogador);
        for (Personagem inimigo : inimigos) {
            if (inimigo != null) {
                desenhaHitbox(inimigo);
            }
        }
        desenhadorDeFormas.end();
    }

    private void desenhaHitbox(Personagem personagem) {
        if (personagem == null || personagem.getHitbox() == null)
            return;
        desenhadorDeFormas.rect(
                personagem.getHitbox().getX(),
                personagem.getHitbox().getY(),
                personagem.getHitbox().getWidth(),
                personagem.getHitbox().getHeight());
    }

    protected void limpaTela() {
        Gdx.gl.glClearColor(0.23f, 0.35f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void atualizaHUD(SpriteBatch batch) {
        if (hud != null && jogador != null) {
            hud.desenhar(batch, jogador);
        }
    }

    protected void renderizaJogo(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        desenhaPersonagens(batch);
        if (jogador != null) {
            jogador.atualizarProjeteis();
            jogador.desenharProjeteis(batch);
        }
        batch.end();
    }

    protected void renderizaHUD(SpriteBatch batch) {
        batch.setProjectionMatrix(gameRunner.cameraHUD.combined);
        batch.begin();
        atualizaHUD(batch);
        batch.end();
    }
}
