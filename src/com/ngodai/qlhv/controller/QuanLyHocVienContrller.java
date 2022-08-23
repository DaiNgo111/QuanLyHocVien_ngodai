/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.controller;

import com.ngodai.qlhv.model.HocVien;
import com.ngodai.qlhv.service.HocVienService;
import com.ngodai.qlhv.service.HocVienServiceImpl;
import com.ngodai.qlhv.utility.ClassTableModel;
import com.ngodai.qlhv.view.HocVienJFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ngoda
 */
public class QuanLyHocVienContrller {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnPrint;
    
    private HocVienService hocVienService = null;
    private String[] listColumn={"Mã học viên", "STT", "Họ và tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Tính trạng"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    

    public QuanLyHocVienContrller(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnPrint) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnPrint = btnPrint;
        this.hocVienService = new HocVienServiceImpl();
    }
    
    
    
    public void setDateToTable(){
        List<HocVien> listItem = hocVienService.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableModelHocVien(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        // chỉnh độ rộng của cột thôi
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        //
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && table.getSelectedRow()!=-1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    // đoạn này trả giá trị dòng cho selectedRowIndex
                    System.out.println(selectedRowIndex);
                    
                    HocVien hocVien = new HocVien();
                    // gám các thuộc tính cho đối tượng - dòng nào cột nào của model nhận được
                    hocVien.setMa_hoc_vien((int) model.getValueAt(selectedRowIndex, 0));
                    hocVien.setHo_ten(model.getValueAt(selectedRowIndex, 2).toString());
                    hocVien.setNgay_sinh((Date) model.getValueAt(selectedRowIndex, 3));
                    hocVien.setGioi_tinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    // neu ma thong tin trong thi hien thi chuoi rong len 
                    hocVien.setSo_dien_thoai(model.getValueAt(selectedRowIndex, 5) != null ? model.getValueAt(selectedRowIndex, 5).toString():"");
                    hocVien.setDia_chi(model.getValueAt(selectedRowIndex, 6) != null ? model.getValueAt(selectedRowIndex, 6).toString() : "");
                    hocVien.setTinh_trang((boolean)model.getValueAt(selectedRowIndex, 7));
                    
                    HocVienJFrame frame = new HocVienJFrame(hocVien);
                    frame.setTitle("Thông tin học viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    
                    
                }
                    
            }
            
        });
        
        
        table.getTableHeader().setFont(new Font("Arrial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100,50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
        
    }
    public void setEvent(){
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              HocVienJFrame frame = new HocVienJFrame(new HocVien());
              frame.setTitle("Thông tin học viên");
              frame.setLocationRelativeTo(null);
              frame.setResizable(false);
              frame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
            }
        });
        
        btnPrint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Học Viên");
                
                XSSFRow row = null;
                Cell cell = null;
                
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue("STT");
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Họ và tên");
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày sinh");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Giới tính");
                
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue("Số điện thoại");
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Địa chỉ");
                
                
                List<HocVien> listItem = hocVienService.getList();
                if(listItem!=null){
                    FileOutputStream fis = null;
                    try {
                        int s = listItem.size();
                        for(int i = 0; i < s; i++){
                            HocVien hocVien = listItem.get(i);
                            
                            row = sheet.createRow(4 + i);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i+1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(hocVien.getHo_ten());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(hocVien.getNgay_sinh().toString());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(hocVien.isGioi_tinh()? "Nam":"Nữ");
                            
                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(hocVien.getSo_dien_thoai());
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(hocVien.getDia_chi());
                            
                        }   //lu file
                        File f = new File("D:/hoc_vien.xlsx");
                        fis = new FileOutputStream(f);
                        workbook.write(fis);
                        fis.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(QuanLyHocVienContrller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(QuanLyHocVienContrller.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            fis.close();
                        } catch (IOException ex) {
                            Logger.getLogger(QuanLyHocVienContrller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                
                
                   
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnPrint.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPrint.setBackground(new Color(100, 221, 23));
            }
        });
    }
        
        
}
