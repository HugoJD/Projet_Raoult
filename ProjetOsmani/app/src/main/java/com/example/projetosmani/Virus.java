package com.example.projetosmani;

public class Virus extends Personnages {

    private float posx;
    private float posy;
    private float width;
    private float height;
    private int life;
    private int speed;

    public Virus(float posx, float posy, float width, float height, int life, int speed) {
        super(posx, posy, width, height, life, speed);
    }
}
