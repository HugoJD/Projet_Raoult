package com.example.myapplication;

import android.graphics.drawable.shapes.Shape;

public class Personnages {

    private float  posx ;
    private float  posy ;

    private int life ;
    private boolean death ;

    private float width ;
    private float height ;
    private Shape avatar ;

    private int movX ;
    private int movY ;
    private int speed ;

    public Personnages(){
        super();
    }
    public Personnages(float posx,float posy,float width, float height, int life, int speed) {

        this.posx = posx ;
        this.posy = posy ;

        this.width = width ;
        this.height = height ;

        this.life = life ;
        this.speed = speed ;

    }

    public float getX(){
        return this.posx ;
    }

    public float getY(){
        return this.posy ;
    }

    public void setX(float posx){
        this.posx = posx ;
    }

    public void setY(float posy){
        this.posy = posy ;
    }

    public void setLife(int life){
        this.life = life ;
    }


}
