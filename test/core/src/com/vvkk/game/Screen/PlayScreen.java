package com.vvkk.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vvkk.game.hulk;
import com.vvkk.game.scenes.Hud;
import com.vvkk.game.sprite.hulkman;
import com.vvkk.game.tools.b2world;

public class PlayScreen implements Screen {

    private hulk game;
    private Hud hud;
    private TmxMapLoader maploaader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private World world;
    private Box2DDebugRenderer b2dr;
    private hulkman player;
    private TextureAtlas atlas;




    public  PlayScreen(hulk game){
        atlas =new TextureAtlas("vk.pack");
         this.game=game;
        gamecam=new OrthographicCamera();
        gameport =new FitViewport(hulk.V_WIDTH/hulk.PPM,hulk.V_HEIGHT/hulk.PPM,gamecam);
//        hud=new Hud(game.batch);

        maploaader =new TmxMapLoader();

        map=maploaader.load("background.tmx");
        renderer =new OrthogonalTiledMapRenderer(map,1/hulk.PPM);
        gamecam.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2,0);
        world=new World(new Vector2(0,-15),true);

        b2dr=new Box2DDebugRenderer();
        new b2world(world,map );
        player = new hulkman(world,this);


    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void show() {

    }
    public void handleInput(float dt){

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0,5f),player.b2body.getWorldCenter(),true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <=5)
            player.b2body.applyLinearImpulse(new Vector2(.3f,0f),player.b2body.getWorldCenter(),true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-.3f,0f),player.b2body.getWorldCenter(),true);



    }
    public void update(float dt){
        handleInput(dt);
       world.step(1/60f,6,2);
      player.update(dt);
       gamecam.position.x = player.b2body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);

    }
    @Override
    public void render(float delta) {
        update(delta);
//     Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
//        game.batch.setProjectionMatrix(hud.stage.getCamera().view);
//        hud.stage.draw();
        b2dr.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

        gameport.update(width,height);
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
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();


    }
}
