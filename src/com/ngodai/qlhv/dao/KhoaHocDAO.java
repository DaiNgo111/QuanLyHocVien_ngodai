/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.dao;

import com.ngodai.qlhv.model.KhoaHoc;
import java.util.List;

/**
 *
 * @author ngoda
 */
public interface KhoaHocDAO {
    public List<KhoaHoc> getList();
    
    public int createOrUpdate(KhoaHoc khoaHoc);
}
