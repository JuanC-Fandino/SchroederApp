package com.example.juank.schroederapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Length, Width,  Height;
    Button btnCalculate,btnModos,btnvolumen;
    Float L,W,H,vol;
    TextView Resultados;
    String Res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Length = (EditText) findViewById(R.id.editText3);
        Width = (EditText) findViewById(R.id.editText4);
        Height = (EditText) findViewById(R.id.editText5);

        Resultados = (TextView) (findViewById(R.id.textView5));
        btnCalculate = (Button) (findViewById(R.id.btn));
        btnModos = (Button) (findViewById(R.id.button4));

    }

    protected void onResume(){
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE);
        Float restoredFloat = prefs.getFloat("Sch", 0f);

        if (restoredFloat != 0f) {
            btnModos.setEnabled(true);
        }


    }

    public void onClick(View v) {

        int ID = v.getId();

        if (ID == R.id.button2) {

            Intent intent = new Intent(getApplicationContext(), Materiales.class);
            startActivity(intent);

        }


        if (ID == R.id.button7) {

            btnvolumen = (Button) (findViewById(R.id.button7));
            btnvolumen.setText("Modificar Volumen");


            Length.setEnabled(false);
            Width.setEnabled(false);
            Height.setEnabled(false);


            if (Length.getText().toString().trim().equalsIgnoreCase("") || Width.getText().toString().trim().equalsIgnoreCase("") || Height.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(MainActivity.this, "Existen campos vacios!",
                        Toast.LENGTH_SHORT).show();
            } else {

                L = Float.parseFloat(Length.getText().toString());
                W = Float.parseFloat(Width.getText().toString());
                H = Float.parseFloat(Height.getText().toString());
                vol = L * W * H;
                SharedPreferences.Editor editor = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE).edit();
                editor.putFloat("volumen", vol);
                editor.putFloat("Largo", L);
                editor.putFloat("Ancho", W);
                editor.putFloat("Alto", H);
                editor.apply();

            }


        }



        if (ID == R.id.button5) {

            Intent intent = new Intent(getApplicationContext(), MetodosReverbacion.class);
            startActivity(intent);

        }


        if (ID == R.id.button4) {

            if (Length.getText().toString().trim().equalsIgnoreCase("") || Width.getText().toString().trim().equalsIgnoreCase("") || Height.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(MainActivity.this, "Existen campos vacios!",
                        Toast.LENGTH_SHORT).show();
            } else {

                L = Float.parseFloat(Length.getText().toString());
                W = Float.parseFloat(Width.getText().toString());
                H = Float.parseFloat(Height.getText().toString());
                vol = L * W * H;

                SharedPreferences.Editor editor = getSharedPreferences("DATOS_A_COMPARTIR", MODE_PRIVATE).edit();
                editor.putFloat("volumen", vol);
                editor.putFloat("Largo", L);
                editor.putFloat("Ancho", W);
                editor.putFloat("Alto", H);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), Room_modes.class);
                startActivity(intent);

            }

        }

        if (ID == R.id.btn) {

            if (Length.getText().toString().trim().equalsIgnoreCase("") || Width.getText().toString().trim().equalsIgnoreCase("") || Height.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(MainActivity.this, "Existen campos vacios!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), Room.class);
                startActivity(intent);
            }
        }





    }
}
