package Fdata;

import Jlogin.Koneksi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.sql.*;

public class Fdata extends  JFrame{
    private JTable viewTable;
    private JTable table1;
    private JButton createButton;
    private JButton keluarButton;
    private JButton readButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JPanel mainPanel;
    private int Id;

    public Fdata() {
        createButton.addActionListener(e -> {
            IFrame inputFrame = new IFrame();
            inputFrame.setVisible(true);
        });
        keluarButton.addActionListener(e -> {
            int barisTerpilih = viewTable.getSelectedRow();
            if (barisTerpilih < 0) {
                JOptionPane.showMessageDialog(null, "pilih data dulu");
                return;
            }
            int pilihan = JOptionPane.showConfirmDialog(null, "yakin mau hapus?", "Konfirmasi hapus", JOptionPane.YES_NO_OPTION);
            if (pilihan == 0) {
                TableModel tm = viewTable.getModel();
                int id = Integer.parseInt(tm.getValueAt(barisTerpilih, 0).toString());
                Connection c = Koneksi.getConnection();
                String deleteSQL = "DELETE FROM tgambar WHERE id = ?";
                try {
                    PreparedStatement ps = c.prepareStatement(deleteSQL);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        readButton.addActionListener(e -> {
            int barisTerpilih = viewTable.getSelectedRow();
            if (barisTerpilih < 0) {
                JOptionPane.showMessageDialog(null,"Pilih Data Dulu");
                return;
            }
            TableModel tm = viewTable.getModel();
            int id = Integer.parseInt(tm.getValueAt(barisTerpilih, 0).toString());
            IFrame inputFrame = new IFrame();
            inputFrame.setId(id);
            inputFrame.isikomponen();
            inputFrame.setVisible(true);

        });
        deleteButton.addActionListener(e -> {

        });
        isiTable();
        init();


    }
            public void init() {
                setContentPane(mainPanel);
                setTitle("Data Kota");
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
            }

            private void setContentPane(JPanel mainPanel) {
            }

            public void isiTable () {
                Connection c = Koneksi.getConnection();
                String selecSQL = "SELECT * FROM tgambar";
                try {
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery(selecSQL);
                    String header[] = {"id", "Nama barang", "Harga","Stok","pemasuk","Gambar"};
                    DefaultTableModel dtm = new DefaultTableModel(header, 0) {
                        public Class getColumnClass(int colomn) {
                            return getValueAt(0, colomn).getClass();
                        }
                    };
                    viewTable.setModel(dtm);
                    viewTable.setPreferredScrollableViewportSize(viewTable.getPreferredSize());
                    viewTable.setRowHeight(100);
                    Object[] row = new Object[6];
                    while (rs.next()) {
                        Icon icon = new ImageIcon(getBufferedImage(rs.getBlob("Gambar")));
                        row[0] = rs.getInt("id");
                        row[1] = rs.getString("Nama barang");
                        row[2] = rs.getInt("Harga");
                        row[3] = rs.getInt("Stok");
                        row[4] = rs.getString("Pemasukan");
                        row[5] = icon;
                        dtm.addRow(row);
                    }
                }catch (SQLException e){
                        throw new RuntimeException();
                    }
                }


    public BufferedImage getBufferedImage(Blob imageBlob) {
                    InputStream binaryStream = null;
                    BufferedImage b = null;
                    try {
                        binaryStream = imageBlob.getBinaryStream();
                        b = ImageIO.read(binaryStream);
                    } catch ( SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return b;
                }

    }