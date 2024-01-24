package vn.viettuts.qlsv.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chitieu")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChiTieu implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int day;
    private int month;
    private int years;
    private String chuthich;
    private String noidung; 
    private String nguon;
    private double money;

    public ChiTieu(int day, int month, int years) {
        this.day = day;
        this.month = month;
        this.years = years;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getChuthich() {
        return chuthich;
    }

    public void setChuthich(String chuthich) {
        this.chuthich = chuthich;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ChiTieu(int id, int day, int month, int years, String chuthich, String noidung, String nguon, double money) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.years = years;
        this.chuthich = chuthich;
        this.noidung = noidung;
        this.nguon = nguon;
        this.money = money;
    }

    
    
    
    
    
   
    public ChiTieu() {
    }

    public void setDate(int parseInt, int parseInt0, int parseInt1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
}
