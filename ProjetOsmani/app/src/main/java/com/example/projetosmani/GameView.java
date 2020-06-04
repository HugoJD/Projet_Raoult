package com.example.projetosmani;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

// SurfaceView est une surface de dessin.
// référence : https://developer.android.com/reference/android/view/SurfaceView
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    // déclaration de l'objet définissant la boucle principale de déplacement et de rendu
    private GameLoopThread gameLoopThread;
    private Modele modele;
    private Map map;

    // création de la surface de dessin
    public GameView(Context context, Map map) {
        super(context);
        getHolder().addCallback(this);
        gameLoopThread = new GameLoopThread(this);

        this.map = map;

        // création du modèle
        modele = new Modele(this.getContext());

    }

    // Fonction qui "dessine" un écran de jeu
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void doDraw(Canvas canvas) {
        if(canvas==null) {return;}

        // on efface l'écran, en blanc
        canvas.drawColor(Color.WHITE);
        Drawable d = null;
        int h = 0;
        int map_h = map.getHeight();
        while (h < map_h) {
            int w = 0;
            int map_w = map.getWidth();
            while (w < map_w) {
                d = map.getImage(map.getCharAt(h,w));
                d.draw(canvas);
                canvas.translate(Map.TILE_SIZE,0);
                w += 1;
            }
            canvas.translate(-map_w * Map.TILE_SIZE,Map.TILE_SIZE);
            h += 1;
        }
        // A compléter avec le reste de notre affichage
    }

    // Fonction appelée par la boucle principale (gameLoopThread)
    // On gère ici le déplacement des objets
    public void update() {

    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée immédiatement après la création de l'objet SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // création du processus GameLoopThread si cela n'est pas fait
        if(gameLoopThread.getState()==Thread.State.TERMINATED) {
            gameLoopThread=new GameLoopThread(this);
        }
        gameLoopThread.setRunning(true);
        gameLoopThread.start();
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée juste avant que l'objet ne soit détruit.
    // on tente ici de stopper le processus de gameLoopThread
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameLoopThread.setRunning(false);
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    // Gère les touchés sur l'écran
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Fonction principale d'interprétation
        return true;
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {

    }
} // class GameView