package com.vvkk.game.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.vvkk.game.Screen.PlayScreen;
import com.vvkk.game.hulk;

public class hulkman extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion h1s;


    public hulkman(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("h1"));
        this.world =world;
        definehulk();
        h1s=new TextureRegion(getTexture(),127,1,116,157);
        setBounds(0,0,200/hulk.PPM,200/hulk.PPM);
        setRegion(h1s);
    }

        public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/3);


        }

    public void definehulk(){
        BodyDef bdef =new BodyDef();
        bdef.position.set(32/hulk.PPM,32/hulk.PPM);
        bdef.type =BodyDef.BodyType.DynamicBody;
        b2body =world.createBody(bdef);

        FixtureDef fdef =new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(50/hulk.PPM);
        fdef.shape=shape;
        b2body.createFixture(fdef);

    }
}
