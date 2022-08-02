package com.example.juank.schroederapp;

/**
 * Created by Juank on 28/09/17.
 */

public class Superficie {

    private int superficieID,Mat;
    private float Superficie;
    private float Axis;

    public int getsSupId() {
        return superficieID;
    }

    public void setSupId(int superficieID) {
        this.superficieID = superficieID;
    }

    public Float getSuperficie() {
        return Superficie;
    }

    public void setSuperficie(Float Superficie) {
        this.Superficie = Superficie;
    }

    public Float getAxis() {
        return Axis;
    }

    public void setAxis(Float Axis) {
        this.Axis = Axis;
    }

    public int getMat() {
        return Mat;
    }

    public void setMat(int Mat) {
        this.Mat = Mat;
    }
}
