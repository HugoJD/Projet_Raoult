package com.example.projetosmani;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import java.io.IOException;

public class MainActivity extends Activity {

    private GameView gameView;
    private Button BoutonGauche;
    private Button BoutonDroite;
    private Button BoutonHaut;
    private Button BoutonBas;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssetManager as = getAssets();
        Map m = null;
        try {
            m = new Map(as, "Test.map", getResources());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Print", "onCreate: " + m.toString());
        // On cré un objet "GameView" qui est le code principal du jeu
        gameView=new GameView(this, m);

        // et on l'affiche.
        setContentView(gameView);
        setContentView(R.layout.activity_main);
        
        BoutonGauche = (Button) findViewById(R.id.gauche);
        BoutonDroite = (Button) findViewById(R.id.droite);
        BoutonBas = (Button) findViewById(R.id.bas);
        BoutonHaut = (Button) findViewById(R.id.haut);

        BoutonGauche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on appelle la méthode pour que Raoult aille à gauche;
                //raoult.setPosition(raoult.getX()-1; raoult.getY());

            }
        });

        BoutonDroite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on appelle la méthode pour que Raoult aille à droite;
                //raoult.setPosition(raoult.getX()+1; raoult.getY());
            }
        });

        BoutonBas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on appelle la méthode pour que Raoult aille en bas;
                //raoult.setPosition(raoult.getX(); raoult.getY()-1);
            }
        });

        BoutonHaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on appelle la méthode pour que Raoult aille en haut;
                //raoult.setPosition(raoult.getX(); raoult.getY()+1);
            }
        });

    }

} // class MainActivity