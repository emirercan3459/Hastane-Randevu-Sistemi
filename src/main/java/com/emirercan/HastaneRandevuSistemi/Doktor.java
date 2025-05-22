package com.emirercan.HastaneRandevuSistemi;

public class Doktor {
    private static int sayac = 1;

    private int id;
    private String ad;
    private String soyad;
    private String brans;

    public Doktor(String ad, String soyad, String brans) {
        this.id = sayac++;
        this.ad = ad;
        this.soyad = soyad;
        this.brans = brans;
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

    public String getBrans() {
        return brans;
    }

    @Override
    public String toString() {
        return ad + " " + soyad;
    }

}
