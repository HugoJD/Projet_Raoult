package com.example.projetosmani;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

public class Modele
{
    // contexte de l'application Android
    // il servira à accéder aux ressources, dont l'image de la balle
    private final Context mContext;

    public Modele(final Context c)
    {
        mContext = c;
    }

    // permet de faire une image
    public BitmapDrawable setImage(final Context c, final int ressource, final int w, final int h)
    {
        return null;
    }

    // on dessine le modèle
    public void draw(Canvas canvas)
    {

    }
} // public class Balle