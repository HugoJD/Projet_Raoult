package com.example.projetosmani;

public class Doctor extends Personnages {

    // Constantes de direction (Nord, Sud, Est, Ouest)

    private int posx;
    private int posy;
    private Map map;

    public Doctor(int posx, int posy, Map map) {
        this.posx = posx;
        this.posy = posy;
        this.map = map;
    }

    int getPosx() { return posx; }

    int getPosy() { return posy; }


    public void displayDoc(){

    }

    public void moveDoc(int dir){
        switch (dir) {
            case 1:
                if (map.getDoctor().getPosy() > 0)
                    posy -= 1;
                break;
            case 2:
                if (map.getDoctor().getPosx() < map.getWidth() - 1)
                    posx += 1;
                break;
            case 3:
                if (map.getDoctor().getPosy() < map.getHeight() - 2)
                    posy += 1;
                break;
            case 4:
                if (map.getDoctor().getPosx() > 0)
                    posx -= 1;
                break;
            default :
                break;
        }
    }
}
