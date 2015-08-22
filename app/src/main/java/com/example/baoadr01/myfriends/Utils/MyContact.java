package com.example.baoadr01.myfriends.Utils;

/**
 * Created by BaoADR01 on 8/18/2015.
 */
public class MyContact {
    String NAME;
    String SDT;
    byte []IMAGE;
    int ID;
    String CATEGORY;
    int ID_CHECKBOX;

    public int getID_CHECKBOX() {
        return ID_CHECKBOX;
    }

    public void setID_CHECKBOX(int ID_CHECKBOX) {
        this.ID_CHECKBOX = ID_CHECKBOX;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public byte[] getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(byte[] IMAGE) {
        this.IMAGE = IMAGE;
    }
}
