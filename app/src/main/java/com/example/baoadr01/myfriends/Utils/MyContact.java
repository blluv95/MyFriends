package com.example.baoadr01.myfriends.Utils;

/**
 * Created by BaoADR01 on 8/18/2015.
 */
public class MyContact {
    String NAME;
    String SDT;
    byte []IMAGE;
    int ID;

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
