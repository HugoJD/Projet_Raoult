package com.example.projetosmani;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
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
        canvas.translate((this.getWidth() - Map.DISPLAYED_TILES * Map.TILE_SIZE) / 2, 0.f);
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
        Doctor doctor = map.getDoctor();

        canvas.translate(0.f, -map_h * Map.TILE_SIZE);
        canvas.translate(doctor.getPosx() * Map.TILE_SIZE, doctor.getPosy() * Map.TILE_SIZE);
        d = map.getImage('D');
        d.draw(canvas);
        Log.d("Print", "doDraw: DR DONE" + doctor.getPosx());

        canvas.translate(-doctor.getPosx() * Map.TILE_SIZE, -doctor.getPosy() * Map.TILE_SIZE);
        canvas.translate(this.getWidth()/2 - 2 * Map.ARROW_SIZE,  this.getHeight()* 2 / 3);
        d = map.getImage('1');
        d.draw(canvas);
        canvas.translate(-Map.ARROW_SIZE, Map.ARROW_SIZE);
        d = map.getImage('4');
        d.draw(canvas);
        canvas.translate(2 * Map.ARROW_SIZE, 0.f);
        d = map.getImage('2');
        d.draw(canvas);
        canvas.translate(-Map.ARROW_SIZE, Map.ARROW_SIZE);
        d = map.getImage('3');
        d.draw(canvas);
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
        float arr = Map.ARROW_SIZE;
        float pos1X = this.getWidth()/2 + 24;
        float pos1Y = this.getHeight()* 2 / 3 + arr;
        if (event.getX() < pos1X && event.getX() > pos1X - arr) {
            if (event.getY() < pos1Y && event.getY() > pos1Y - arr) {
                map.getDoctor().moveDoc(1);
            }
        }
        pos1X -= arr;
        pos1Y += arr;
        if (event.getX() < pos1X && event.getX() > pos1X - arr) {
            if (event.getY() < pos1Y && event.getY() > pos1Y - arr) {
                map.getDoctor().moveDoc(4);
            }
        }
        pos1X += 2 * arr;
        if (event.getX() < pos1X && event.getX() > pos1X - arr) {
            if (event.getY() < pos1Y && event.getY() > pos1Y - arr) {
                map.getDoctor().moveDoc(2);
            }
        }
        pos1X -= arr;
        pos1Y += arr;
        if (event.getX() < pos1X && event.getX() > pos1X - arr) {
            if (event.getY() < pos1Y && event.getY() > pos1Y - arr) {
                map.getDoctor().moveDoc(3);
            }
        }
        return true;
    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée à la CREATION et MODIFICATION et ONRESUME de l'écran
    // nous obtenons ici la largeur/hauteur de l'écran en pixels
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int w, int h) {

    }
} // class GameView