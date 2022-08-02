package com.example.juank.schroederapp;

/**
 * Created by Juank on 20/08/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.juank.schroederapp.MaterialesCaracteristicas;

import java.util.ArrayList;
import java.util.List;


public class SQLiteDBHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "SchroederAppDB";

    static final String TABLE_NAME = "Coeficientes";
    static final String TABLE_NAME_2 = "Superficies";

    public static final String KEY_MATERIAL_ID = "material_id";
    public static final String KEY_SUP_ID = "sup_id";

    static final String KEY_MATERIAL = "material";
    static final String KEY_ALFA125 = "a125";
    static final String KEY_ALFA250= "a250";
    static final String KEY_ALFA500 = "a500";
    static final String KEY_ALFA1000 = "a1000";
    static final String KEY_ALFA2000 = "a2000";
    static final String KEY_ALFA4000 = "a4000";

    static final String KEY_SUPERFICIE = "sup";
    static final String KEY_EJE = "axis";
    static final String KEY_MAT = "mat";

    //Create Table Query
    private static final String SQL_CREATE_COEFICIENTES =
            "CREATE TABLE Coeficientes (" + KEY_MATERIAL_ID + "  INTEGER PRIMARY KEY, "
                    +  KEY_MATERIAL + " TEXT, " + KEY_ALFA125 + "  REAL, "
                    + KEY_ALFA250 + "  REAL, " + KEY_ALFA500 + "  REAL, " + KEY_ALFA1000 + "  REAL, " + KEY_ALFA2000 +  "  REAL, " + KEY_ALFA4000 + "  REAL);";

    private static final String SQL_CREATE_SUPERFICIES =
            "CREATE TABLE Superficies (" + KEY_SUP_ID + "  INTEGER PRIMARY KEY, "
                    + KEY_SUPERFICIE + "  REAL, "+ KEY_EJE + "  REAL, "+ KEY_MAT + "  REAL);";

    private static final String SQL_DELETE_COEFICIENTES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_DELETE_SUPERFICIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COEFICIENTES);
        db.execSQL(SQL_CREATE_SUPERFICIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop the table while upgrading the database
        // such as adding new column or changing existing constraint
        db.execSQL(SQL_DELETE_COEFICIENTES);
        db.execSQL(SQL_DELETE_SUPERFICIES);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }

    public long createTableSup(Superficie sup) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SUPERFICIE, sup.getSuperficie());
        values.put(KEY_EJE, sup.getAxis());
        values.put(KEY_MAT, sup.getMat());


        // insert row
        long tag_id = db.insert(TABLE_NAME_2, null, values);
        db.close();
        return tag_id;

    }


    public long addMaterials(MaterialesCaracteristicas mat) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //Create a map having materials details to be inserted
        ContentValues material_details = new ContentValues();

        material_details.put(KEY_MATERIAL, mat.getMaterialName());
        material_details.put(KEY_ALFA125, mat.getAlfa125());
        material_details.put(KEY_ALFA250, mat.getAlfa250());
        material_details.put(KEY_ALFA500, mat.getAlfa500());
        material_details.put(KEY_ALFA1000, mat.getAlfa1000());
        material_details.put(KEY_ALFA2000, mat.getAlfa2000());
        material_details.put(KEY_ALFA4000, mat.getAlfa4000());



        long newRowId = db.insert(TABLE_NAME, null, material_details);
        db.close();
        return newRowId;


    }

    public List getAllMaterials() {
        List materialesCaracteristricasList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_NAME
                + " ORDER BY " + KEY_MATERIAL_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //if TABLE has rows
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                MaterialesCaracteristicas matCat = new MaterialesCaracteristicas();

                matCat.setMaterialId(cursor.getInt(0));
                matCat.setMaterialName(cursor.getString(1));
                matCat.setAlfa125(cursor.getFloat(2));
                matCat.setAlfa250(cursor.getFloat(3));
                matCat.setAlfa500(cursor.getFloat(4));
                matCat.setAlfa1000(cursor.getFloat(5));
                matCat.setAlfa2000(cursor.getFloat(6));
                matCat.setAlfa4000(cursor.getFloat(7));
                //Add movie details to list
                materialesCaracteristricasList .add(matCat);
            } while (cursor.moveToNext());
        }
        db.close();
        return materialesCaracteristricasList;
    }

    public List<Superficie> getAllSups() {

        List<Superficie> Supslist = new ArrayList();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_2+ " ORDER BY " + KEY_SUP_ID + " DESC";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Superficie s = new Superficie();
                s.setSupId(c.getInt(0));
                s.setSuperficie(c.getFloat(1));
                s.setAxis(c.getFloat(2));
                s.setMat(c.getInt(3));

                // adding to tags list
                Supslist.add(s);
            } while (c.moveToNext());
        }
        db.close();
        return Supslist;

        //revisada
    }


    //in
    public Superficie getSuperficie(int superficie_id) {

        Superficie sup = new Superficie();
        SQLiteDatabase db = this.getReadableDatabase();

        //specify the columns to be fetched
        String[] columns = {KEY_SUP_ID,KEY_SUPERFICIE,KEY_EJE,KEY_MAT};
        //Select condition
        String selection = KEY_SUP_ID + " = ?";
        //Arguments for selection
        String[] selectionArgs = {String.valueOf(superficie_id)};


        Cursor cursor = db.query(TABLE_NAME_2, columns, selection,
                selectionArgs, null, null, null);

        if (null != cursor) {
            cursor.moveToFirst();
            sup.setSupId(cursor.getInt(0));
            sup.setSuperficie(cursor.getFloat(1));
            sup.setAxis(cursor.getFloat(2));
            sup.setMat(cursor.getInt(3));

        }
        db.close();
        return sup;

    }
    //end


    public MaterialesCaracteristicas getMaterial(int material_id) {

        MaterialesCaracteristicas matCat = new MaterialesCaracteristicas();
        SQLiteDatabase db = this.getReadableDatabase();
        //specify the columns to be fetched
        String[] columns = {KEY_MATERIAL_ID, KEY_MATERIAL, KEY_ALFA125, KEY_ALFA250, KEY_ALFA500, KEY_ALFA1000, KEY_ALFA2000, KEY_ALFA4000};
        //Select condition
        String selection = KEY_MATERIAL_ID + " = ?";
        //Arguments for selection
        String[] selectionArgs = {String.valueOf(material_id)};


        Cursor cursor = db.query(TABLE_NAME, columns, selection,
                selectionArgs, null, null, null);

        if (null != cursor) {
            cursor.moveToFirst();
            matCat.setMaterialId(cursor.getInt(0));
            matCat.setMaterialName(cursor.getString(1));
            matCat.setAlfa125(cursor.getFloat(2));
            matCat.setAlfa250(cursor.getFloat(3));
            matCat.setAlfa500(cursor.getFloat(4));
            matCat.setAlfa1000(cursor.getFloat(5));
            matCat.setAlfa2000(cursor.getFloat(6));
            matCat.setAlfa4000(cursor.getFloat(7));

        }
        db.close();
        return matCat;

    }

    public void updateSup(Superficie sup) {

        SQLiteDatabase db = this.getWritableDatabase();
        String supsIds[] = {String.valueOf(sup.getsSupId())};

        ContentValues sups_details = new ContentValues();

        sups_details.put(KEY_SUPERFICIE, sup.getSuperficie());
        sups_details.put(KEY_EJE, sup.getAxis());
        sups_details.put(KEY_MAT,sup.getMat());

        db.update(TABLE_NAME_2, sups_details, KEY_SUP_ID + " = ?", supsIds);
        db.close();
    }

    public void updateMaterial(MaterialesCaracteristicas mat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String materialsIds[] = {String.valueOf(mat.getMaterialId())};

        ContentValues material_details = new ContentValues();
        material_details.put(KEY_MATERIAL, mat.getMaterialName());
        material_details.put(KEY_ALFA125, mat.getAlfa125());
        material_details.put(KEY_ALFA250, mat.getAlfa250());
        material_details.put(KEY_ALFA500, mat.getAlfa500());
        material_details.put(KEY_ALFA1000, mat.getAlfa1000());
        material_details.put(KEY_ALFA2000, mat.getAlfa2000());
        material_details.put(KEY_ALFA4000, mat.getAlfa4000());


        db.update(TABLE_NAME, material_details, KEY_MATERIAL_ID + " = ?", materialsIds);
        db.close();
    }

    public void deleteMaterial(int materialID) {
        String materialsIds[] = {String.valueOf(materialID)};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_MATERIAL_ID + " = ?", materialsIds);
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_2,null,null);
        db.close();
    }

    public void delete(String id)
    {
        String[] args={id};
        getWritableDatabase().delete("texts", "_ID=?", args);
    }



}
