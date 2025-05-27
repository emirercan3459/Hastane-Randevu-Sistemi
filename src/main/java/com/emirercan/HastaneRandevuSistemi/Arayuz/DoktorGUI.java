package com.emirercan.HastaneRandevuSistemi.Arayuz;

import com.emirercan.HastaneRandevuSistemi.Doktor;
import com.emirercan.HastaneRandevuSistemi.Veritabani;

import javax.swing.*;
import java.awt.*;

public class DoktorGUI extends JFrame {

    public DoktorGUI() {
        setTitle("Doktor İşlemleri");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));
        JButton ekleBtn = new JButton("Yeni Doktor Ekle");
        JButton silBtn = new JButton("Doktor Sil");
        JButton listeBtn = new JButton("Doktorları Listele");
        JButton geriBtn = new JButton("↩ Geri Dön");
        ekleBtn.addActionListener(e -> doktorEkleFormu());
        silBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Silme henüz hazır değil."));
        listeBtn.addActionListener(e -> doktorlariListele());
        geriBtn.addActionListener(e -> dispose());
        add(ekleBtn);
        add(silBtn);
        add(listeBtn);
        add(geriBtn);
        setVisible(true);
    }

    private void doktorEkleFormu() {
        JTextField adField = new JTextField();
        JTextField soyadField = new JTextField();
        String[] branslar = { "Dahiliye", "Romatoloji", "Kardiyoloji", "Onkoloji", "Psikiyatri" };
        JComboBox<String> bransCombo = new JComboBox<>(branslar);
        Object[] mesaj = {"Ad:", adField,"Soyad:", soyadField,"Branş:", bransCombo};
        int secim = JOptionPane.showConfirmDialog(this, mesaj, "Yeni Doktor Kaydı", JOptionPane.OK_CANCEL_OPTION);
        if (secim == JOptionPane.OK_OPTION) {
            String ad = adField.getText().trim();
            String soyad = soyadField.getText().trim();
            String brans = (String) bransCombo.getSelectedItem();
            if (ad.isEmpty() || soyad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ad soyad boş bırakılamaz");
                return;
            }
            Doktor yeni = new Doktor(ad, soyad, brans);
            Veritabani.doktorlar.add(yeni);
            JOptionPane.showMessageDialog(this, "Yeni Doktor: " + yeni.getAd() + " " + yeni.getSoyad() + " (" + yeni.getBrans() + ")");
        }
    }


    private void doktorlariListele() {
        String[] kolonlar = { "ID", "Ad Soyad", "Branş" };
        Object[][] veri = new Object[Veritabani.doktorlar.size()][kolonlar.length];
        for (int i = 0; i < Veritabani.doktorlar.size(); i++) {
            Doktor d = Veritabani.doktorlar.get(i);
            veri[i][0] = d.getId();
            veri[i][1] = d.getAd() + " " + d.getSoyad();
            veri[i][2] = d.getBrans();
        }
        new TabloGUI(kolonlar, veri, id -> {
            Veritabani.doktorlar.removeIf(d -> d.getId() == id);
        });

    }
}
