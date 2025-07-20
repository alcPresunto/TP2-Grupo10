package com.jogo.ActRaiser.modelos.mapas;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MapaPrimeiraFase {
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;

    public MapaPrimeiraFase() {
        mapa = new TmxMapLoader().load("Mapa1.tmx"); // nome do seu arquivo .tmx
        renderer = new OrthogonalTiledMapRenderer(mapa);
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        mapa.dispose();
        renderer.dispose();
    }
}