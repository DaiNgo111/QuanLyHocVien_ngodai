/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.main;

import com.ngodai.qlhv.view.DangNhapJDialog;
import com.ngodai.qlhv.view.MainJFrame;

/**
 *
 * @author ngoda
 */
public class Main {
    public static void main(String[] args) {
       // new MainJFrame().setVisible(true);
        DangNhapJDialog dialog = new DangNhapJDialog(null, true);
        dialog.setTitle("Đăng Nhập Hệ Thống");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
