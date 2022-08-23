/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.utility;

import com.ngodai.qlhv.model.HocVien;
import com.ngodai.qlhv.model.KhoaHoc;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngoda
 */
public class ClassTableModel {
    public DefaultTableModel setTableModelHocVien(List<HocVien> listItem, String[] listColumn){
       DefaultTableModel dtm = new DefaultTableModel(){
           @Override
           public boolean isCellEditable(int row, int column) {
              return false;
           }
           
           
           // hàm này biến cái tình trang học thành kiểu tích, không có nó nó hiện true false
           @Override
           public Class<?> getColumnClass(int columnIndex) {
              return columnIndex == 7 ? Boolean.class : String.class;
           }
           
       };
       dtm.setColumnIdentifiers(listColumn);
       int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for(int i=0; i< rows;i++){
                HocVien hocVien = listItem.get(i);
                obj = new Object[columns];
                obj[0] = hocVien.getMa_hoc_vien();
                obj[1] = (i+1);
                obj[2] = hocVien.getHo_ten();
                obj[3] = hocVien.getNgay_sinh();
                obj[4] = hocVien.isGioi_tinh()==true? "Nam":"Nữ"; 
                obj[5] = hocVien.getSo_dien_thoai();
                obj[6] = hocVien.getDia_chi(); 
                obj[7] = hocVien.isTinh_trang();
                dtm.addRow(obj);
            }
        }
        
        
       return dtm;
    }
    
    public DefaultTableModel setTableModelKhoaHoc(List<KhoaHoc> listItem, String[] listColumn){
       DefaultTableModel dtm = new DefaultTableModel(){
           @Override
           public boolean isCellEditable(int row, int column) {
              return false;
           }
           
           
         
           
       };
       dtm.setColumnIdentifiers(listColumn);
       int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for(int i=0; i< rows;i++){
                KhoaHoc khoaHoc = listItem.get(i);
                obj = new Object[columns];
                obj[0] = khoaHoc.getMa_khoa_hoc();
                obj[1] = (i+1);
                obj[2] = khoaHoc.getTen_khoa_hoc();
                obj[3] = khoaHoc.getMo_ta();
                obj[4] = khoaHoc.getNgay_bat_dau(); 
                obj[5] = khoaHoc.getNgay_ket_thuc();
                 
                obj[6] = khoaHoc.isTinh_trang()==true? "Đang Học":"Kết Thúc";
                
                //obj[4] = hocVien.isGioi_tinh()==true? "Nam":"Nữ";
                dtm.addRow(obj);
            }
        }
        
        
       return dtm;
    }
}
