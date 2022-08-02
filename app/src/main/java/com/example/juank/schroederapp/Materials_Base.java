package com.example.juank.schroederapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juank.schroederapp.SQLiteDBHelper;
import com.example.juank.schroederapp.MaterialesCaracteristicas;
import com.example.juank.schroederapp.MovieSpinnerVO;

import java.util.ArrayList;
import java.util.List;

import static com.example.juank.schroederapp.Materiales.fa;

public class Materials_Base extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
private static String STRING_EMPTY = "";
static Spinner sItems = null;
private static int selectedMovieId = 0;
private static boolean isEdit = false;
        SQLiteDBHelper dbHelper = null;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials__base);
        dbHelper = new SQLiteDBHelper(this);
        fa.finish();
        populateSpinner();

        Button addMovie = (Button) findViewById(R.id.btnAdd);
        addMovie.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        addNewMovie();
        }
        });
        }

private void addNewMovie() {

        MaterialesCaracteristicas matCat = new MaterialesCaracteristicas();

        EditText materialName = (EditText) findViewById(R.id.txtMovieNameAdd);
        EditText alfa125 = (EditText) findViewById(R.id.txtGenreAdd);
        EditText alfa250 = (EditText) findViewById(R.id.txtYearAdd);
        EditText alfa500 = (EditText) findViewById(R.id.txtRatingAdd);
        EditText alfa1000 = (EditText) findViewById(R.id.txt1000Add);
        EditText alfa2000 = (EditText) findViewById(R.id.txt2000Add);
        EditText alfa4000 = (EditText) findViewById(R.id.txt4000Add);

        if (!STRING_EMPTY.equals(materialName.getText().toString()) &&
        !STRING_EMPTY.equals(alfa125.getText().toString()) &&
        !STRING_EMPTY.equals(alfa250.getText().toString()) &&
        !STRING_EMPTY.equals(alfa500.getText().toString()) &&
        !STRING_EMPTY.equals(alfa1000.getText().toString()) &&
        !STRING_EMPTY.equals(alfa2000.getText().toString()) &&
        !STRING_EMPTY.equals(alfa4000.getText().toString())   ) {

        matCat.setMaterialName(materialName.getText().toString());
        matCat.setAlfa125(Float.parseFloat(alfa125.getText().toString()));
        matCat.setAlfa250(Float.parseFloat(alfa250.getText().toString()));
        matCat.setAlfa500(Float.parseFloat(alfa500.getText().toString()));
        matCat.setAlfa1000(Float.parseFloat(alfa1000.getText().toString()));
        matCat.setAlfa2000(Float.parseFloat(alfa2000.getText().toString()));
        matCat.setAlfa4000(Float.parseFloat(alfa4000.getText().toString()));

        dbHelper.addMaterials(matCat);
        populateSpinner();
        materialName.setText("");
        alfa125.setText("");
        alfa250.setText("");
        alfa500.setText("");
        alfa1000.setText("");
        alfa2000.setText("");
        alfa4000.setText("");

        } else {
        Toast.makeText(Materials_Base.this, "One or more fields left empty!",
        Toast.LENGTH_LONG).show();
        }
        }

private void populateSpinner() {
        List<MaterialesCaracteristicas> matCatList = dbHelper.getAllMaterials();
        List<MovieSpinnerVO> matSpinnerList = new ArrayList<>();
        for (int i = 0; i < matCatList.size(); i++) {

            MaterialesCaracteristicas matCatVO = matCatList.get(i);

            MovieSpinnerVO matSpinnerVO = new MovieSpinnerVO(matCatVO.getMaterialId(), matCatVO.getMaterialName());

        matSpinnerList.add(matSpinnerVO);


        }
        if(matCatList.size() == 0){
        LinearLayout displayArea = (LinearLayout) findViewById(R.id.displayArea);
        displayArea.setVisibility(LinearLayout.GONE);

        LinearLayout editButton = (LinearLayout) findViewById(R.id.editButton);
        editButton.setVisibility(LinearLayout.GONE);

        LinearLayout editArea = (LinearLayout) findViewById(R.id.editArea);
        editArea.setVisibility(LinearLayout.GONE);

        LinearLayout movieSpinnerLayout = (LinearLayout) findViewById(R.id.movieSpinnerLayout);
        movieSpinnerLayout.setVisibility(LinearLayout.GONE);

        }else{
        LinearLayout movieSpinnerLayout = (LinearLayout) findViewById(R.id.movieSpinnerLayout);
        movieSpinnerLayout.setVisibility(LinearLayout.VISIBLE);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter(
        this, android.R.layout.simple_spinner_item, matSpinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.movieSpinner);
        sItems.setAdapter(adapter);
        sItems.setOnItemSelectedListener(this);

        }

public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MovieSpinnerVO movieSpinnerVO = (MovieSpinnerVO) sItems.getSelectedItem();
        int movieId = movieSpinnerVO.getMovieId();
        selectedMovieId = movieId;

        MaterialesCaracteristicas matCatVO = dbHelper.getMaterial(movieId);

        TextView txtMovieNameDisplay = (TextView) findViewById(R.id.txtMovieNameDisplay);
        txtMovieNameDisplay.setText(matCatVO.getMaterialName());

        TextView txtGenreDisplay = (TextView) findViewById(R.id.txtGenreDisplay);
        String a125 = Float.toString(matCatVO.getAlfa125());
        txtGenreDisplay.setText(a125);

        TextView txtYearDisplay = (TextView) findViewById(R.id.txtYearDisplay);
        String a250 = Float.toString(matCatVO.getAlfa250());
        txtYearDisplay.setText(a250);

        TextView txtRatingDisplay = (TextView) findViewById(R.id.txtRatingDisplay);
        String a500 = Float.toString(matCatVO.getAlfa500());
        txtRatingDisplay.setText(a500);

        TextView txt1000display = (TextView) findViewById(R.id.txtAlfa1000);
        String a1000 = Float.toString(matCatVO.getAlfa1000());
        txt1000display.setText(a1000);

        TextView txt2000display = (TextView) findViewById(R.id.txtAlfa2000);
        String a2000 = Float.toString(matCatVO.getAlfa2000());
        txt2000display.setText(a2000);

        TextView txt4000display = (TextView) findViewById(R.id.txtAlfa4000);
        String a4000 = Float.toString(matCatVO.getAlfa4000());
        txt4000display.setText(a4000);


        LinearLayout displayArea = (LinearLayout) findViewById(R.id.displayArea);
        displayArea.setVisibility(LinearLayout.VISIBLE);

        LinearLayout editButton = (LinearLayout) findViewById(R.id.editButton);
        editButton.setVisibility(LinearLayout.VISIBLE);

        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {

        editMaterial();
        }
        });

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        deleteMovie();
        }
        });

        }

public void onNothingSelected(AdapterView<?> parent) {
        }

private void editMaterial() {

        MaterialesCaracteristicas matCatVO = null;

        EditText movieName = (EditText) findViewById(R.id.txtMovieNameEdit);
        EditText genre = (EditText) findViewById(R.id.txtGenreEdit);
        EditText year = (EditText) findViewById(R.id.txtYearEdit);
        EditText rating = (EditText) findViewById(R.id.txtRatingEdit);
        EditText alfa1000 = (EditText) findViewById(R.id.txt1000EDIT);
        EditText alfa2000 = (EditText) findViewById(R.id.txt2000EDIT);
        EditText alfa4000 = (EditText) findViewById(R.id.txt4000EDIT);

        if(isEdit){

        if (!STRING_EMPTY.equals(movieName.getText().toString()) &&
        !STRING_EMPTY.equals(genre.getText().toString()) &&
        !STRING_EMPTY.equals(year.getText().toString()) &&
        !STRING_EMPTY.equals(rating.getText().toString()) &&
        !STRING_EMPTY.equals(alfa1000.getText().toString())&&
        !STRING_EMPTY.equals(alfa2000.getText().toString())&&
        !STRING_EMPTY.equals(alfa4000.getText().toString())
                ) {

        matCatVO = new MaterialesCaracteristicas();

        LinearLayout displayArea = (LinearLayout) findViewById(R.id.displayArea);
        displayArea.setVisibility(LinearLayout.VISIBLE);
        LinearLayout editArea = (LinearLayout) findViewById(R.id.editArea);
        editArea.setVisibility(LinearLayout.GONE);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setVisibility(Button.VISIBLE);

        matCatVO.setMaterialId(selectedMovieId);
        matCatVO.setMaterialName(movieName.getText().toString());
        matCatVO.setAlfa125(Float.parseFloat(genre.getText().toString()));
        matCatVO.setAlfa250(Float.parseFloat(year.getText().toString()));
        matCatVO.setAlfa500(Float.parseFloat(rating.getText().toString()));
        matCatVO.setAlfa1000(Float.parseFloat(alfa1000.getText().toString()));
        matCatVO.setAlfa2000(Float.parseFloat(alfa2000.getText().toString()));
        matCatVO.setAlfa4000(Float.parseFloat(alfa4000.getText().toString()));

        dbHelper.updateMaterial(matCatVO);
        populateSpinner();
        sItems = (Spinner) findViewById(R.id.movieSpinner);
        sItems.setSelection(getSpinnerPosition(sItems));
        isEdit = false;

        } else {
        Toast.makeText(Materials_Base.this, "Existen campos vacios!",
        Toast.LENGTH_LONG).show();
        }

        }else{
        matCatVO = dbHelper.getMaterial(selectedMovieId);
        LinearLayout displayArea = (LinearLayout) findViewById(R.id.displayArea);
        displayArea.setVisibility(LinearLayout.GONE);
        LinearLayout editArea = (LinearLayout) findViewById(R.id.editArea);
        editArea.setVisibility(LinearLayout.VISIBLE);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setVisibility(Button.INVISIBLE);

        movieName.setText(matCatVO.getMaterialName());
        genre.setText(Float.toString(matCatVO.getAlfa125()));
        year.setText(Float.toString(matCatVO.getAlfa250()));
        rating.setText(Float.toString(matCatVO.getAlfa500()));
        alfa1000.setText(Float.toString(matCatVO.getAlfa1000()));
        alfa2000.setText(Float.toString(matCatVO.getAlfa2000()));
        alfa4000.setText(Float.toString(matCatVO.getAlfa4000()));

        isEdit = true;

        }

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

private void deleteMovie(){
        dbHelper.deleteMaterial(selectedMovieId);
        populateSpinner();
        }


}
