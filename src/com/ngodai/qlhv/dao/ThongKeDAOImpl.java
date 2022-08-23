/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.dao;

import com.ngodai.qlhv.bean.KhoaHocBean;
import com.ngodai.qlhv.bean.LopHocBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngoda
 */
public class ThongKeDAOImpl implements ThongKeDAO{

    @Override
    public List<LopHocBean> getListByLopHoc() {
       
        try {
            Connection cons = DBConnect.getConnetion();
            String sql="SELECT ngay_dang_ky, COUNT(*) AS so_luong FROM lop_hoc GROUP BY ngay_dang_ky";
            List<LopHocBean> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LopHocBean lopHocBean = new LopHocBean();
                lopHocBean.setNgayDangKy(rs.getString("ngay_dang_ky"));
                lopHocBean.setSoLuongHocVien(rs.getInt("so_luong"));
                list.add(lopHocBean);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KhoaHocBean> getListByKhoaHoc() {
        try {
            Connection cons = DBConnect.getConnetion();
            String sql="SELECT ten_khoa_hoc, ngay_bat_dau, ngay_ket_thuc FROM khoa_hoc WHERE tinh_trang = TRUE";
            List<KhoaHocBean> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhoaHocBean khoaHocBean = new KhoaHocBean();
                khoaHocBean.setTenKhoaHoc(rs.getString("ten_khoa_hoc"));
                khoaHocBean.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                khoaHocBean.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                list.add(khoaHocBean);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
}
