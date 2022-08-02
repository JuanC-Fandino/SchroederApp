package com.example.juank.schroederapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class Room_modes extends AppCompatActivity {

    public int c=340/2,i=1,contador2=1,contador3=1;

    public float Largo,Ancho,Alto,frecsch,frec=0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_modes);

        Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla_modos);

        SharedPreferences prefs = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE);
        frecsch = prefs.getFloat("Sch", 0f);
        Log.d("v",Float.toString(frecsch));
        Largo = prefs.getFloat("Largo", 10f);
        Log.d("v",Float.toString(Largo));
        Ancho = prefs.getFloat("Ancho", 8f);
        Log.d("v",Float.toString(Ancho));
        Alto = prefs.getFloat("Alto", 2.3f);
        Log.d("v",Float.toString(Alto));

        ArrayList<String> ax = new ArrayList<>();
        ax.add("Axiales");
        ax.add("    ");
        tabla.agregarFilaTabla(ax);

        while (frec<frecsch){

            frec =c*(float) Math.sqrt((float) Math.pow(i/Largo,2));
            if(frec<frecsch){
                ArrayList<String> Axiales = new ArrayList<>();
                String z ="("+Integer.toString(i)+",0,0)";
                Axiales.add(z);
                Axiales.add(Float.toString(frec));
                tabla.agregarFilaTabla(Axiales);
            }



            i++;

        }

        ArrayList<String> q = new ArrayList<>();
        q.add("    ");
        q.add("    ");
        tabla.agregarFilaTabla(q);


        Log.d("v","q");
        frec=0f;
        i=1;
        while (frec<frecsch){

            frec =c*(float) Math.sqrt((float) Math.pow(i/Ancho,2));
            if(frec<frecsch){
                ArrayList<String> Axiales = new ArrayList<>();
                String z ="(0,"+Integer.toString(i)+",0)";
                Axiales.add(z);
                Axiales.add(Float.toString(frec));
                tabla.agregarFilaTabla(Axiales);
            }

            i++;

        }

        ArrayList<String> r = new ArrayList<>();
        r.add("    ");
        r.add("    ");
        tabla.agregarFilaTabla(r);

        Log.d("v","r");
        frec=0f;
        i=1;
        while (frec<frecsch){
            frec =c*(float) Math.sqrt((float) Math.pow(i/Alto,2));
            if(frec<frecsch){
                ArrayList<String> Axiales = new ArrayList<>();
                String z ="(0,0,"+Integer.toString(i)+")";
                Axiales.add(z);
                Axiales.add(Float.toString(frec));
                tabla.agregarFilaTabla(Axiales);
            }
            i++;

        }

        ArrayList<String> ob = new ArrayList<>();
        ob.add("Tangenciales");
        ob.add("    ");
        tabla.agregarFilaTabla(ob);

        frec=0f;
        i=1;
        contador2=1;
        while (contador2<10){


            while (frec<frecsch) {
                frec = c * (float) Math.sqrt((float) Math.pow(i / Largo, 2) + (float) Math.pow(contador2 / Ancho, 2));

                if(frec<frecsch){
                    ArrayList<String> Tangenciales = new ArrayList<>();
                    String z = "(" + Integer.toString(i)+"," +Integer.toString(contador2) +",0)";
                    Tangenciales.add(z);
                    Tangenciales.add(Float.toString(frec));
                    tabla.agregarFilaTabla(Tangenciales);
                }



                i++;


            }

            contador2++;
            i=1;
            frec=0;

        }



        frec=0f;
        i=1;
        contador2=1;

        while (contador2<5){


            while (frec<frecsch) {

                frec = c * (float) Math.sqrt((float) Math.pow(i / Ancho, 2) + (float) Math.pow(contador2 / Alto, 2));
                if(frec<frecsch){
                    ArrayList<String> Tangenciales = new ArrayList<>();
                    String z = "(0," + Integer.toString(i)+"," +Integer.toString(contador2)+ ")";
                    Tangenciales.add(z);
                    Tangenciales.add(Float.toString(frec));
                    tabla.agregarFilaTabla(Tangenciales);
                }

                i++;

            }


            contador2++;
            i=1;
            frec=0;


        }

        frec=0f;
        i=1;
        contador2=1;
        while (contador2<5){

            while (frec<frecsch) {

                frec = c * (float) Math.sqrt((float) Math.pow(i / Largo, 2) + (float) Math.pow(contador2 / Alto, 2));
                if(frec<frecsch){
                    ArrayList<String> Tangenciales = new ArrayList<>();
                    String z = "(" + Integer.toString(i)+",0," +Integer.toString(contador2)+ ")";
                    Tangenciales.add(z);
                    Tangenciales.add(Float.toString(frec));
                    tabla.agregarFilaTabla(Tangenciales);

                }


                i++;

            }

            contador2++;
            i=1;
            frec=0;

        }


        ArrayList<String> oblicuos = new ArrayList<>();
        oblicuos.add("Oblicuos");
        oblicuos.add("    ");
        tabla.agregarFilaTabla(oblicuos);

        frec=0f;
        i=1;
        contador2=1;
        contador3=1;

        while(contador3<3) {

            while (contador2 < 3) {

                while (frec < frecsch) {
                    frec = c * (float) Math.sqrt((float) Math.pow(i / Largo, 2) + (float) Math.pow(contador2 / Ancho, 2)+ (float) Math.pow(contador3 / Alto, 2));
                    if(frec<frecsch){
                        ArrayList<String> Oblicuos = new ArrayList<>();
                        String z = "(" + Integer.toString(i) + "," + Integer.toString(contador2) + ","+Integer.toString(contador3)+")";
                        Oblicuos.add(z);
                        Oblicuos.add(Float.toString(frec));
                        tabla.agregarFilaTabla(Oblicuos);
                    }

                    i++;
                }

                contador2++;
                i = 1;
                frec = 0;

            }
            contador2=1;
            contador3++;
            i = 1;
            frec = 0;

        }






}}
