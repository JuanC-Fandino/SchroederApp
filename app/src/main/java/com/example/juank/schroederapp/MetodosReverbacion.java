package com.example.juank.schroederapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;


import com.example.juank.schroederapp.SQLiteDBHelper;
import com.example.juank.schroederapp.MaterialesCaracteristicas;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static com.github.mikephil.charting.components.Legend.LegendPosition.LEFT_OF_CHART_INSIDE;
import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART;
import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART_INSIDE;



public class MetodosReverbacion extends AppCompatActivity  implements View.OnClickListener {

    public double Rt_Sabine_125,Rt_Sabine_250,Rt_Sabine_500,Rt_Sabine_1000,Rt_Sabine_2000,Rt_Sabine_4000,Rt_norris_125,Rt_norris_250,Rt_norris_500,Rt_norris_1000,Rt_norris_2000,Rt_norris_4000,Rt_milli_125,Rt_milli_250,Rt_milli_500,Rt_milli_1000,Rt_milli_2000,Rt_milli_4000,Rt_fitz_125,Rt_fitz_250,Rt_fitz_500,Rt_fitz_1000,Rt_fitz_2000,Rt_fitz_4000,alfamid;
    public double denominador_milli_125,denominador_milli_250,denominador_milli_500,denominador_milli_1000,denominador_milli_2000,denominador_milli_4000,RTX_125,RTY_125,RTZ_125,RTX_250,RTY_250,RTZ_250,RTX_500,RTY_500,RTZ_500,RTX_1000,RTY_1000,RTZ_1000,RTX_2000,RTY_2000,RTZ_2000,RTX_4000,RTY_4000,RTZ_4000,Rt_arau_125,Rt_arau_250,Rt_arau_500,Rt_arau_1000,Rt_arau_2000,Rt_arau_4000;
    public float Largo,Ancho,Alto,a125,a250,a500,a1000,a2000,a4000,St,vol,Sz,Sx,Sy,alfax_125,alfax_250,alfax_500,alfax_1000,alfax_2000,alfax_4000,alfay_125,alfay_250,alfay_500,alfay_1000,alfay_2000,alfay_4000,alfaz_125,alfaz_250,alfaz_500,alfaz_1000,alfaz_2000,alfaz_4000;
    public double a_125,a_250,a_500,a_1000,a_2000,a_4000,beta,d_125,d_250,d_500,d_1000,d_2000,d_4000,Rtf_125,Rtf_250,Rtf_500,Rtf_1000,Rtf_2000,Rtf_4000,Rti_125,Rti_250,Rti_500,Rti_1000,Rti_2000,Rti_4000;
    private static String STRING_EMPTY = "";
    double facthum_125,facthum_250,facthum_500,facthum_1000,facthum_4000,facthum_2000;
    SQLiteDBHelper dbHelper = null;
    String[] betas = {"Baja Difusión (-5)","Baja Difusión (-4)", "Difusión Regular (-3)", "Difusión Regular (-2)", "Difusión Buena (-1)", "Difusión Buena (0)", "Difusión Excelente (1)"};

    float [] Rt_Sabine=new float [6];
    float [] Rt_norris=new float [6];
    float [] Rt_milli=new float [6];
    float [] Rt_fitz=new float [6];
    float [] Rt_arau=new float [6];
    float [] Rti_arau=new float [6];
    float [] Rtf_arau=new float [6];

    public static Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_reverbacion);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fa=this;
        Spinner s =(Spinner) findViewById(R.id.spinner);
        s.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, betas));

    }

    public void creartabla(){

        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla2));
        tabla.agregarCabecera(R.array.cabecera_tabla_metodos);

            ArrayList<String> elementos = new ArrayList<>();

            elementos.add("Sabine");
            elementos.add(Double.toString(Rt_Sabine_125));
            elementos.add(Double.toString(Rt_Sabine_250));
            elementos.add(Double.toString(Rt_Sabine_500));
            elementos.add(Double.toString(Rt_Sabine_1000));
            elementos.add(Double.toString(Rt_Sabine_2000));
            elementos.add(Double.toString(Rt_Sabine_4000));
            tabla.agregarFilaTabla(elementos);

            ArrayList<String> elementos2 = new ArrayList<>();

            elementos2.add("Norris");
            elementos2.add(Double.toString(Rt_norris_125));
            elementos2.add(Double.toString(Rt_norris_250));
            elementos2.add(Double.toString(Rt_norris_500));
            elementos2.add(Double.toString(Rt_norris_1000));
            elementos2.add(Double.toString(Rt_norris_2000));
            elementos2.add(Double.toString(Rt_norris_4000));
            tabla.agregarFilaTabla(elementos2);

            ArrayList<String> elementos3 = new ArrayList<>();

            elementos3.add("Millington");
            elementos3.add(Double.toString(Rt_milli_125));
            elementos3.add(Double.toString(Rt_milli_250));
            elementos3.add(Double.toString(Rt_milli_500));
            elementos3.add(Double.toString(Rt_milli_1000));
            elementos3.add(Double.toString(Rt_milli_2000));
            elementos3.add(Double.toString(Rt_milli_4000));
            tabla.agregarFilaTabla(elementos3);

            ArrayList<String> elementos4 = new ArrayList<>();

            elementos4.add("Fitzroy");
            elementos4.add(Double.toString(Rt_fitz_125));
            elementos4.add(Double.toString(Rt_fitz_250));
            elementos4.add(Double.toString(Rt_fitz_500));
            elementos4.add(Double.toString(Rt_fitz_1000));
            elementos4.add(Double.toString(Rt_fitz_2000));
            elementos4.add(Double.toString(Rt_fitz_4000));
            tabla.agregarFilaTabla(elementos4);

            ArrayList<String> elementos5 = new ArrayList<>();

            elementos5.add("Arau");
            elementos5.add(Double.toString(Rt_arau_125));
            elementos5.add(Double.toString(Rt_arau_250));
            elementos5.add(Double.toString(Rt_arau_500));
            elementos5.add(Double.toString(Rt_arau_1000));
            elementos5.add(Double.toString(Rt_arau_2000));
            elementos5.add(Double.toString(Rt_arau_4000));
            tabla.agregarFilaTabla(elementos5);

            ArrayList<String> elementos6 = new ArrayList<>();

            elementos6.add("Arau inicial");
            elementos6.add(Double.toString(Rti_125));
            elementos6.add(Double.toString(Rti_250));
            elementos6.add(Double.toString(Rti_500));
            elementos6.add(Double.toString(Rti_1000));
            elementos6.add(Double.toString(Rti_2000));
            elementos6.add(Double.toString(Rti_4000));
            tabla.agregarFilaTabla(elementos6);

            ArrayList<String> elementos7 = new ArrayList<>();

            elementos7.add("Arau final");
            elementos7.add(Double.toString(Rtf_125));
            elementos7.add(Double.toString(Rtf_250));
            elementos7.add(Double.toString(Rtf_500));
            elementos7.add(Double.toString(Rtf_1000));
            elementos7.add(Double.toString(Rtf_2000));
            elementos7.add(Double.toString(Rtf_4000));
            tabla.agregarFilaTabla(elementos7);


    }

    public void onClick(View v) {

        int ID = v.getId();

        if (ID == R.id.button6) {

            final LineChart chart = (LineChart) findViewById(R.id.chart);
            Button ax =(Button) findViewById(R.id.button6) ;
            //Button vertab =(Button) findViewById(R.id.button8) ;

            EditText q =(EditText) findViewById(R.id.editText2);
            Spinner s =(Spinner) findViewById(R.id.spinner);
            TextView b=(TextView) findViewById(R.id.textView9);
            TextView c=(TextView) findViewById(R.id.textView10);

            if(!STRING_EMPTY.equals(q.getText().toString())){

                //vertab.setVisibility(View.VISIBLE);

                Float humedad = Float.parseFloat(q.getText().toString());
                if(vol>4000) {
                    facthum_125 = 0.00055 * (50 / humedad) * Math.pow((125 / 1000), 1.7) * 4 * vol;
                    facthum_250 = 0.00055 * (50 / humedad) * Math.pow((250 / 1000), 1.7) * 4 * vol;
                    facthum_500 = 0.00055 * (50 / humedad) * Math.pow((500 / 1000), 1.7) * 4 * vol;
                    facthum_1000 = 0.00055 * (50 / humedad) * Math.pow((1000 / 1000), 1.7) * 4 * vol;
                }else if(vol<4000){
                    facthum_125 =0;
                    facthum_250 = 0;
                    facthum_500 = 0;
                    facthum_1000 = 0;

                }

                facthum_2000 = 0.00055 * (50 / humedad) * Math.pow((2000 / 1000), 1.7) * 4 * vol;
                facthum_4000 = 0.00055 * (50 / humedad) * Math.pow((4000 / 1000), 1.7) * 4 * vol;


                dbHelper = new SQLiteDBHelper(this);
                List<Superficie> supList = dbHelper.getAllSups();
                List<MaterialesCaracteristicas> matCatList = dbHelper.getAllMaterials();

                SharedPreferences prefs = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE);
                a125 = prefs.getFloat("Alfa125", 0f);
                Log.d("v",Float.toString(a125));
                a250 = prefs.getFloat("Alfa250", 0f);
                Log.d("v",Float.toString(a250));
                a500 = prefs.getFloat("Alfa500", 0f);
                Log.d("v",Float.toString(a500));
                a1000 = prefs.getFloat("Alfa1000", 0f);
                Log.d("v",Float.toString(a1000));
                a2000 = prefs.getFloat("Alfa2000", 0f);
                Log.d("v",Float.toString(a2000));
                a4000 = prefs.getFloat("Alfa4000", 0f);
                Log.d("v",Float.toString(a4000));

                alfamid= prefs.getFloat("AlfaMID", 0f);
                Log.d("v",Double.toString(alfamid));
                St= prefs.getFloat("St", 0f);
                Log.d("v",Float.toString(St));

                vol = prefs.getFloat("volumen", 1f);
                Log.d("v",Float.toString(vol));
                Largo = prefs.getFloat("Largo", 10f);
                Log.d("v",Float.toString(Largo));
                Ancho = prefs.getFloat("Ancho", 8f);
                Log.d("v",Float.toString(Ancho));
                Alto = prefs.getFloat("Alto", 2.3f);
                Log.d("v",Float.toString(Alto));

                //Metodos de reverberación

                Rt_Sabine_125=(0.16*vol)/(St*(a125/St)+facthum_125);
                Rt_Sabine_250=(0.16*vol)/(St*(a250/St)+facthum_250);
                Rt_Sabine_500=(0.16*vol)/(St*(a500/St)+facthum_500);
                Rt_Sabine_1000=(0.16*vol)/(St*(a1000/St)+facthum_1000);
                Rt_Sabine_2000=(0.16*vol)/(St*(a2000/St)+facthum_2000);
                Rt_Sabine_4000=(0.16*vol)/(St*(a4000/St)+facthum_4000);

                Rt_norris_125=(0.16*vol)/(-St*Math.log(1-(a125/St))+facthum_125);
                Rt_norris_250=(0.16*vol)/(-St*Math.log(1-(a250/St))+facthum_250);
                Rt_norris_500=(0.16*vol)/(-St*Math.log(1-(a500/St))+facthum_500);
                Rt_norris_1000=(0.16*vol)/(-St*Math.log(1-(a1000/St))+facthum_1000);
                Rt_norris_2000=(0.16*vol)/(-St*Math.log(1-(a2000/St))+facthum_2000);
                Rt_norris_4000=(0.16*vol)/(-St*Math.log(1-(a4000/St))+facthum_4000);


                for(int i = 0; i<supList.size() ; i++){

                    Log.d("v","material "+Integer.toString(i+1));
                    Superficie supVO = supList.get(i);
                    float eje = supVO.getAxis();
                    float area = supVO.getSuperficie();
                    Log.d("v","Eje: "+Float.toString(supVO.getAxis()));

                    MaterialesCaracteristicas matCatVO = dbHelper.getMaterial(supVO.getMat());

                    if (eje==0){
                        Sx+=supVO.getSuperficie();
                        alfax_125+=area*(double) matCatVO.getAlfa125();
                        alfax_250+=area*(double) matCatVO.getAlfa250();
                        alfax_500+=area*(double) matCatVO.getAlfa500();
                        alfax_1000+=area*(double) matCatVO.getAlfa1000();
                        alfax_2000+=area*(double) matCatVO.getAlfa2000();
                        alfax_4000+=area*(double) matCatVO.getAlfa4000();

                        Log.d("v","Ejex: "+Float.toString(supVO.getSuperficie()));
                    }

                    if (eje==1){
                        Sy+=supVO.getSuperficie();
                        alfay_125+=area*(double) matCatVO.getAlfa125();
                        alfay_250+=area*(double) matCatVO.getAlfa250();
                        alfay_500+=area*(double) matCatVO.getAlfa500();
                        alfay_1000+=area*(double) matCatVO.getAlfa1000();
                        alfay_2000+=area*(double) matCatVO.getAlfa2000();
                        alfay_4000+=area*(double) matCatVO.getAlfa4000();
                        Log.d("v","Ejey: "+Float.toString(supVO.getSuperficie()));
                    }

                    if (eje==2){
                        Sz+=supVO.getSuperficie();
                        alfaz_125+=area*(double) matCatVO.getAlfa125();
                        alfaz_250+=area*(double) matCatVO.getAlfa250();
                        alfaz_500+=area*(double) matCatVO.getAlfa500();
                        alfaz_1000+=area*(double) matCatVO.getAlfa1000();
                        alfaz_2000+=area*(double) matCatVO.getAlfa2000();
                        alfaz_4000+=area*(double) matCatVO.getAlfa4000();

                        Log.d("v","Ejez: "+Float.toString(supVO.getSuperficie()));
                    }
                    Log.d("v","Sx: "+Float.toString(Sx));
                    Log.d("v","Sy: "+Float.toString(Sy));
                    Log.d("v","Sz: "+Float.toString(Sz));


                    denominador_milli_125+=area*Math.log(1-(double) matCatVO.getAlfa125());
                    denominador_milli_250+=area*Math.log(1-(double) matCatVO.getAlfa250());
                    denominador_milli_500+=area*Math.log(1-(double) matCatVO.getAlfa500());
                    denominador_milli_1000+=area*Math.log(1-(double) matCatVO.getAlfa1000());
                    denominador_milli_2000+=area*Math.log(1-(double) matCatVO.getAlfa2000());
                    denominador_milli_4000+=area*Math.log(1-(double) matCatVO.getAlfa4000());

                }

                Rt_milli_125=(0.16*vol)/(-denominador_milli_125+facthum_125);
                Rt_milli_250=(0.16*vol)/(-denominador_milli_250+facthum_250);
                Rt_milli_500=(0.16*vol)/(-denominador_milli_500+facthum_500);
                Rt_milli_1000=(0.16*vol)/(-denominador_milli_1000+facthum_1000);
                Rt_milli_2000=(0.16*vol)/(-denominador_milli_2000+facthum_2000);
                Rt_milli_4000=(0.16*vol)/(-denominador_milli_4000+facthum_4000);


                if(Sx==0){

                    RTX_125=1;
                    RTX_250=1;
                    RTX_500=1;
                    RTX_1000=1;
                    RTX_2000=1;
                    RTX_4000=1;
                }

                if(Sy==0){
                    RTY_125=1;
                    RTY_250=1;
                    RTY_500=1;
                    RTY_1000=1;
                    RTY_2000=1;
                    RTY_4000=1;
                }

                if(Sz==0){
                    RTZ_125=1;
                    RTZ_250=1;
                    RTZ_500=1;
                    RTZ_1000=1;
                    RTZ_2000=1;
                    RTZ_4000=1;
                }

                if(Sx!=0){
                    alfax_125=alfax_125/Sx;
                    alfax_250=alfax_250/Sx;
                    alfax_500=alfax_500/Sx;
                    alfax_1000=alfax_1000/Sx;
                    alfax_2000=alfax_2000/Sx;
                    alfax_4000=alfax_4000/Sx;

                    RTX_125=((0.16*vol)/(-St*Math.log(1-alfax_125)+facthum_125));
                    RTX_250=((0.16*vol)/(-St*Math.log(1-alfax_250)+facthum_250));
                    RTX_500=((0.16*vol)/(-St*Math.log(1-alfax_500)+facthum_500));
                    RTX_1000=((0.16*vol)/(-St*Math.log(1-alfax_1000)+facthum_1000));
                    RTX_2000=((0.16*vol)/(-St*Math.log(1-alfax_2000)+facthum_2000));
                    RTX_4000=((0.16*vol)/(-St*Math.log(1-alfax_4000)+facthum_4000));


                }

                if(Sy!=0){
                    alfay_125=alfay_125/Sy;
                    alfay_250=alfay_250/Sy;
                    alfay_500=alfay_500/Sy;
                    alfay_1000=alfay_1000/Sy;
                    alfay_2000=alfay_2000/Sy;
                    alfay_4000=alfay_4000/Sy;

                    RTY_125=((0.16*vol)/(-St*Math.log(1-alfay_125)+facthum_125));
                    RTY_250=((0.16*vol)/(-St*Math.log(1-alfay_250)+facthum_250));
                    RTY_500=((0.16*vol)/(-St*Math.log(1-alfay_500)+facthum_500));
                    RTY_1000=((0.16*vol)/(-St*Math.log(1-alfay_1000)+facthum_1000));
                    RTY_2000=((0.16*vol)/(-St*Math.log(1-alfay_2000)+facthum_2000));
                    RTY_4000=((0.16*vol)/(-St*Math.log(1-alfay_4000)+facthum_4000));



                }

                if(Sz!=0){
                    alfaz_125=alfaz_125/Sz;
                    alfaz_250=alfaz_250/Sz;
                    alfaz_500=alfaz_500/Sz;
                    alfaz_1000=alfaz_1000/Sz;
                    alfaz_2000=alfaz_2000/Sz;
                    alfaz_4000=alfaz_4000/Sz;

                    RTZ_125=((0.16*vol)/(-St*Math.log(1-alfaz_125)+facthum_125));
                    RTZ_250=((0.16*vol)/(-St*Math.log(1-alfaz_250)+facthum_250));
                    RTZ_500=(((0.16*vol)/(-St*Math.log(1-alfaz_500)+facthum_500)));
                    RTZ_1000=((0.16*vol)/(-St*Math.log(1-alfaz_1000)+facthum_1000));
                    RTZ_2000=((0.16*vol)/(-St*Math.log(1-alfaz_2000)+facthum_2000));
                    RTZ_4000=((0.16*vol)/(-St*Math.log(1-alfaz_4000)+facthum_4000));

                }

                //Arau

                a_125=Math.pow((-Math.log(1-alfax_125)),(Sx/St))*Math.pow((-Math.log(1-alfay_125)),(Sy/St))*Math.pow((-Math.log(1-alfaz_125)),(Sz/St));
                a_250=Math.pow((-Math.log(1-alfax_250)),(Sx/St))*Math.pow((-Math.log(1-alfay_250)),(Sy/St))*Math.pow((-Math.log(1-alfaz_250)),(Sz/St));
                a_500=Math.pow((-Math.log(1-alfax_500)),(Sx/St))*Math.pow((-Math.log(1-alfay_500)),(Sy/St))*Math.pow((-Math.log(1-alfaz_500)),(Sz/St));
                a_1000=Math.pow((-Math.log(1-alfax_1000)),(Sx/St))*Math.pow((-Math.log(1-alfay_1000)),(Sy/St))*Math.pow((-Math.log(1-alfaz_1000)),(Sz/St));
                a_2000=Math.pow((-Math.log(1-alfax_2000)),(Sx/St))*Math.pow((-Math.log(1-alfay_2000)),(Sy/St))*Math.pow((-Math.log(1-alfaz_2000)),(Sz/St));
                a_4000=Math.pow((-Math.log(1-alfax_4000)),(Sx/St))*Math.pow((-Math.log(1-alfay_4000)),(Sy/St))*Math.pow((-Math.log(1-alfaz_4000)),(Sz/St));

                Rt_arau_125=(0.16*vol)/(St*a_125+facthum_125);
                Rt_arau_250=(0.16*vol)/(St*a_250+facthum_250);
                Rt_arau_500=(0.16*vol)/(St*a_500+facthum_500);
                Rt_arau_1000=(0.16*vol)/(St*a_1000+facthum_1000);
                Rt_arau_2000=(0.16*vol)/(St*a_2000+facthum_2000);
                Rt_arau_4000=(0.16*vol)/(St*a_4000+facthum_4000);

                //


                //Fitzroy

                Rt_fitz_125=((Sx/St)*RTX_125)+((Sy/St)*RTY_125)+((Sz/St)*RTZ_125);
                Rt_fitz_250=((Sx/St)*RTX_250)+((Sy/St)*RTY_250)+((Sz/St)*RTZ_250);
                Rt_fitz_500=((Sx/St)*RTX_500)+((Sy/St)*RTY_500)+((Sz/St)*RTZ_500);
                Rt_fitz_1000=((Sx/St)*RTX_1000)+((Sy/St)*RTY_1000)+((Sz/St)*RTZ_1000);

                Rt_fitz_2000=((Sx/St)*RTX_2000)+((Sy/St)*RTY_2000)+((Sz/St)*RTZ_2000);
                Rt_fitz_4000=((Sx/St)*RTX_4000)+((Sy/St)*RTY_4000)+((Sz/St)*RTZ_4000);

                //
                Rt_arau[0]=(float) Rt_arau_125;
                Rt_arau[1]=(float) Rt_arau_250;
                Rt_arau[2]=(float) Rt_arau_500;
                Rt_arau[3]=(float) Rt_arau_1000;
                Rt_arau[4]=(float) Rt_arau_2000;
                Rt_arau[5]=(float) Rt_arau_4000;

                Log.d("v","Rtarau125: "+Double.toString(Rt_arau_125));
                Log.d("v","Rtarau250: "+Double.toString(Rt_arau_250));
                Log.d("v","Rtarau500: "+Double.toString(Rt_arau_500));
                Log.d("v","Rtarau1000: "+Double.toString(Rt_arau_1000));
                Log.d("v","Rtarau2000: "+Double.toString(Rt_arau_2000));
                Log.d("v","Rtarau4000: "+Double.toString(Rt_arau_4000));


                Rt_fitz[0]=(float) Rt_fitz_125;
                Rt_fitz[1]=(float) Rt_fitz_250;
                Rt_fitz[2]=(float) Rt_fitz_500;
                Rt_fitz[3]=(float) Rt_fitz_1000;
                Rt_fitz[4]=(float) Rt_fitz_2000;
                Rt_fitz[5]=(float) Rt_fitz_4000;

                Log.d("v","Rtfitz125: "+Double.toString(Rt_fitz_125));
                Log.d("v","Rtfitz250: "+Double.toString(Rt_fitz_250));
                Log.d("v","Rtfitz500: "+Double.toString(Rt_fitz_500));
                Log.d("v","Rtfitz1000: "+Double.toString(Rt_fitz_1000));
                Log.d("v","Rtfitz2000: "+Double.toString(Rt_fitz_2000));
                Log.d("v","Rtfitz4000: "+Double.toString(Rt_fitz_4000));


                Rt_Sabine[0]=(float) Rt_Sabine_125;
                Rt_Sabine[1]=(float) Rt_Sabine_250;
                Rt_Sabine[2]=(float) Rt_Sabine_500;
                Rt_Sabine[3]=(float) Rt_Sabine_1000;
                Rt_Sabine[4]=(float) Rt_Sabine_2000;
                Rt_Sabine[5]=(float) Rt_Sabine_4000;

                Log.d("v","RtSabine125: "+Double.toString(Rt_Sabine_125));
                Log.d("v","RtSabine250: "+Double.toString(Rt_Sabine_250));
                Log.d("v","RtSabine500: "+Double.toString(Rt_Sabine_500));
                Log.d("v","RtSabine1000: "+Double.toString(Rt_Sabine_1000));
                Log.d("v","RtSabine2000: "+Double.toString(Rt_Sabine_2000));
                Log.d("v","RtSabine4000: "+Double.toString(Rt_Sabine_4000));


                Rt_norris[0]=(float) Rt_norris_125;
                Rt_norris[1]=(float) Rt_norris_250;
                Rt_norris[2]=(float) Rt_norris_500;
                Rt_norris[3]=(float) Rt_norris_1000;
                Rt_norris[4]=(float) Rt_norris_2000;
                Rt_norris[5]=(float) Rt_norris_4000;

                Log.d("v","RtNorris125: "+Double.toString(Rt_norris_125));
                Log.d("v","RtNorris250: "+Double.toString(Rt_norris_250));
                Log.d("v","RtNorris500: "+Double.toString(Rt_norris_500));
                Log.d("v","RtNorris1000: "+Double.toString(Rt_norris_1000));
                Log.d("v","RtNorris2000: "+Double.toString(Rt_norris_2000));
                Log.d("v","RtNorris4000: "+Double.toString(Rt_norris_4000));


                Rt_milli[0]=(float) Rt_milli_125;
                Rt_milli[1]=(float) Rt_milli_250;
                Rt_milli[2]=(float) Rt_milli_500;
                Rt_milli[3]=(float) Rt_milli_1000;
                Rt_milli[4]=(float) Rt_milli_2000;
                Rt_milli[5]=(float) Rt_milli_4000;

                Log.d("v","Rtmilli125: "+Double.toString(Rt_milli_125));
                Log.d("v","Rtmilli250: "+Double.toString(Rt_milli_250));
                Log.d("v","Rtmilli500: "+Double.toString(Rt_milli_500));
                Log.d("v","Rtmilli1000: "+Double.toString(Rt_milli_1000));
                Log.d("v","Rtmilli2000: "+Double.toString(Rt_milli_2000));
                Log.d("v","Rtmilli4000: "+Double.toString(Rt_milli_4000));

//Fin Metodos de Reverberación

                final LinearLayout mlscroll = (LinearLayout) findViewById(R.id.lin);

                findViewById(R.id.lin).setVisibility(View.GONE);

                chart.setVisibility(View.VISIBLE);
                int betaescogido  = s.getSelectedItemPosition();


                switch (betaescogido){
                    case 0:
                        betaescogido=-5;
                        break;
                    case 1:
                        betaescogido=-4;
                        break;
                    case 2:
                        betaescogido=-3;
                        break;
                    case 3:
                        betaescogido=-2;
                        break;
                    case 4:
                        betaescogido=-1;
                        break;
                    case 5:
                        betaescogido=0;
                        break;
                    case 6:
                        betaescogido=1;
                    break;

                }

                beta=betaescogido;
                Log.d("v","Beta_escogido: "+Double.toString(beta));


                //Factor de corrección
                d_125 = Math.exp( (1 /(6.0+beta))*Math.pow( (Sx/St)*Math.pow(Math.log(alfax_125),2) + (Sy/St)*Math.pow(Math.log(alfay_125),2) + (Sz/St)*Math.pow(Math.log(alfaz_125),2) -Math.pow(((Sx/St)*Math.log(alfax_125)+(Sy/St)*Math.log(alfay_125)+(Sz/St)*Math.log(alfaz_125)),2), 0.5));
                d_250 = Math.exp( (1 /(6.0+beta))*Math.pow( (Sx/St)*Math.pow(Math.log(alfax_250),2) + (Sy/St)*Math.pow(Math.log(alfay_250),2) + (Sz/St)*Math.pow(Math.log(alfaz_250),2) -Math.pow(((Sx/St)*Math.log(alfax_250)+(Sy/St)*Math.log(alfay_250)+(Sz/St)*Math.log(alfaz_250)),2), 0.5));
                d_500 = Math.exp( (1 /(6.0+beta))*Math.pow( (Sx/St)*Math.pow(Math.log(alfax_500),2) + (Sy/St)*Math.pow(Math.log(alfay_500),2) + (Sz/St)*Math.pow(Math.log(alfaz_500),2) -Math.pow(((Sx/St)*Math.log(alfax_500)+(Sy/St)*Math.log(alfay_500)+(Sz/St)*Math.log(alfaz_500)),2), 0.5));
                d_1000= Math.exp( (1 /(6.0+beta))*Math.pow( (Sx/St)*Math.pow(Math.log(alfax_1000),2) + (Sy/St)*Math.pow(Math.log(alfay_1000),2) + (Sz/St)*Math.pow(Math.log(alfaz_1000),2) -Math.pow(((Sx/St)*Math.log(alfax_1000)+(Sy/St)*Math.log(alfay_1000)+(Sz/St)*Math.log(alfaz_1000)),2), 0.5));
                d_2000= Math.exp( (1 /(6.0+beta))*Math.pow( (Sx/St)*Math.pow(Math.log(alfax_2000),2) + (Sy/St)*Math.pow(Math.log(alfay_2000),2) + (Sz/St)*Math.pow(Math.log(alfaz_2000),2) -Math.pow(((Sx/St)*Math.log(alfax_2000)+(Sy/St)*Math.log(alfay_2000)+(Sz/St)*Math.log(alfaz_2000)),2), 0.5));
                d_4000= Math.exp( (1 /(6.0+beta))*Math.pow( (Sx/St)*Math.pow(Math.log(alfax_4000),2) + (Sy/St)*Math.pow(Math.log(alfay_4000),2) + (Sz/St)*Math.pow(Math.log(alfaz_4000),2) -Math.pow(((Sx/St)*Math.log(alfax_4000)+(Sy/St)*Math.log(alfay_4000)+(Sz/St)*Math.log(alfaz_4000)),2), 0.5));
                //

                //VERIFICAR d's
                Log.d("v","d_125: "+Double.toString(d_125));
                Log.d("v","d_250: "+Double.toString(d_250));
                Log.d("v","d_500: "+Double.toString(d_500));
                Log.d("v","d_1000: "+Double.toString(d_1000));
                Log.d("v","d_2000: "+Double.toString(d_2000));
                Log.d("v","d_4000: "+Double.toString(d_4000));
                //

                Rti_125=60/((d_125*(60/Rt_arau_125)));
                Rti_250=60/((d_250*(60/Rt_arau_250)));
                Rti_500=60/((d_500*(60/Rt_arau_500)));
                Rti_1000=60/((d_1000*(60/Rt_arau_1000)));
                Rti_2000=60/((d_2000*(60/Rt_arau_2000)));
                Rti_4000=60/((d_4000*(60/Rt_arau_4000)));

                Rti_arau[0]=(float) Rti_125;
                Rti_arau[1]=(float) Rti_250;
                Rti_arau[2]=(float) Rti_500;
                Rti_arau[3]=(float) Rti_1000;
                Rti_arau[4]=(float) Rti_2000;
                Rti_arau[5]=(float) Rti_4000;


                Log.d("v","Rti arau125: "+Double.toString(Rti_125));
                Log.d("v","Rti arau250: "+Double.toString(Rti_250));
                Log.d("v","Rti arau500: "+Double.toString(Rti_500));
                Log.d("v","Rti arau1000: "+Double.toString(Rti_1000));
                Log.d("v","Rti arau2000: "+Double.toString(Rti_2000));
                Log.d("v","Rti arau4000: "+Double.toString(Rti_4000));
/*
                Rtf_125=60*((60/Rt_arau_125)/d_125);
                Rtf_250=60*((60/Rt_arau_250)/d_250);
                Rtf_500=60*((60/Rt_arau_500)/d_500);
                Rtf_1000=60*((60/Rt_arau_1000)/d_1000);
                Rtf_2000=60*((60/Rt_arau_2000)/d_2000);
                Rtf_4000=60*((60/Rt_arau_4000)/d_4000);
*/
                Rtf_125=(0.16*vol)/ (St*(a_125/d_125));
                Rtf_250=(0.16*vol)/ (St*(a_250/d_250));
                Rtf_500=(0.16*vol)/ (St*(a_500/d_500));
                Rtf_1000=(0.16*vol)/ (St*(a_1000/d_1000));
                Rtf_2000=(0.16*vol)/ (St*(a_2000/d_2000));
                Rtf_4000=(0.16*vol)/ (St*(a_4000/d_4000));

                Rtf_arau[0]=(float) Rtf_125;
                Rtf_arau[1]=(float) Rtf_250;
                Rtf_arau[2]=(float) Rtf_500;
                Rtf_arau[3]=(float) Rtf_1000;
                Rtf_arau[4]=(float) Rtf_2000;
                Rtf_arau[5]=(float) Rtf_4000;

                Log.d("v","Rtf arau125: "+Double.toString(Rtf_125));
                Log.d("v","Rtf arau250: "+Double.toString(Rtf_250));
                Log.d("v","Rtf arau500: "+Double.toString(Rtf_500));
                Log.d("v","Rtf arau1000: "+Double.toString(Rtf_1000));
                Log.d("v","Rtf arau2000: "+Double.toString(Rtf_2000));
                Log.d("v","Rtf arau4000: "+Double.toString(Rtf_4000));


                List<Entry> entries = new ArrayList<>();
                List<Entry> entries2 = new ArrayList<>();
                List<Entry> entries3 = new ArrayList<>();
                List<Entry> entries4 = new ArrayList<>();
                List<Entry> entries5 = new ArrayList<>();
                List<Entry> entries6 = new ArrayList<>();
                List<Entry> entries7 = new ArrayList<>();

                int a= 125;
                for (int i=0 ; i< Rt_norris.length;i++ ) {


                    // turn your data into Entry objects
                    entries7.add(new Entry(a,Rtf_arau[i]));
                    entries6.add(new Entry(a,Rti_arau[i]));
                    entries5.add(new Entry(a,Rt_arau[i]));
                    entries4.add(new Entry(a,Rt_fitz[i]));
                    entries3.add(new Entry(a, Rt_milli[i]));
                    entries2.add(new Entry(a, Rt_Sabine[i]));
                    entries.add(new Entry(a, Rt_norris[i]));
                    a=a*2;
                }

                LineDataSet dataSet = new LineDataSet(entries, "Norris"); // add entries to dataset
                dataSet.setColor(Color.BLACK);
                dataSet.setValueTextColor(Color.TRANSPARENT);
                dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

                LineDataSet dataSet2 = new LineDataSet(entries2, "Sabine"); // add entries to dataset
                dataSet2.setColor(Color.RED);
                dataSet2.setValueTextColor(Color.TRANSPARENT);
                dataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);

                LineDataSet dataSet3 = new LineDataSet(entries3, "Millington"); // add entries to dataset
                dataSet3.setColor(Color.BLUE);
                dataSet3.setValueTextColor(Color.TRANSPARENT);
                dataSet3.setAxisDependency(YAxis.AxisDependency.LEFT);

                LineDataSet dataSet4 = new LineDataSet(entries4, "Fitzroy"); // add entries to dataset
                dataSet4.setColor(Color.YELLOW);
                dataSet4.setValueTextColor(Color.TRANSPARENT);
                dataSet4.setAxisDependency(YAxis.AxisDependency.LEFT);

                LineDataSet dataSet5 = new LineDataSet(entries5, "Arau"); // add entries to dataset
                dataSet5.setColor(Color.GREEN);
                dataSet5.setValueTextColor(Color.TRANSPARENT);
                dataSet5.setAxisDependency(YAxis.AxisDependency.LEFT);

                LineDataSet dataSet6 = new LineDataSet(entries6, "Arau Rti"); // add entries to dataset
                dataSet6.setColor(Color.MAGENTA);
                dataSet6.setValueTextColor(Color.TRANSPARENT);
                dataSet6.setAxisDependency(YAxis.AxisDependency.LEFT);

                LineDataSet dataSet7 = new LineDataSet(entries7, "Arau Rtf"); // add entries to dataset
                dataSet7.setColor(Color.CYAN);
                dataSet7.setValueTextColor(Color.TRANSPARENT);
                dataSet7.setAxisDependency(YAxis.AxisDependency.LEFT);

                List<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(dataSet);
                dataSets.add(dataSet2);
                dataSets.add(dataSet3);
                dataSets.add(dataSet4);
                dataSets.add(dataSet5);
                dataSets.add(dataSet6);
                dataSets.add(dataSet7);

                LineData lineData = new LineData(dataSets);
                chart.setData(lineData);
                chart.invalidate();
                chart.getDescription().setEnabled(false);
                chart.animateXY(3000, 3000);

                Legend legend = chart.getLegend();
                legend.setForm(Legend.LegendForm.LINE);
                legend.setFormSize(12);
                legend.setTextSize(12);
                legend.setXEntrySpace(10);


                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


                /*
                final String[] quarters = new String[] { "125", "250", "500", "1000","2000","4000" };

                IAxisValueFormatter formatter = new IAxisValueFormatter() {


                    public String getFormattedValue(float value, AxisBase axis) {
                        return quarters[(int) 5];
                    }

                    // we don't draw numbers, so no decimal digits needed

                    public int getDecimalDigits() {  return 0; }
                };

                XAxis xAxis = chart.getXAxis();
                xAxis.setGranularity(125f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);

                */

                creartabla();

            }

        }

        if(ID==R.id.button8){


            //findViewById(R.id.lin2).setVisibility(View.GONE);
            creartabla();

        }

    }

}
