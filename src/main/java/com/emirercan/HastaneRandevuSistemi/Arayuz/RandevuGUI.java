package com.emirercan.HastaneRandevuSistemi.Arayuz;

import com.emirercan.HastaneRandevuSistemi.Doktor;
import com.emirercan.HastaneRandevuSistemi.Hasta;
import com.emirercan.HastaneRandevuSistemi.Randevu;
import com.emirercan.HastaneRandevuSistemi.Veritabani;
import javax.swing.*;
import java.awt.*;

public class RandevuGUI extends JFrame {

    public RandevuGUI() {
        setTitle("Randevu İşlemleri");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton olusturBtn = new JButton("Yeni Randevu Oluştur");
        JButton listeBtn = new JButton("Randevuları Listele");
        JButton geriBtn = new JButton("↩ Geri Dön");

        olusturBtn.addActionListener(e -> randevuOlusturFormu());
        listeBtn.addActionListener(e -> randevuListele());
        geriBtn.addActionListener(e -> dispose());

        add(olusturBtn);
        add(listeBtn);
        add(geriBtn);

        setVisible(true);
    }

    private void randevuOlusturFormu() {
        if (Veritabani.hastalar.isEmpty() || Veritabani.doktorlar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Hasta ve doktor kayıtlı olmalı.");
            return;
        }

        JComboBox<Hasta> hastaCombo = new JComboBox<>(Veritabani.hastalar.toArray(new Hasta[0]));
        JComboBox<Doktor> doktorCombo = new JComboBox<>(Veritabani.doktorlar.toArray(new Doktor[0]));
        JTextField tarihField = new JTextField("2025-05-21 14:00");

        Object[] mesaj = {
                "com.emirercan.Hasta Seç:", hastaCombo,
                "com.emirercan.Doktor Seç:", doktorCombo,
                "Tarih (yyyy-MM-dd HH:mm):", tarihField
        };

        int secim = JOptionPane.showConfirmDialog(this, mesaj, "Yeni Randevu", JOptionPane.OK_CANCEL_OPTION);

        if (secim == JOptionPane.OK_OPTION) {
            Hasta seciliHasta = (Hasta) hastaCombo.getSelectedItem();
            Doktor seciliDoktor = (Doktor) doktorCombo.getSelectedItem();
            String tarih = tarihField.getText();

            if (tarih.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tarih girilmelidir.");
                return;
            }

            Randevu randevu = new Randevu(seciliHasta, seciliDoktor, tarih);
            Veritabani.randevular.add(randevu);

            JOptionPane.showMessageDialog(this, "✅ Randevu oluşturuldu!");
        }
    }

    private void randevuListele() {
        if (Veritabani.randevular.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Randevu yok");
            return;
        }

        String[] kolonlar = { "ID", "Hasta Adı", "Hasta Soyadı", "Doktor Adı", "Doktor Soyadı", "Branş", "Tarih" };
        Object[][] veri = new Object[Veritabani.randevular.size()][kolonlar.length];

        for (int i = 0; i < Veritabani.randevular.size(); i++) {
            Randevu r = Veritabani.randevular.get(i);
            veri[i][0] = r.getId();
            veri[i][1] = r.getHasta().getAd();
            veri[i][2] = r.getHasta().getSoyad();
            veri[i][3] = r.getDoktor().getAd();
            veri[i][4] = r.getDoktor().getSoyad();
            veri[i][5] = r.getDoktor().getBrans();
            veri[i][6] = r.getTarih();
        }

        new TabloGUI(kolonlar, veri, id -> {
            Veritabani.randevular.removeIf(r -> r.getId() == id);
        });

    }


}