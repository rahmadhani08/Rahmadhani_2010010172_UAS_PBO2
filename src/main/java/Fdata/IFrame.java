package Fdata;

import Jlogin.Koneksi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IFrame extends JFrame{

    private JTextField id;

    private JTextField Nama_Brg;

    private JTextField HargaF;
    private JTextField StockF;
    private JTextField PemasukanF;
    private JButton saveButton;
    private JButton batalButton;
    private JPanel mainPanel;
    private int Id;

    public IFrame() {
        saveButton.addActionListener(e -> {
            String nama = Nama_Brg.getText();
            Double harga = Double.valueOf(HargaF.getText());
            String stok = StockF.getText();
            String Pemasukan = PemasukanF.getText();
            Connection c = Koneksi.getConnection();
            PreparedStatement ps;
            try {
                if (Id == 0) {
                    String insertSQL = "INSERT INTO tbrang VALUES (NULL, ?, ?, ?, ?)";
                    ps = c.prepareStatement(insertSQL);
                    ps.setString(1, nama);
                    ps.setDouble(2, harga);
                    ps.setString(3, stok);
                    ps.setString(4, Pemasukan);
                    ps.executeUpdate();
                    dispose();
                } else {
                    String updateSQL = "UPDATE tbaring SET nama = ?," + "harga = ? ," + " stok =?," + " pemasuk = ? WHERE id = ?";
                    ps = c.prepareStatement(updateSQL);
                    ps.setString(1, nama);
                    ps.setDouble(2, harga);
                    ps.setString(3, stok);
                    ps.setString(4, Pemasukan);
                    ps.setInt(3, Id);
                    ps.executeUpdate();
                    dispose();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        init();
    }
    public  void init() {
        setContentPane(mainPanel);
        setTitle("Data Kota");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void isikomponen() {
        Connection connection = Koneksi.getConnection();
        String findSQL = "SELECT * FROM tbrang WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(findSQL);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(String.valueOf(rs.getInt("id")));
                Nama_Brg.setText(String.valueOf(rs.getString("nama")));
                HargaF.setText(String.valueOf(rs.getDouble("harga")));
                StockF.setText(String.valueOf(rs.getString("stok")));
                PemasukanF.setText(String.valueOf(rs.getString("pemasuk")));
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e);


                }
            }

    public void setId(int id) {
    }
}


