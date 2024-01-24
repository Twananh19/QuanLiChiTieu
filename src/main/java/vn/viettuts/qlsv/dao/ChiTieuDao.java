package vn.viettuts.qlsv.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.viettuts.qlsv.entity.ChiTieu;
import vn.viettuts.qlsv.entity.ChiTieuXML;
import vn.viettuts.qlsv.utils.FileUtils;



public class ChiTieuDao {
    private static final String CHITIEU_FILE_NAME = "chitieu.xml";
    private List<ChiTieu> listChiTieus;

    public ChiTieuDao() {
        this.listChiTieus = readListChiTieus();
        if (listChiTieus == null) {
            listChiTieus = new ArrayList<ChiTieu>();
        }
    }

    /**
     * Lưu các đối tượng chitieu vào file chitieu.xml
     * 
     * @param chitieu
     */
    public void writeListChiTieus(List<ChiTieu> chitieus) {
        ChiTieuXML chitieuXML = new ChiTieuXML();
        chitieuXML.setChiTieu(chitieus);
        FileUtils.writeXMLtoFile(CHITIEU_FILE_NAME, chitieuXML);
    }

    /**
     * Đọc các đối tượng chitieu từ file chitieu.xml
     * 
     * @return list chitieu
     */
    public List<ChiTieu> readListChiTieus() {
        List<ChiTieu> list = new ArrayList<ChiTieu>();
        ChiTieuXML chitieuXML = (ChiTieuXML) FileUtils.readXMLFile(
                CHITIEU_FILE_NAME, ChiTieuXML.class);
        if (chitieuXML != null) {
            list = chitieuXML.getChiTieu();
        }
        return list;
    }
    

    /**
     * cập nhật chitieu vào listChiTieus và lưu listChiTieus vào file
     * 
     * @param chitieu
     */
    public void add(ChiTieu chitieu) {
        int id = 1;
        if (listChiTieus != null && listChiTieus.size() > 0) {
            id = listChiTieus.size() + 1;
        }
        chitieu.setId(id);
        listChiTieus.add(chitieu);
        writeListChiTieus(listChiTieus);
    }

    /**
     * cập nhật chitieu vào listChiTieus và lưu listChiTieus vào file
     * 
     * @param chitieu
     */
    
    public void edit(ChiTieu chitieu) {
    for (ChiTieu existingChiTieu : listChiTieus) {
        if (existingChiTieu.getId() == chitieu.getId()) {
            existingChiTieu.setDay(chitieu.getDay());
            existingChiTieu.setMonth(chitieu.getMonth());
            existingChiTieu.setYears(chitieu.getYears());
            existingChiTieu.setNoidung(chitieu.getNoidung());
            existingChiTieu.setNguon(chitieu.getNguon());
            existingChiTieu.setChuthich(chitieu.getChuthich());
            writeListChiTieus(listChiTieus);
            // Thêm dòng sau để cập nhật hiển thị trong giao diện người dùng
            //chitieuView.showListChiTieu(listChiTieus);
            break;
        }
    }
}


    /**
     * xóa chitieu từ listChiTieus và lưu listChiTieus vào file
     * 
     * @param chitieu
     */
    
    
    public boolean delete(ChiTieu chitieu) {
        boolean isFound = false;
        int size = listChiTieus.size();
        for (int i = 0; i < size; i++) {
            if (listChiTieus.get(i).getId() == chitieu.getId()) {
                chitieu = listChiTieus.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listChiTieus.remove(chitieu);
            writeListChiTieus(listChiTieus);
            return true;
        }
        return false;
    }

    /**
     * sắp xếp danh sách chitieu theo name theo tứ tự tăng dần
     */
    
    public void sortChiTieuByDayMonthYears() {
    listChiTieus.sort(Comparator.comparing(ChiTieu::getYears)
                                .thenComparing(ChiTieu::getMonth)
                                .thenComparing(ChiTieu::getDay));
}

  
    
  public List<ChiTieu> searchChiTieuByDate(int searchDay, int searchMonth, int searchYear) {
    List<ChiTieu> resultList = new ArrayList<>();
    for (ChiTieu chiTieu : listChiTieus) {
        if (chiTieu.getDay() == searchDay &&
            chiTieu.getMonth() == searchMonth &&
            chiTieu.getYears() == searchYear) {
            resultList.add(chiTieu);
        }
    }
    return resultList;
}

    
    
    
    public List<ChiTieu> getListChiTieu() {
        return listChiTieus;
    }

    public void setListChiTieu(List<ChiTieu> listChiTieus) {
        this.listChiTieus = listChiTieus;
    }
}
