package com.emirercan.HastaneRandevuSistemi.Arayuz;

import com.emirercan.HastaneRandevuSistemi.Hasta;
import com.emirercan.HastaneRandevuSistemi.Veritabani;

import javax.swing.*;
import java.awt.*;

public class HastaGUI extends JFrame {

    public HastaGUI() {
        setTitle("Hasta İşlemleri");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton ekleBtn = new JButton("Yeni Hasta Ekle");
        JButton silBtn = new JButton("Hasta Sil");
        JButton listeBtn = new JButton("Hastaları Listele");
        JButton geriBtn = new JButton("↩ Geri Dön");

        ekleBtn.addActionListener(e -> hastaEkleFormu());
        silBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Silme henüz hazır değil."));
        listeBtn.addActionListener(e -> hastalariListele());
        geriBtn.addActionListener(e -> dispose());

        add(ekleBtn);
        add(silBtn);
        add(listeBtn);
        add(geriBtn);

        setVisible(true);
    }

    private void hastaEkleFormu() {
        JTextField adField = new JTextField();
        JTextField soyadField = new JTextField();
        JTextField tcField = new JTextField();

        Object[] mesaj = {
                "Ad:", adField,
                "Soyad:", soyadField,
                "TC:", tcField
        };

        int secim = JOptionPane.showConfirmDialog(this, mesaj, "Yeni Hasta Ekle", JOptionPane.OK_CANCEL_OPTION);

        if (secim == JOptionPane.OK_OPTION) {
            String ad = adField.getText();
            String soyad = soyadField.getText();
            String tc = tcField.getText();

            if (ad.isEmpty() || soyad.isEmpty() || tc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Tüm alanlar doldurulmalı.");
                return;
            }

            Hasta yeni = new Hasta(ad, soyad, tc);
            Veritabani.hastalar.add(yeni);
            JOptionPane.showMessageDialog(this, "✅ Hasta eklendi: " + yeni.getAd() + " " + yeni.getSoyad());
        }
    }

    private void hastalariListele() {
        if (Veritabani.hastalar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hasta listesi boş.");
            return;
        }

        String[] kolonlar = { "ID", "Ad", "Soyad", "TC Kimlik No" };
        Object[][] veri = new Object[Veritabani.hastalar.size()][kolonlar.length];

        for (int i = 0; i < Veritabani.hastalar.size(); i++) {
            Hasta h = Veritabani.hastalar.get(i);
            veri[i][0] = h.getId();
            veri[i][1] = h.getAd();
            veri[i][2] = h.getSoyad();
            veri[i][3] = h.getTc();
        }

        new TabloGUI(kolonlar, veri, id -> {
            Veritabani.hastalar.removeIf(h -> h.getId() == id);
        });
    }
}
