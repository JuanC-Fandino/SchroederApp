package com.example.juank.schroederapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TableLayout;
import com.example.juank.schroederapp.SQLiteDBHelper;
import com.example.juank.schroederapp.MaterialesCaracteristicas;

import java.util.ArrayList;
import java.util.List;

public class Materiales extends AppCompatActivity {

    SQLiteDBHelper dbHelper = null;
    public static Activity fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiales);


        dbHelper = new SQLiteDBHelper(this);
        fa=this;

        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        List<MaterialesCaracteristicas> matCatList = dbHelper.getAllMaterials();

        for (int i = 0; i < matCatList.size(); i++) {
            ArrayList<String> elementos = new ArrayList<>();
            MaterialesCaracteristicas matCatVO = matCatList.get(i);
            elementos.add(matCatVO.getMaterialName());
            elementos.add(Float.toString(matCatVO.getAlfa125()));
            elementos.add(Float.toString(matCatVO.getAlfa250()));
            elementos.add(Float.toString(matCatVO.getAlfa500()));
            elementos.add(Float.toString(matCatVO.getAlfa1000()));
            elementos.add(Float.toString(matCatVO.getAlfa2000()));
            elementos.add(Float.toString(matCatVO.getAlfa4000()));
            tabla.agregarFilaTabla(elementos)
            ;
        }


    }

    @Override
    public void onResume(){
        super.onResume();

        Log.d("v", "onResume");


    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d("v", "onDestroy");
    }

    public void onClick(View v) {

       int ID = v.getId();


        if (ID == R.id.button3) {

            Intent intent = new Intent(getApplicationContext(), Materials_Base.class);
            startActivity(intent);
            onStop();
        }


    }


}
