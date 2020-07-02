package com.professionalandroid.apps.myapplication;

import java.io.Serializable;

public class Notepad implements Serializable {

    int seq;
    String maintext;
    String subtext;
    int isdone;

    public Notepad(String maintext, String subtext, int isdone){
        this.maintext = maintext;
        this.subtext = subtext;
        this.isdone = isdone;
    }

    public Notepad(int seq, String maintext, String subtext, int isdone) {
        this.seq = seq;
        this.maintext = maintext;
        this.subtext = subtext;
        this.isdone = isdone;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public int getIsdone() {
        return isdone;
    }

    public void setIsdone(int isdone) {
        this.isdone = isdone;
    }
}
