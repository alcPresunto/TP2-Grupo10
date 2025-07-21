package com.jogo.ActRaiser.modelos.mapas;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class MapaBase {
    protected TiledMap mapa;
    protected OrthogonalTiledMapRenderer renderer;

    public MapaBase() {
        carregarMapa();
        criarRenderer();
    }

    protected abstract void carregarMapa();

    private void criarRenderer() {
        renderer = new OrthogonalTiledMapRenderer(mapa);
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        if (mapa != null)
            mapa.dispose();
        if (renderer != null)
            renderer.dispose();
    }

    public abstract float getLarguraMapa();

    public abstract float getAlturaMapa();
}
