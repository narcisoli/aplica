package com.example.intern06.lifereminder.obiecte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intern02 on 19.05.2017.
 */

public class reminder {
    int status;
    String text;
    String data;
    int culoaretext;
    int culoarefundal;
    int fixeaza;


    public int getCuloaretext() {
        return culoaretext;
    }

    public void setCuloaretext(int culoaretext) {
        this.culoaretext = culoaretext;
    }

    public int getCuloarefundal() {
        return culoarefundal;
    }

    public void setCuloarefundal(int culoarefundal) {
        this.culoarefundal = culoarefundal;
    }


    public reminder() {
    }

    public int getFixeaza() {
        return fixeaza;
    }

    public void setFixeaza(int fixeaza) {
        this.fixeaza = fixeaza;
    }

    public reminder(int status, String text, String data, int culoaretext, int culoarefundal, int fixeaza) {
        this.status = status;
        this.text = text;
        this.data = data;
        this.culoaretext = culoaretext;
        this.culoarefundal = culoarefundal;
        this.fixeaza = fixeaza;

    }

    public int getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public String getData() {
        return data;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setData(String data) {
        this.data = data;
    }


}
