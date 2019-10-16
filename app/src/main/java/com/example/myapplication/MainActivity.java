package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {
    ImageView img;
    Bitmap bitmap;
    String URLimg = "https://autolab.com.co/wp-content/uploads/2019/02/Iconos-Atributos_Mesa-de-trabajo-1-copia-4.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);

        new GetImageFromURL(img).execute(URLimg);


    }
    //metodo el boton agregar
    public void Agregarcliente(View view) {
        Intent Agregarcliente = new Intent(this, ActivityAgregar.class);
        startActivity(Agregarcliente);

    }

    //metodo el boton acerca de
    public void Maps(View view) {
        Intent Maps = new Intent(this,MapsActivity .class);
        startActivity(Maps);

    }

        public class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView imgVie;

        public GetImageFromURL(ImageView imgV) {
            this.imgVie = imgV;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            bitmap = null;
            try {
                InputStream srt = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgVie.setImageBitmap(bitmap);


            //Poner icono en el action bar
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        }


    }
}
