package com.emirercan.HastaneRandevuSistemi.Arayuz;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Randevu Sistemi");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        JButton hastaBtn = new JButton("Hasta İşlemleri");
        JButton doktorBtn = new JButton("Doktor İşlemleri");
        JButton randevuBtn = new JButton("Randevu İşlemleri");
        JButton cikisBtn = new JButton("Çıkış");
        hastaBtn.addActionListener(e -> new HastaGUI());
        doktorBtn.addActionListener(e -> new DoktorGUI());
        randevuBtn.addActionListener(e -> new RandevuGUI());
        cikisBtn.addActionListener(e -> System.exit(0));
        add(hastaBtn);
        add(doktorBtn);
        add(randevuBtn);
        add(cikisBtn);
        setVisible(true);
    }
}
