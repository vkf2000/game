package com.vvkk.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vvkk.game.hulk;

public class b2world {
    public b2world(World world,TiledMap map){

        BodyDef bdef= new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fdef =new FixtureDef();
        Body body;


//        stone
        for(MapObject object :map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2 )/ hulk.PPM,( rect.getY() + rect.getHeight() / 2 )/ hulk.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / hulk.PPM, rect.getHeight() / 2 / hulk.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

//        grass
//        for(MapObject object :map.getLayers().get().getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rec = ((RectangleMapObject) object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set(rec.getX() + rec.getWidth()/2 , rec.getY() + rec.getHeight() /2);
//            body = world.createBody(bdef);
//            shape.setAsBox(rec.getWidth() / 2/hulk.PPM, rec.getHeight() / 2/hulk.PPM);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//
//        }
//        ground
            for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {

                Rectangle ret = ((RectangleMapObject) object).getRectangle();

                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((ret.getX() + ret.getWidth() / 2)/hulk.PPM, (ret.getY() + ret.getHeight() / 2) / hulk.PPM);
                body = world.createBody(bdef);
                shape.setAsBox(ret.getWidth() / 2 / hulk.PPM, ret.getHeight()/ 2 / hulk.PPM);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
        }
    }

