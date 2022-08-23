/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.dao;

import com.ngodai.qlhv.model.HocVien;
import java.util.List;

/**
 *
 * @author ngoda
 */
public interface HocVienDAO {
    
    public List<HocVien> getList();
    
    public int createOrUpdate(HocVien hocVien);
}
