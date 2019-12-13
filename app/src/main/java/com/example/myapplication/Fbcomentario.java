package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fbcomentario extends AppCompatActivity {
    EditText nom, com;
    Button btn;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbcomentario);

        nom = (EditText) findViewById(R.id.editText);
        com = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button9);

        FirebaseDatabase DATOS = FirebaseDatabase.getInstance();
        mDatabase = DATOS.getReference(MisRefencias.usuario_referencia);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona miPersona = new Persona(nom.getText().toString().toUpperCase().trim(), com.getText().toString().toUpperCase().trim());
                mDatabase.child("").push().setValue(miPersona);
                validar();
            }
        });

    }

    public void Volver2(View view) {
        Intent volver2 = new Intent(this, MainActivity.class);
        startActivity(volver2);
    }


    public void validar() {
        nom.setError(null);
        com.setError(null);

        String nombre = nom.getText().toString();
        String comentario = com.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            nom.setError("Rellene el campo nombre");
            nom.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(comentario)) {
            com.setError("Rellene el campo comentario");
            com.requestFocus();
            return;

        }

            Toast.makeText(getApplicationContext(), "El comentario se ingreso correctamente", Toast.LENGTH_SHORT).show();

        }

    }

