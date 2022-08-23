/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ngodai.qlhv.controller;

import com.ngodai.qlhv.model.HocVien;
import com.ngodai.qlhv.model.KhoaHoc;
import com.ngodai.qlhv.service.HocVienService;
import com.ngodai.qlhv.service.HocVienServiceImpl;
import com.ngodai.qlhv.service.KhoaHocService;
import com.ngodai.qlhv.service.KhoaHocServiceImpl;
import com.ngodai.qlhv.utility.ClassTableModel;
import com.ngodai.qlhv.view.HocVienJFrame;
import com.ngodai.qlhv.view.KhoaHocJFrame;
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
public class QuanLyKhoaHocController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnPrint;
    
    private KhoaHocService khoaHocService = null;
    private String[] listColumn={"Mã khóa học", "STT", "Tên khóa học", "mô tả", "Ngày bắt đầu", "Ngày kêt thúc", "Tính trạng"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    

    public QuanLyKhoaHocController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnPrint) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnPrint = btnPrint;
        this.khoaHocService = new KhoaHocServiceImpl();
    }
    
    public void setDateToTable(){
        List<KhoaHoc> listItem = khoaHocService.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableModelKhoaHoc(listItem, listColumn);
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
                    
                    KhoaHoc khoaHoc = new KhoaHoc();
                    // gám các thuộc tính cho đối tượng - dòng nào cột nào của model nhận được
                    khoaHoc.setMa_khoa_hoc((int) model.getValueAt(selectedRowIndex, 0));
                    khoaHoc.setTen_khoa_hoc(model.getValueAt(selectedRowIndex, 2).toString());
                    khoaHoc.setMo_ta(model.getValueAt(selectedRowIndex, 3).toString());
                    khoaHoc.setNgay_bat_dau((Date) model.getValueAt(selectedRowIndex, 4));
                    khoaHoc.setNgay_ket_thuc((Date) model.getValueAt(selectedRowIndex, 5));
                    khoaHoc.setTinh_trang((model.getValueAt(selectedRowIndex, 6).equals("Đang học"))?true:false);
                    // neu ma thong tin trong thi hien thi chuoi rong len 
                    
                    
                    KhoaHocJFrame frame = new KhoaHocJFrame(khoaHoc);
                    frame.setTitle("Thông tin khoá hoc");
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
              KhoaHocJFrame frame = new KhoaHocJFrame(new KhoaHoc());
              frame.setTitle("Thông tin khóa học");
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
                XSSFSheet sheet = workbook.createSheet("Khoa Học");
                
                XSSFRow row = null;
                Cell cell = null;
                
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue("STT");
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã khóa học");
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Mô tả");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Ngày bắt đầu");
                
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Ngày kết thúc");
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Tinh trạng");
                
                
                List<KhoaHoc> listItem = khoaHocService.getList();
                if(listItem!=null){
                    FileOutputStream fis = null;
                    try {
                        int s = listItem.size();
                        for(int i = 0; i < s; i++){
                            KhoaHoc khoaHoc = listItem.get(i);
                            
                            row = sheet.createRow(4 + i);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i+1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(khoaHoc.getMa_khoa_hoc());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(khoaHoc.getMo_ta());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(khoaHoc.getNgay_bat_dau().toString());
                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(khoaHoc.getNgay_ket_thuc().toString());
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(khoaHoc.isTinh_trang()? "Đang Học":"Kêt Thúc");
                            
                            
                            
                        }   //lu file
                        File f = new File("D:/khoa_hoc.xlsx");
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
