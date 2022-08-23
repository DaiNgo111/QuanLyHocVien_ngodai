/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.controller;

import com.ngodai.qlhv.model.KhoaHoc;
import com.ngodai.qlhv.service.KhoaHocService;
import com.ngodai.qlhv.service.KhoaHocServiceImpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ngoda
 */
public class KhoaHocController {
    private JButton btnSubmit;
    private JTextField jtfMaKhoaHoc;
    private JTextField jtfTenKhoaHoc;
    private JTextArea jtaMoTa;
    private JDateChooser jdcNgayBatDau;
    private JDateChooser jdcNgayKetThuc;
    
    private JTextField jtfTinhTrang;
    private JLabel jlbMsg;
    
    private KhoaHoc khoaHoc = null;
    
    private KhoaHocService khoaHocService = null;

    public KhoaHocController(JButton btnSubmit, JTextField jtfMaKhoaHoc, JTextField jtfTenKhoaHoc, JTextArea jtaMoTa, JDateChooser jdcNgayBatDau, JDateChooser jdcNgayKetThuc, JTextField jtfTinhTrang, JLabel jlbMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaKhoaHoc = jtfMaKhoaHoc;
        this.jtfTenKhoaHoc = jtfTenKhoaHoc;
        this.jtaMoTa = jtaMoTa;
        this.jdcNgayBatDau = jdcNgayBatDau;
        this.jdcNgayKetThuc = jdcNgayKetThuc;
        this.jtfTinhTrang = jtfTinhTrang;
        this.jlbMsg = jlbMsg;
        
        this.khoaHocService = new KhoaHocServiceImpl();
    }

    

    

    

    
    
    public void setView(KhoaHoc khoaHoc){
        this.khoaHoc = khoaHoc;
        
        jtfMaKhoaHoc.setText("#" + khoaHoc.getMa_khoa_hoc());
        jtfTenKhoaHoc.setText(khoaHoc.getTen_khoa_hoc());
        jtaMoTa.setText(khoaHoc.getMo_ta());
        jdcNgayBatDau.setDate(khoaHoc.getNgay_bat_dau());
        jdcNgayKetThuc.setDate(khoaHoc.getNgay_ket_thuc());
        
        jtfTinhTrang.setText(khoaHoc.isTinh_trang()?"Đang học":"Kết thúc");
        
          
    }
       
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
               if(jtfTenKhoaHoc.getText().length() == 0 || jdcNgayBatDau.getDate() == null){
                   jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
               }else{
                   khoaHoc.setTen_khoa_hoc(jtfTenKhoaHoc.getText());
                   int ma =Integer.parseInt(jtfMaKhoaHoc.getText());
                   khoaHoc.setMa_khoa_hoc(ma);
                   khoaHoc.setMo_ta(jtaMoTa.getText());
                   khoaHoc.setNgay_bat_dau(jdcNgayBatDau.getDate());
                   khoaHoc.setNgay_ket_thuc(jdcNgayKetThuc.getDate());
                   
                   khoaHoc.setTinh_trang((jtfTinhTrang.getText().equals("Đang học"))?true: false );
                   int lastId = khoaHocService.createOrUpdate(khoaHoc);
                   if(lastId > 0){
                   khoaHoc.setMa_khoa_hoc(lastId);
                   jtfMaKhoaHoc.setText("#" + lastId);
                   jlbMsg.setText("Cập nhật dữ liệu thành công!");
                   }
               }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }
            
            
            
        });
    }
}
