package vn.viettuts.qlsv.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlsv.dao.ChiTieuDao;
import vn.viettuts.qlsv.entity.ChiTieu;
import vn.viettuts.qlsv.view.ChiTieuView;

public class ChiTieuController {
    private ChiTieuDao chitieuDao;
    private ChiTieuView chitieuView;

    public ChiTieuController(ChiTieuView view) {
        this.chitieuView = view;
        chitieuDao = new ChiTieuDao();

        view.addAddChiTieuListener(new AddChiTieuListener());
        view.addEdiChiTieuListener(new EditChiTieuListener());
        view.addDeleteChiTieuListener(new DeleteChiTieuListener());
        view.addClearListener(new ClearChiTieuListener());
        view.addSortChiTieuDateListener(new SortChiTieuByDayMonthYears());
        view.addListChiTieuSelectionListener(new ListChiTieuSelectionListener());
        view.addSearchChiTieuListener(new SearchChiTieuListener());
        view.tinhTongThuChiToanBo(new TinhTongChiTieuListener());
    }
    
    class TinhTongChiTieuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<ChiTieu> listChiTieu = chitieuDao.getListChiTieu();
            double tongThu = 0.0;
            double tongChi = 0.0;

            for (ChiTieu chiTieu : listChiTieu) {
                if ("thu".equals(chiTieu.getNoidung())) {
                    tongThu += chiTieu.getMoney();
                } else if ("chi".equals(chiTieu.getNoidung())) {
                    tongChi += chiTieu.getMoney();
                }
            }

            // Display the totals on the ChiTieuView
            chitieuView.hienThiTongThuChi(tongThu, tongChi);
        }
    }


    public void showChiTieuView() {
        List<ChiTieu> chitieuList = chitieuDao.getListChiTieu();
        chitieuView.setVisible(true);
        chitieuView.showListChiTieu(chitieuList);
    }

    /**
     * Lớp AddChiTieuListener 
     * chứa cài đặt cho sự kiện click button "Add"
     * 
     * @author viettuts.vn
     */
    class AddChiTieuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChiTieu chitieu = chitieuView.getChiTieuInfo();
            if (chitieu != null) {
                chitieuDao.add(chitieu);
                chitieuView.showChiTieu(chitieu);
                chitieuView.showListChiTieu(chitieuDao.getListChiTieu());
                chitieuView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     * 
     * @author viettuts.vn
     */
    
    class EditChiTieuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChiTieu chitieu = chitieuView.getChiTieuInfo();
            if (chitieu != null) {
                chitieuDao.edit(chitieu);
                chitieuView.showChiTieu(chitieu);
                chitieuView.showListChiTieu(chitieuDao.getListChiTieu());
                chitieuView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteStudentListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     * 
     * @author viettuts.vn
     */
    
    class DeleteChiTieuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChiTieu chitieu = chitieuView.getChiTieuInfo();
            if (chitieu != null) {
                chitieuDao.delete(chitieu);
                chitieuView.clearChiTieuInfo();
                chitieuView.showListChiTieu(chitieuDao.getListChiTieu());
                chitieuView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    
    class ClearChiTieuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            chitieuView.clearChiTieuInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     * 
     * @author viettuts.vn
     */
   class SortChiTieuByDayMonthYears implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<ChiTieu> sortedList = chitieuDao.getListChiTieu();
            sortedList.sort(new Comparator<ChiTieu>() {
                @Override
                public int compare(ChiTieu ct1, ChiTieu ct2) {
                    int compareYears = Integer.compare(ct1.getYears(), ct2.getYears());
                    if (compareYears != 0) {
                        return compareYears;
                    }

                    int compareMonths = Integer.compare(ct1.getMonth(), ct2.getMonth());
                    if (compareMonths != 0) {
                        return compareMonths;
                    }

                    return Integer.compare(ct1.getDay(), ct2.getDay());
                }
            });

            chitieuView.showListChiTieu(sortedList);
        }
    }
   
   // Trong lớp ChiTieuController
class SearchChiTieuListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String searchDate = chitieuView.getSearchDate();

        List<ChiTieu> resultList = new ArrayList<>();

        List<ChiTieu> listChiTieu = chitieuDao.getListChiTieu();
        for (ChiTieu chiTieu : listChiTieu) {
            String chiTieuDate = String.format("%02d/%02d/%04d",
                chiTieu.getDay(), chiTieu.getMonth(), chiTieu.getYears());

            if (chiTieuDate.equals(searchDate)) {
                resultList.add(chiTieu);
            }
        }

        chitieuView.showListChiTieu(resultList);
    }
}




    

    /**
     * Lớp ListStudentSelectionListener 
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     * 
     * @author viettuts.vn
     */
    class ListChiTieuSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            chitieuView.fillChiTieuFromSelectedRow();
        }
    }
}
