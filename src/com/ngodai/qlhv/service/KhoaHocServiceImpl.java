/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.service;

import com.ngodai.qlhv.dao.HocVienDAO;
import com.ngodai.qlhv.dao.HocVienDAOImpl;
import com.ngodai.qlhv.dao.KhoaHocDAO;
import com.ngodai.qlhv.dao.KhoaHocDAOImpl;
import com.ngodai.qlhv.model.HocVien;
import com.ngodai.qlhv.model.KhoaHoc;
import java.util.List;

/**
 *
 * @author ngoda
 */
public class KhoaHocServiceImpl implements KhoaHocService{

    private KhoaHocDAO khoaHocDAO = null;

    public KhoaHocServiceImpl() {
        khoaHocDAO = new KhoaHocDAOImpl();
    }
    
    
    
    @Override
    public List<KhoaHoc> getList() {
       
        return khoaHocDAO.getList();
    }

    @Override
    public int createOrUpdate(KhoaHoc khoaHoc) {
        return khoaHocDAO.createOrUpdate(khoaHoc);
    }
    
}
