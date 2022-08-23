/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.dao;

import com.ngodai.qlhv.model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngoda
 */
public class TaiKhoanDAOImpl implements TaiKhoanDAO{

    @Override
    public TaiKhoan login(String tenDangNhap, String matKhau) {
        try {
            Connection cons = DBConnect.getConnetion();
            String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap LIKE ? AND mat_khau LIKE ?";
            TaiKhoan taiKhoan = null;
            
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                taiKhoan = new TaiKhoan();
                taiKhoan.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                taiKhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                taiKhoan.setMat_khau(rs.getString("mat_khau"));
                taiKhoan.setTinh_trang(rs.getBoolean("tinh_trang"));
            }
            ps.close();
            return taiKhoan;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
