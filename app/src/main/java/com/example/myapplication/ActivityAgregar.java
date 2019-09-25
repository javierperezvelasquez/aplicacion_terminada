package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;

public class ActivityAgregar extends AppCompatActivity {

    private EditText et_rut, et_nombre, et_apoderado, et_direccion, et_telefono, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        et_rut = (EditText) findViewById(R.id.txt_rut);
        et_nombre = (EditText) findViewById(R.id.txt_nombres);
        et_apoderado = (EditText) findViewById(R.id.txt_apoderado);
        et_direccion = (EditText) findViewById(R.id.txt_direccion);
        et_telefono = (EditText) findViewById(R.id.txt_numtel);
        et_precio = (EditText) findViewById(R.id.txt_precio);


    }
    //Méotdo para dar de alta a las personas

    public void Registrar(View view) {
        IngresoSQLite admin = new IngresoSQLite(this, "administracion", null, 1);
        //abrir base de datos en modo lectura y escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //variables para guardar datos
        String rut = et_rut.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apoderado = et_apoderado.getText().toString();
        String direccion = et_direccion.getText().toString();
        String telefono = et_telefono.getText().toString();
        String precio = et_precio.getText().toString();

        //si esta variable es diferente de vacio
        if (!rut.isEmpty() && !nombre.isEmpty() && !apoderado.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("rut", rut);
            registro.put("nombre", nombre);
            registro.put("apoderado", apoderado);
            registro.put("direccion", direccion);
            registro.put("telefono", telefono);
            registro.put("precio", precio);

            BaseDeDatos.insert("personas", null, registro);

            BaseDeDatos.close();
            et_rut.setText("");
            et_nombre.setText("");
            et_apoderado.setText("");
            et_direccion.setText("");
            et_telefono.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Se a registrado con exito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor , rellene los campos", Toast.LENGTH_SHORT).show();
        }
    }


    //Método para buscar una persona
    public void Buscar(View view) {
        IngresoSQLite admin = new IngresoSQLite(this, "administracion", null, 1);
        //abrir base de datos en modo lectura y escritura
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();

        if (!rut.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select  nombre, apoderado, direccion, telefono ,precio from personas where rut =" + rut, null);

            if (fila.moveToFirst()) {
                et_nombre.setText(fila.getString(0));
                et_apoderado.setText(fila.getString(1));
                et_direccion.setText(fila.getString(2));
                et_telefono.setText(fila.getString(3));
                et_precio.setText(fila.getString(4));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this, "Persona no registrada", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el rut de la persona", Toast.LENGTH_SHORT).show();
        }
    }


    //Método para eliminar un persona
    public void Eliminar(View view) {
        IngresoSQLite admin = new IngresoSQLite(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();

        if (!rut.isEmpty()) {

            int cantidad = BaseDatabase.delete("personas", "rut=" + rut, null);
            BaseDatabase.close();

            et_rut.setText("");
            et_nombre.setText("");
            et_apoderado.setText("");
            et_direccion.setText("");
            et_telefono.setText("");
            et_precio.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "Persona eliminada ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La persona no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el rut de la persona", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para modificar persona
    public void Modificar(View view) {
        IngresoSQLite admin = new IngresoSQLite(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String rut = et_rut.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apoderado = et_apoderado.getText().toString();
        String direccion = et_direccion.getText().toString();
        String telefono = et_telefono.getText().toString();
        String precio = et_precio.getText().toString();

        if (!rut.isEmpty() && !nombre.isEmpty() && !apoderado.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty() && !precio.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("rut", rut);
            registro.put("nombre", nombre);
            registro.put("apoderado", apoderado);
            registro.put("direccion", direccion);
            registro.put("telefono", telefono);
            registro.put("precio", precio);

            int cantidad = BaseDatabase.update("personas", registro, "rut=" + rut, null);
            BaseDatabase.close();

            if (cantidad == 1) {
                Toast.makeText(this, "Persona modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La persona no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Por favor , llene los campos", Toast.LENGTH_SHORT).show();
        }
    }




    //metodo el boton volver
        public void Volver(View view ){
            Intent volver = new Intent( this,MainActivity.class);
            startActivity(volver);

        }
}
