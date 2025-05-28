package com.emirercan.HastaneRandevuSistemi;

public class Hasta {
    private static int sayac = 1;

    private int id;
    private String ad;
    private String soyad;
    private String tc;

    public Hasta(String ad, String soyad, String tc) {
        this.id = sayac++;
        this.ad = ad;
        this.soyad = soyad;
        this.tc = tc;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getTc() {
        return tc;
    }

    public String toString() {
        return ad + " " + soyad;
    }

}
