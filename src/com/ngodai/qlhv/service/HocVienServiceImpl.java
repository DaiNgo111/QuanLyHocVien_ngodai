/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.service;

import com.ngodai.qlhv.dao.HocVienDAO;
import com.ngodai.qlhv.dao.HocVienDAOImpl;
import com.ngodai.qlhv.model.HocVien;
import java.util.List;

/**
 *
 * @author ngoda
 */
public class HocVienServiceImpl implements HocVienService{
    
    private HocVienDAO hocVienDAO = null;

    public HocVienServiceImpl() {
        hocVienDAO = new HocVienDAOImpl();
    }
    
    
    
    @Override
    public List<HocVien> getList() {
       
        return hocVienDAO.getList();
    }

    @Override
    public int createOrUpdate(HocVien hocVien) {
        return hocVienDAO.createOrUpdate(hocVien);
    }
    
    
    
}
