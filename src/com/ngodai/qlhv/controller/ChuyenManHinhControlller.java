/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.controller;

import com.ngodai.qlhv.bean.DanhMucBean;
import com.ngodai.qlhv.view.HocVienJPanel;
import com.ngodai.qlhv.view.KhoaHocJPanel;
import com.ngodai.qlhv.view.ThongKeJPanel;
import com.ngodai.qlhv.view.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ngoda
 */
public class ChuyenManHinhControlller {
    
    private JPanel root;
    private String kindSElected="";
    
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhControlller(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSElected = "TrangChu";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    
       public void setEvent(List<DanhMucBean> listItem){
           this.listItem = listItem;
           for(DanhMucBean item : listItem){
               item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
           }
       }
       
       
       // tạo các kiểu và hiệu ứng cho click chuột
       class LabelEvent implements MouseListener{
           
        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        

        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind){
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "HocVien":
                    node = new HocVienJPanel();
                    break;
                case "KhoaHoc":
                    node = new KhoaHocJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                //các tùy chọn khác nữa...
                default:
                    node = new TrangChuJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSElected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(kindSElected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(76, 175, 80));
                jlbItem.setBackground(new Color(76, 175, 80));
            }
           
       }
    }
       
    private void setChangeBackgroud(String kind){
        for(DanhMucBean item: listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            }else{
                item.getJpn().setBackground(new Color(76, 175, 80));
                item.getJlb().setBackground(new Color(76, 175, 80));
            }
        }
    }
    
}
