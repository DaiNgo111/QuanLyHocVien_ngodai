/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.service;

import com.ngodai.qlhv.dao.TaiKhoanDAO;
import com.ngodai.qlhv.dao.TaiKhoanDAOImpl;
import com.ngodai.qlhv.model.TaiKhoan;

/**
 *
 * @author ngoda
 */
public class TaiKhoanServiceImpl implements TaiKhoanService{
    
    private TaiKhoanDAO taiKhoanDAO = null;

    public TaiKhoanServiceImpl() {
        taiKhoanDAO = new TaiKhoanDAOImpl();
    }
    
    
    
    @Override
    public TaiKhoan login(String tenDangNhap, String matKhau) {
       return taiKhoanDAO.login(tenDangNhap, matKhau);
    }
    
}
