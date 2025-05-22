package com.emirercan.HastaneRandevuSistemi;

public class Randevu {
    private static int sayac = 1;

    private int id;
    private Hasta hasta;
    private Doktor doktor;
    private String tarih;
     public Randevu(Hasta hasta, Doktor doktor, String tarih) {
        this.id = sayac++;
        this.hasta = hasta;
        this.doktor = doktor;
        this.tarih = tarih;
    }

    public int getId() {
        return id;
    }

    public Hasta getHasta() {
        return hasta;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public String getTarih() {
        return tarih;
    }
}
