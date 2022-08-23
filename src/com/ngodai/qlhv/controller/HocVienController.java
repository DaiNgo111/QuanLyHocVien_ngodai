/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.controller;

import com.ngodai.qlhv.model.HocVien;
import com.ngodai.qlhv.service.HocVienService;
import com.ngodai.qlhv.service.HocVienServiceImpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author ngoda
 */
public class HocVienController {
    private JButton btnSubmit;
    private JTextField jtfMaHocVien;
    private JTextField jtfHoTen;
    private JDateChooser jdcNgaySinh;
    private JRadioButton jrdNam;
    private JRadioButton jrdNu;
    private JTextField jtfSoDienThoai;
    private JTextArea jtaDiaChi;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;
    
    private HocVien hocVien = null;
    
    private HocVienService hocVienService = null;

    public HocVienController(JButton btnSubmit, JTextField jtfMaHocVien, JTextField jtfHoTen, JDateChooser jdcNgaySinh, JRadioButton jrdNam, JRadioButton jrdNu, JTextField jtfSoDienThoai, JTextArea jtaDiaChi, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaHocVien = jtfMaHocVien;
        this.jtfHoTen = jtfHoTen;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfSoDienThoai = jtfSoDienThoai;
        this.jtaDiaChi = jtaDiaChi;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;
        
        this.hocVienService = new HocVienServiceImpl();
    }

    

    
    
    public void setView(HocVien hocVien){
        this.hocVien = hocVien;
        
        jtfMaHocVien.setText("#" + hocVien.getMa_hoc_vien());
        jtfHoTen.setText(hocVien.getHo_ten());
        jdcNgaySinh.setDate(hocVien.getNgay_sinh());
        if(hocVien.isGioi_tinh()){
          jrdNam.setSelected(true);
          jrdNu.setSelected(false);
        }else{
            jrdNam.setSelected(false);
          jrdNu.setSelected(true);
        }
        jtfSoDienThoai.setText(hocVien.getSo_dien_thoai());
        jtaDiaChi.setText(hocVien.getDia_chi());
        jcbTinhTrang.setSelected(hocVien.isTinh_trang());
        
          
    }
       
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
               if(jtfHoTen.getText().length() == 0 || jdcNgaySinh.getDate() == null){
                   jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
               }else{
                   hocVien.setHo_ten(jtfHoTen.getText());
                   hocVien.setNgay_sinh(jdcNgaySinh.getDate());
                   hocVien.setGioi_tinh(jrdNam.isSelected());
                   hocVien.setDia_chi(jtaDiaChi.getText());
                   hocVien.setSo_dien_thoai(jtfSoDienThoai.getText());
                   hocVien.setTinh_trang(jcbTinhTrang.isSelected());
                   int lastId = hocVienService.createOrUpdate(hocVien);
                   if(lastId > 0){
                   hocVien.setMa_hoc_vien(lastId);
                   jtfMaHocVien.setText("#" + lastId);
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
