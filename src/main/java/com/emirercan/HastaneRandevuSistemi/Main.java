package com.emirercan.HastaneRandevuSistemi;

import com.emirercan.HastaneRandevuSistemi.Arayuz.MainGUI;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Tema yüklenemedi.");
        }

        SwingUtilities.invokeLater(MainGUI::new);
    }
}
