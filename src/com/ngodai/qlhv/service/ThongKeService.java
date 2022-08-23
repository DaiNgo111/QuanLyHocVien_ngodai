/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.service;

import com.ngodai.qlhv.bean.KhoaHocBean;
import com.ngodai.qlhv.bean.LopHocBean;
import java.util.List;

/**
 *
 * @author ngoda
 */
public interface ThongKeService {
    
    public List<LopHocBean> getListByLopHoc();
    
    public List<KhoaHocBean> getListByKhoaHoc();
}
