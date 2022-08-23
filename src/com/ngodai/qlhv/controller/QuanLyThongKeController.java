/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.controller;

import com.ngodai.qlhv.bean.KhoaHocBean;
import com.ngodai.qlhv.bean.LopHocBean;
import com.ngodai.qlhv.service.ThongKeService;
import com.ngodai.qlhv.service.ThongKeServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 *
 * @author ngoda
 */
public class QuanLyThongKeController {
    
    private ThongKeService thongKeService = null;
    
    public QuanLyThongKeController(){
        thongKeService = new ThongKeServiceImpl();
        
    }
    
    public void setDataToChart1(JPanel jpnItem){
        List<LopHocBean> listItem = thongKeService.getListByLopHoc();
        
        if(listItem != null){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(LopHocBean item : listItem){
                dataset.addValue(item.getSoLuongHocVien(), "Hoc Viên", item.getNgayDangKy());
            }
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SỐ LƯỢNG HỌC VIÊN ĐANG KÝ", "Thời gian", "Số lượng", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            // set kich thuoc
            chartPanel.setPreferredSize(new Dimension(850 , 300));
            
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
            
        }
    }
    
    public void setDataToChart2(JPanel jpnItem){
        
        List<KhoaHocBean> listItem = thongKeService.getListByKhoaHoc();
        if(listItem !=  null){
            TaskSeriesCollection ds = new TaskSeriesCollection();
            
            TaskSeries taskSeries;
            Task task;
            
            for(KhoaHocBean item : listItem){
                taskSeries = new TaskSeries(item.getTenKhoaHoc());
                task = new Task(item.getTenKhoaHoc(), item.getNgayBatDau(), item.getNgayKetThuc());
                taskSeries.add(task);
                ds.add(taskSeries);
            }
            
            JFreeChart chart = ChartFactory.createGanttChart("THỐNG KÊ TÌNH TRẠNG KHÓA HỌC", "Khóa Học", "Thời Gian", ds);
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(850 , 300));
            
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }
}
