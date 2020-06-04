package com.example.projetosmani;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;

public class MainActivity extends Activity {

    private GameView gameView;

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
    }

} // class MainActivity