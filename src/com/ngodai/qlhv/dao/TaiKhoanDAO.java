/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.dao;

import com.ngodai.qlhv.model.TaiKhoan;

/**
 *
 * @author ngoda
 */
public interface TaiKhoanDAO {
    public TaiKhoan login(String tdn, String mk);
}
