package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chitieus")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChiTieuXML {
    
    private List<ChiTieu> chitieu;

    public List<ChiTieu> getChiTieu() {
        return chitieu;
    }

    public void setChiTieu(List<ChiTieu> chitieu) {
        this.chitieu = chitieu;
    }
}
