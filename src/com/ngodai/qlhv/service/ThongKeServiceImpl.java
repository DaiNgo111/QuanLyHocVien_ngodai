/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.service;

import com.ngodai.qlhv.bean.KhoaHocBean;
import com.ngodai.qlhv.bean.LopHocBean;
import com.ngodai.qlhv.dao.ThongKeDAO;
import com.ngodai.qlhv.dao.ThongKeDAOImpl;
import java.util.List;

/**
 *
 * @author ngoda
 */
public class ThongKeServiceImpl implements ThongKeService{

    private ThongKeDAO thongKeDAO = null;
    
    public ThongKeServiceImpl(){
        thongKeDAO = new ThongKeDAOImpl();
    }
    
    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDAO.getListByLopHoc();
    }

    @Override
    public List<KhoaHocBean> getListByKhoaHoc() {
       return thongKeDAO.getListByKhoaHoc();
    }
    
}
