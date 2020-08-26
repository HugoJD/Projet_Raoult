package com.example.projetosmani;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Map {

    public static final int TILE_SIZE = 32;

    private int width;
    private int height;
    private char map[][];
    private Resources resources;
    private HashMap<Character, Drawable> images;
    private Doctor doctor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Map(AssetManager assetManager, String nomFichier, Resources resources) throws IOException {
        InputStream inputStream = assetManager.open(nomFichier);
        images = new HashMap<>();
        this.resources = resources;
        BufferedReader bufferedreader = null;
        if (!nomFichier.matches(".*\\.map")) {
            System.err.println("Mauvais nom de fichier");
            System.exit(1);
        }
        try {
            bufferedreader = new BufferedReader(new InputStreamReader(inputStream));
            String strCurrentLine = bufferedreader.readLine();
            height = Integer.valueOf(strCurrentLine);
            strCurrentLine = bufferedreader.readLine();
            width = Integer.valueOf(strCurrentLine);
            map = new char[height][];

            strCurrentLine = bufferedreader.readLine();
            int docPosX = Integer.valueOf(strCurrentLine);
            strCurrentLine = bufferedreader.readLine();
            int docPosY = Integer.valueOf(strCurrentLine);
            doctor = new Doctor(docPosX, docPosY);
            addKey('D');

            int i = 0;
            while ((strCurrentLine = bufferedreader.readLine()) != null) {
                map[i] = new char[width];
                int j = 0;
                while (j < strCurrentLine.length()) {
                    char c = strCurrentLine.charAt(j);
                     map[i][j] = c;
                     if (!images.containsKey(c)) {
                         addKey(c);
                     }
                     j += 1;
                }
                i += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedreader != null)
                    bufferedreader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Doctor getDoctor() { return doctor; }

    public Drawable getImage(char c) {
        return images.get(c);
    }

    public char getCharAt(int x, int y) {
        return map[x][y];
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addKey(char c) {
        Drawable d = null;
        switch (c) {
            case 'M':
                d = resources.getDrawable(R.drawable.buisson2, null);
                d.setBounds(0,0,TILE_SIZE,TILE_SIZE);
                images.put('M', d);
                break;
            case 'S':
                d = resources.getDrawable(R.drawable.solvert, null);
                d.setBounds(0,0,TILE_SIZE,TILE_SIZE);
                images.put('S', d);
                break;
            case 'D':
                d = resources.getDrawable(R.drawable.facetest, null);
                d.setBounds(0,0,32,41);
                images.put('D', d);
                break;
            default:
                break;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        String str = "";
        str = str + "Dimensions de la carte:\nHeigth: " + height + "\nWidth: " + width + "\n";
        int i = 0;
        while (i < map.length) {
            int j = 0;
            while (j < map[i].length) {
                str += map[i][j] + " ";
                j += 1;
            }
            str += "\n";
            i += 1;
        }
        return str;
    }
}