package com.emirercan.HastaneRandevuSistemi.Arayuz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public class TabloGUI extends JFrame {

    private DefaultTableModel model;
    private JTable tablo;

    public TabloGUI(String[] basliklar, Object[][] veriler, Consumer<Integer> silmeCallback) {
        setTitle("Listeleme");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        model = new DefaultTableModel(veriler, basliklar) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablo = new JTable(model);
        tablo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane kaydirma = new JScrollPane(tablo);
        JButton silBtn = new JButton("Seçilen Veriyi Sil");
        silBtn.addActionListener(e -> {
            int secilenSatir = tablo.getSelectedRow();
            if (secilenSatir == -1) {
                JOptionPane.showMessageDialog(this, "Silinmesi istenilen veriyi seçmelisin");
                return;
            }
            int id = (int) model.getValueAt(secilenSatir, 0);
            int onay = JOptionPane.showConfirmDialog(this,
                    "Seçilen veriyi silmek istediğine emin misin?",
                    "Onay",
                    JOptionPane.YES_NO_OPTION);
            if (onay == JOptionPane.YES_OPTION) {
                silmeCallback.accept(id);
                model.removeRow(secilenSatir);
                JOptionPane.showMessageDialog(this, "Veri başarıyla silindi.");
            }
        });
        add(kaydirma, BorderLayout.CENTER);
        add(silBtn, BorderLayout.SOUTH);
        setVisible(true);
    }
}
