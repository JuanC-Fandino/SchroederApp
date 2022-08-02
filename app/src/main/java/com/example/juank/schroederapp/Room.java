package com.example.juank.schroederapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.juank.schroederapp.Materials_Base;
import com.example.juank.schroederapp.SQLiteDBHelper;
import com.example.juank.schroederapp.MaterialesCaracteristicas;

import java.util.ArrayList;
import java.util.List;



public class Room extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    static Spinner sItems = null;

    SQLiteDBHelper dbHelper = null;

    EditText NumMateriales;
    public int Num,id, buttonid;
    public float suptotal,alfamid,reverbtime,ABSmid,FrecSch,vol;
    Button AceptarBtn,SaveBtn;
    String[] ejes = {"X","Y","Z"};

    private static int selectedMovieId = 0;

    private static int selectedMaterialId = 0;

    float a125,a250,a500,a1000,a2000,a4000,ABS125,ABS250,ABS500,ABS1000,ABS2000,ABS4000,area1,area2,area3,area4;

    List<Spinner> SpinnerList = new ArrayList<>();
    List<Spinner> SpinnerList_2 = new ArrayList<>();
    List<EditText> EditList = new ArrayList<>();

    List<Float> ABST125 = new ArrayList<>();
    List<Float>  ABST250 = new ArrayList<>();
    List<Float>  ABST500= new ArrayList<>();
    List<Float>  ABST1000= new ArrayList<>();
    List<Float>  ABST2000= new ArrayList<>();
    List<Float>  ABST4000= new ArrayList<>();
    List<Float> superficies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);
        dbHelper = new SQLiteDBHelper(this);
        dbHelper.deleteAll();
        SharedPreferences prefs = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE);
        vol = prefs.getFloat("volumen", 100f);



    }


    public void populateSpinnerx(Spinner spn) {

        List<MaterialesCaracteristicas> matCatList = dbHelper.getAllMaterials();
        List<MovieSpinnerVO> matSpinnerList = new ArrayList<>();

        for (int i = 0; i < matCatList.size(); i++) {

            MaterialesCaracteristicas matCatVO = matCatList.get(i);

            MovieSpinnerVO matSpinnerVO = new MovieSpinnerVO(matCatVO.getMaterialId(), matCatVO.getMaterialName());

            matSpinnerList.add(matSpinnerVO);


        }

        ArrayAdapter<String> adapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, matSpinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);



    }


    public void onNothingSelected(AdapterView<?> parent) {
    }

    private int getSpinnerPosition(Spinner spinner){
        Adapter adapter = spinner.getAdapter();
        int i=0;
        for (; i < adapter.getCount() ; i++){

            MovieSpinnerVO movieSpinnerVO = (MovieSpinnerVO)adapter.getItem(i);
            if(selectedMovieId == movieSpinnerVO.getMovieId())

            {
                return i;
            }

        }
        return 0;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       // MovieSpinnerVO movieSpinnerVO = (MovieSpinnerVO) sItems.getSelectedItem();
/*        int movieId = movieSpinnerVO.getMovieId();
        selectedMovieId = movieId;

        MaterialesCaracteristicas matCatVO = dbHelper.getMaterial(movieId);

        String a = matCatVO.getMaterialName();
        Log.d("v",a);

        float b= matCatVO.getAlfa125();

        Log.d("v",Float.toString(b));


        float c=matCatVO.getAlfa250();
        Log.d("v",Float.toString(c));


        float d=matCatVO.getAlfa500();
        Log.d("v",Float.toString(d));

        float e=matCatVO.getAlfa1000();
        Log.d("v",Float.toString(e));*/



    }

    public void onClick(View v){

        int ID = v.getId();

        if (ID == R.id.button) {

            NumMateriales= (EditText) findViewById(R.id.editText);
            Num= Integer.parseInt(NumMateriales.getText().toString());
            NumMateriales.setEnabled(false);
            AceptarBtn=(Button) findViewById(R.id.button);
            AceptarBtn.setEnabled(false);
            View view = this.getCurrentFocus();


            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            LinearLayout mlscroll = (LinearLayout) findViewById(R.id.linearscroll);

            for( int i = 0; i < Num; i++ )
            {

                TextView tv=new TextView(this);
                tv.setText("Material "+Integer.toString(i+1)+":");

                EditText textEdit = new EditText(this);
                int a = textEdit.generateViewId();
                textEdit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                textEdit.setId(a);

                Spinner spinner = new Spinner(this);
                int b = spinner.generateViewId();
                spinner.setId(b);

                TextView tv2=new TextView(this);
                tv2.setText("Eje:");

                Spinner spine =new Spinner(this);
                spine.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ejes));
                int c = spine.generateViewId();
                spine.setId(c);

                EditList.add(textEdit);
                SpinnerList.add(spinner);
                SpinnerList_2.add(spine);

                mlscroll.addView(tv);
                mlscroll.addView(spinner);
                mlscroll.addView(textEdit);
                mlscroll.addView(tv2);
                mlscroll.addView(spine);

                populateSpinnerx(spinner);

            }

            Button SaveBtn = new Button(this);
            buttonid = SaveBtn.generateViewId();
            SaveBtn.setId(buttonid);
            SaveBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    int p=0;

                    while (p < SpinnerList.size() ) {

                        Spinner spn = SpinnerList.get(p);
                        EditText et = EditList.get(p);
                        Spinner spn_2 = SpinnerList_2.get(p);
                        float ejescogido = spn_2.getSelectedItemPosition();
                        Log.d("v", Float.toString(ejescogido));


                        float area = Float.parseFloat(et.getText().toString());
                        superficies.add(area);


                        MovieSpinnerVO movieSpinnerVO = (MovieSpinnerVO) spn.getSelectedItem();

                        int materialId = movieSpinnerVO.getMovieId();
                        selectedMaterialId = materialId;
                        Log.d("v", "materialiD: "+Float.toString(materialId));

                        MaterialesCaracteristicas matCatVO = dbHelper.getMaterial(materialId);

                        String a = matCatVO.getMaterialName();
                        Log.d("v", a);

                        float b = matCatVO.getAlfa125();
                        Log.d("v", Float.toString(b));
                        ABS125=area*b;
                        ABST125.add(ABS125);


                        float c = matCatVO.getAlfa250();
                        Log.d("v", Float.toString(c));
                        ABS250=area*c;
                        ABST250.add(ABS250);

                        float d = matCatVO.getAlfa500();
                        Log.d("v", Float.toString(d));
                        ABS500=area*d;
                        ABST500.add(ABS500);

                        float e = matCatVO.getAlfa1000();
                        Log.d("v", Float.toString(e));
                        ABS1000=area*e;
                        ABST1000.add(ABS1000);

                        float f = matCatVO.getAlfa2000();
                        Log.d("v", Float.toString(f));
                        ABS2000=area*f;
                        ABST2000.add(ABS2000);

                        float g = matCatVO.getAlfa4000();
                        Log.d("v", Float.toString(g));
                        ABS4000=area*f;
                        ABST4000.add(ABS4000);

                        Superficie sup = new Superficie();
                        sup.setSuperficie(area);
                        Log.d("v", Float.toString(area));
                        sup.setAxis(ejescogido);
                        sup.setMat(materialId);
                        dbHelper.createTableSup(sup);

                        p++;
                    }

                    guardar();
            }

            });

            SaveBtn.setText("Guardar");
            mlscroll.addView(SaveBtn);

        }

        }

    public void guardar(){

        SharedPreferences prefs = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE);
        Float restoredFloat = prefs.getFloat("volumen", 1f);

        if (restoredFloat != 1f) {
            vol = prefs.getFloat("volumen", 1f);
        }


        a125=0f;
        for(int i = 0; i < ABST125.size(); i++){

            a125 += ABST125.get(i);
        }

        Log.d("v", Float.toString(a125));

        a250=0f;
        for(int i = 0; i < ABST250.size(); i++){

            a250 += ABST250.get(i);
        }
        Log.d("v", Float.toString(a250));


        a500=0f;
        for(int i = 0; i < ABST500.size(); i++){

            a500 += ABST500.get(i);
        }
        Log.d("v", Float.toString(a500));

        a1000=0f;
        for(int i = 0; i < ABST1000.size(); i++){

            a1000 += ABST1000.get(i);
        }
        Log.d("v", Float.toString(a1000));

        a2000=0f;
        for(int i = 0; i < ABST2000.size(); i++){

            a2000 += ABST2000.get(i);
        }
        Log.d("v", Float.toString(a2000));

        a4000=0f;
        for(int i = 0; i < ABST4000.size(); i++){

            a4000 += ABST4000.get(i);
        }
        Log.d("v", Float.toString(a4000));


        suptotal=0f;
        for(int i = 0; i < superficies.size(); i++){

            suptotal += superficies.get(i);
        }
        Log.d("v", Float.toString(suptotal));

        alfamid=((a500/suptotal)+(a1000/suptotal))/2;
        Log.d("v", "Alfamid"+Float.toString(alfamid));

        ABSmid=alfamid*suptotal;
        Log.d("v", "ABSmid:"+Float.toString(ABSmid));

        reverbtime=(0.16f*vol)/ABSmid;
        Log.d("v", "reverbtime:"+Float.toString(reverbtime));

        FrecSch= 2000f * (float) Math.sqrt(reverbtime/vol);

        LinearLayout mlscroll = (LinearLayout) findViewById(R.id.linearscroll);

        SharedPreferences.Editor editor = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE).edit();
        Log.d("v", Float.toString(FrecSch));

        editor.putFloat("Alfa125", a125);
        editor.putFloat("Alfa250", a250);
        editor.putFloat("Alfa500", a500);
        editor.putFloat("Alfa1000", a1000);
        editor.putFloat("Alfa2000", a2000);
        editor.putFloat("Alfa4000", a4000);

        editor.putFloat("AlfaMID", alfamid);
        editor.putFloat("St",suptotal);

        editor.putFloat("Sch", FrecSch);
        editor.apply();

        TextView tv=new TextView(this);
        tv.setText("α_mid: "+Float.toString(alfamid));
        mlscroll.addView(tv);

        TextView tv1=new TextView(this);
        tv1.setText("ABSmid: "+Float.toString(ABSmid));
        mlscroll.addView(tv1);

        TextView tv2=new TextView(this);
        tv2.setText("Tiempo de Reverberación: "+Float.toString(reverbtime));
        mlscroll.addView(tv2);

        TextView tv3=new TextView(this);
        tv3.setText("Frecuencia de Schroeder: "+Float.toString(FrecSch));
        mlscroll.addView(tv3);





    }


    }


