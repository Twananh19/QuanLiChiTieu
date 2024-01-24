package vn.viettuts.qlsv.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vn.viettuts.qlsv.entity.ChiTieu;

public class ChiTieuView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton showTongThuChi;
    private JButton addChiTieuBtn;
    private JButton editChiTieuBtn;
    private JButton deleteChiTieuBtn;
    private JButton clearBtn;
    private JButton search;
    private JButton sortChiTieuDateBtn;
    private JScrollPane jScrollPaneChiTieuTable;
    private JScrollPane jScrollPaneNguon;
    private JTable chitieuTable;
    private JButton show;
    
    private JLabel idLabel;
    private JLabel dateLabel;
    private JLabel noidungLabel;
    private JLabel nguonLabel;
    private JLabel moneyLabel;
    private JLabel searchLabel;
    
    private JTextField idField;
    private JTextField noidungField;
    private JTextArea nguonTA;
    private JTextField moneyField;
    private JTextField searchField;
    
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearsField;
    
    // định nghĩa các cột của bảng student
    private String [] columnNames = new String [] {
            "ID", "Date", " Noi Dung ", "Nguon", "Money"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object [][] {};
    
    public ChiTieuView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addChiTieuBtn = new JButton("Add");
        editChiTieuBtn = new JButton("Edit");
        deleteChiTieuBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortChiTieuDateBtn = new JButton("Sort Date");
        showTongThuChi = new JButton("Total Income/Expense");
        search = new JButton("Search");
        show = new JButton("Show all");
        
        
        // khởi tạo bảng chi tieu
        jScrollPaneChiTieuTable = new JScrollPane();
        chitieuTable = new JTable();
         
        // khởi tạo các label
        idLabel = new JLabel("Id");
        dateLabel = new JLabel("Date(dd/mm/yyyy");
        nguonLabel = new JLabel("Nguon");
        noidungLabel  = new JLabel("Noi dung");
        moneyLabel = new JLabel("Money");
        //searchLabel = new JLabel("Search");
        
        // khởi tạo các trường nhập dữ liệu cho quan ly chi tieu
        idField = new JTextField(6);
        idField.setEditable(false);
        //dateField = new JTextField(15);
        noidungField = new JTextField(15);
        nguonTA = new JTextArea();
        nguonTA.setColumns(15);
        nguonTA.setRows(1);
        jScrollPaneNguon = new JScrollPane();
        jScrollPaneNguon.setViewportView(nguonTA);
        moneyField =  new JTextField(15);
        //searchField = new JTextField(15);
        
        dayField = new JTextField(3);
        monthField = new JTextField(3);
        yearsField = new JTextField(3);

        
        
        // cài đặt các cột và data cho bảng danh sác quản lý thu chi
        chitieuTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneChiTieuTable.setViewportView(chitieuTable);
        jScrollPaneChiTieuTable.setPreferredSize(new Dimension (550, 500));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý ChiTieu
        JPanel panel = new JPanel();
        panel.setSize(950, 1000);
        panel.setLayout(layout);
        panel.add(jScrollPaneChiTieuTable);
        
        panel.add(addChiTieuBtn);
        panel.add(editChiTieuBtn);
        panel.add(deleteChiTieuBtn);
        panel.add(clearBtn);
        panel.add(sortChiTieuDateBtn);
        panel.add(showTongThuChi);
        panel.add(search);
        panel.add(show);
        
        panel.add(idLabel);
        panel.add(dateLabel);
        panel.add(noidungLabel);
        panel.add(nguonLabel);
        panel.add(moneyLabel);
        //panel.add(searchLabel);
        
        panel.add(idField);
        panel.add(noidungField);
        panel.add(jScrollPaneNguon);
        panel.add(moneyField);
        //panel.add(searchField);
        panel.add(dayField);
        panel.add(monthField);
        panel.add(yearsField);
        
        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, noidungLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, noidungLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nguonLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nguonLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, moneyLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, moneyLabel, 130, SpringLayout.NORTH, panel);
//        layout.putConstraint(SpringLayout.WEST, searchLabel, 10, SpringLayout.WEST, panel);
//        layout.putConstraint(SpringLayout.NORTH, searchLabel, 170, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, dayField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dayField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, monthField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, monthField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, yearsField, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, yearsField, 40, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, noidungField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, noidungField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneNguon, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneNguon, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, moneyField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, moneyField, 130, SpringLayout.NORTH, panel);
//        layout.putConstraint(SpringLayout.WEST, searchField, 100, SpringLayout.WEST, panel);
//        layout.putConstraint(SpringLayout.NORTH, searchField, 170, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneChiTieuTable, 360, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneChiTieuTable, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addChiTieuBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addChiTieuBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editChiTieuBtn, 60, SpringLayout.WEST, addChiTieuBtn);
        layout.putConstraint(SpringLayout.NORTH, editChiTieuBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteChiTieuBtn);
        layout.putConstraint(SpringLayout.WEST, deleteChiTieuBtn, 60, SpringLayout.WEST, editChiTieuBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteChiTieuBtn, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortChiTieuDateBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortChiTieuDateBtn, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, showTongThuChi, 280, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, showTongThuChi, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, search, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, search, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, show, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, show, 200, SpringLayout.WEST, panel);
       

        
        this.add(panel);
        this.pack();
        this.setTitle("Quản lý chi tiêu");
        this.setSize(950, 350);
        // disable Edit and Delete buttons
        editChiTieuBtn.setEnabled(false);
        deleteChiTieuBtn.setEnabled(false);
        // enable Add button
        addChiTieuBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * hiển thị list student vào bảng studentTable
     * 
     * @param list
     */
    
    public void showListChiTieu(List<ChiTieu> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object [][] chitieu = new Object[size][5];
        for (int i = 0; i < size; i++) {
            chitieu[i][0] = list.get(i).getId();
            chitieu[i][1] = String.format("%02d/%02d/%04d",
            list.get(i).getDay(), list.get(i).getMonth(), list.get(i).getYears());
            chitieu[i][2] = list.get(i).getNoidung();
            chitieu[i][3] = list.get(i).getNguon();
            chitieu[i][4] = list.get(i).getMoney();
        }
        chitieuTable.setModel(new DefaultTableModel(chitieu, columnNames));
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng student 
     * vào các trường tương ứng của student.
     */
    public void fillChiTieuFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = chitieuTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(chitieuTable.getModel().getValueAt(row, 0).toString());
//            dateField.setText(chitieuTable.getModel().getValueAt(row, 1).toString());
            noidungField.setText(chitieuTable.getModel().getValueAt(row, 2).toString());
            nguonTA.setText(chitieuTable.getModel().getValueAt(row, 3).toString());
            moneyField.setText(chitieuTable.getModel().getValueAt(row, 4).toString());
            // enable Edit and Delete buttons
            editChiTieuBtn.setEnabled(true);
            deleteChiTieuBtn.setEnabled(true);
            // disable Add button
            addChiTieuBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin quan  ly chi tieu
     */
    public void clearChiTieuInfo() {
    idField.setText("");
    dayField.setText("");
    monthField.setText("");
    yearsField.setText("");
    noidungField.setText("");
    nguonTA.setText("");
    moneyField.setText("");
    // disable Edit and Delete buttons
    editChiTieuBtn.setEnabled(false);
    deleteChiTieuBtn.setEnabled(false);
    // enable Add button
    addChiTieuBtn.setEnabled(true);
}
    
    /**
     * hiện thị thông tin student
     * 
     * @param chitieu
     */
    public void showChiTieu(ChiTieu chitieu) {
    idField.setText(Integer.toString(chitieu.getId()));
    
    // Sử dụng các trường dayField, monthField, yearsField để đặt giá trị ngày, tháng, năm
    dayField.setText(String.format("%02d", chitieu.getDay()));
    monthField.setText(String.format("%02d", chitieu.getMonth()));
    yearsField.setText(String.format("%04d", chitieu.getYears()));
    
    noidungField.setText(chitieu.getNoidung());
    nguonTA.setText(chitieu.getNguon());
    moneyField.setText(Double.toString(chitieu.getMoney()));
    editChiTieuBtn.setEnabled(true);
    deleteChiTieuBtn.setEnabled(true);
    addChiTieuBtn.setEnabled(false);
}

    
    
    
    /**
     * lấy thông tin student
     * 
     * @return
     */
    
    public ChiTieu getChiTieuInfo() {
    // Validate quản lý chi tieu
    if (!validateDate() || !validateNguon() || !validateNoidung()) {
        return null;
    }
    try {
        ChiTieu chitieu = new ChiTieu();
        if (!idField.getText().isEmpty()) {
            chitieu.setId(Integer.parseInt(idField.getText()));
        }

        // Sử dụng các trường dayField, monthField, yearsField để đặt giá trị ngày, tháng, năm
        chitieu.setDate(Integer.parseInt(dayField.getText().trim()), 
                        Integer.parseInt(monthField.getText().trim()), 
                        Integer.parseInt(yearsField.getText().trim()));

        chitieu.setNoidung(noidungField.getText().trim());
        chitieu.setNguon(nguonTA.getText().trim());
        chitieu.setMoney(Float.parseFloat(moneyField.getText().trim()));
        return chitieu;
    } catch (NumberFormatException e) {
        showMessage("Lỗi chuyển đổi số: " + e.getMessage());
    }
    return null;
}

    
    private boolean validateDate() {
        String date = dayField.getText() + "/" + monthField.getText() + "/" + yearsField.getText();

        if (date.isEmpty()) {
            dayField.requestFocus();
            showMessage("Date không được trống.");
            return false;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            dayField.requestFocus();
            showMessage("Ngày không hợp lệ. Định dạng đúng là dd/MM/yyyy.");
            return false;
        }

        return true;
    }

    
   private boolean validateNoidung() {
        String noidung = noidungField.getText();
        if (noidung == null || "".equals(noidung.trim())) {
            noidungField.requestFocus();
            showMessage("Nội dung không được trống.");
            return false;
        }
        if (!"thu".equals(noidung) && !"chi".equals(noidung)) {
            noidungField.requestFocus();
            showMessage("Nhập sai nội dung (thu/chi). Hãy nhập lại!");
            return false; 
        }
        return true;
    }

    
    private boolean validateNguon   () {
        String noidung = nguonTA.getText();
        if (noidung == null || "".equals(noidung.trim())) {
            nguonTA.requestFocus();
            showMessage("Nguồn không được trống.");
            return false;
        }
        return true;
    }
    
    public void hienThiTongThuChi(double tongThu, double tongChi) {
        JOptionPane.showMessageDialog(this, "Tổng Thu: " + tongThu + "\nTổng Chi: " + tongChi);
        if(tongThu < tongChi){
            showMessage("Chi tiêu vượt quá giới hạn");
        }
    }
    
    public String getSearchDate() {
        String day = dayField.getText().trim();
        String month = monthField.getText().trim();
        String year = yearsField.getText().trim();
        return day + "/" + month + "/" + year;
    }
    
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void tinhTongThuChiToanBo(ActionListener listenner){
        showTongThuChi.addActionListener(listenner);
    }
    
    public void addAddChiTieuListener(ActionListener listener) {
        addChiTieuBtn.addActionListener(listener);
    }
        
    public void addEdiChiTieuListener(ActionListener listener) {
        editChiTieuBtn.addActionListener(listener);
    }
    
    public void addDeleteChiTieuListener(ActionListener listener) {
        deleteChiTieuBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortChiTieuDateListener(ActionListener listener) {
        sortChiTieuDateBtn.addActionListener(listener);
    }
    
    public void addListChiTieuSelectionListener(ListSelectionListener listener) {
        chitieuTable.getSelectionModel().addListSelectionListener(listener);
    }
    // Trong lớp ChiTieuView
    public void addSearchChiTieuListener(ActionListener listener) {
        search.addActionListener(listener);
    }

   
}
