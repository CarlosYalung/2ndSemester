
package main;

import Config.config;
import dashboard.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class orderss extends javax.swing.JFrame {
private double total = 0.0;
   
    private int[] sessionQuantities = new int[6]; 

    public orderss() {
    if (!Session.isLoggedIn()) {
        javax.swing.JOptionPane.showMessageDialog(null,  
            "You need to login first!",
            "DripHorizon - Login Required",
            javax.swing.JOptionPane.WARNING_MESSAGE);

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
        
        return;  
    }

    initComponents();
    setTime();
    displayUser();
    loadOrderData();
}
     void displayUser() {
    try {
        java.sql.Connection con = config.connectDB();

        String sql = "SELECT name FROM tble_user WHERE register_id = ?";
        java.sql.PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, config.loggedInAID);

        java.sql.ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String fullName = rs.getString("name");

            String firstName = fullName.split(" ")[0];

            nm.setText("" + firstName);
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, e);
    }
}
    private void addToCart(JSpinner spinner, String itemName, double unitPrice, int idIndex) {
        int qty = (int) spinner.getValue();

        if (qty > 0) {
            if (jTextArea.getText().isEmpty()) {
                DripHorizon();
                jTextArea.append("\n");
            }

            double itemTotal = unitPrice * qty;
           
            String receiptLine = String.format("Item: %-20s| Qty: %-3d | Price: ₱%.2f\n", itemName, qty, itemTotal);
            jTextArea.append(receiptLine);

            total += itemTotal;
            sessionQuantities[idIndex] += qty; 

            jTextFieldtotal.setText(String.format("%.2f", total));
            spinner.setValue(0);
        } else {
            JOptionPane.showMessageDialog(this, "Please increase the item quantity");
        }
    }
  public void loadOrderQuantity(int orderId) {
    DefaultTableModel model = (DefaultTableModel) Jtable.getModel();
    model.setRowCount(0); 

    if (model.getColumnCount() == 0) {
        model.addColumn("Product");
        model.addColumn("Stock Left");
    }

    try {
        config con = new config();
      
        String sql = "SELECT Pname, quantitys FROM tble_buyers WHERE id_buyers = ?";
        
        try (Connection cn = con.connectDB();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            
            pst.setInt(1, orderId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{ 
                        rs.getString("Pname"), 
                        rs.getInt("quantitys") 
                    });
                }
            }
        } 
    } catch (Exception e) {
        System.out.println("Error Loading Data: " + e.getMessage());
    }
}
  public void loadOrderData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product Name");
        model.addColumn("Quantity");

        try {
            config con = new config();
            Connection cn = con.connectDB();
           
            String sql = "SELECT Pname, quantitys FROM tble_buyers ORDER BY id_buyers ASC";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Pname"),
                    rs.getInt("quantitys")
                });
            }
            Jtable.setModel(model);
        } catch (Exception e) {
            System.out.println("Error Loading Table: " + e.getMessage());
        }
    }
  
    public void setTime() {
    new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException ex) {
                Logger.getLogger(orderss.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date now = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy");

            String currentTime = timeFormat.format(now);
            String currentDate = dateFormat.format(now);

            javax.swing.SwingUtilities.invokeLater(() -> {
                jtime.setText(currentTime);   
                jdates.setText(currentDate);  
            });
        }
    }).start();
}
   public void zero() {
    
    int qty = (int) jSpinner1.getValue();
    
    if (qty == 0) {
        JOptionPane.showMessageDialog(null, "Please increase the item quantity");
    }
}
    
 public void reset() {
        jSpinner1.setValue(0); jSpinner2.setValue(0); jSpinner11.setValue(0);
        jSpinner12.setValue(0); jSpinner13.setValue(0); jSpinner14.setValue(0);
        jTextFieldtotal.setText("0.00");
        jTextArea.setText("");
        total = 0.0;
        sessionQuantities = new int[6];
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTextFieldtotal = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        receiptText = new javax.swing.JPanel();
        jtime = new javax.swing.JLabel();
        jdates = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Jtable = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        name = new javax.swing.JLabel();
        prices = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        name1 = new javax.swing.JLabel();
        prices1 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jSpinner11 = new javax.swing.JSpinner();
        name2 = new javax.swing.JLabel();
        prices2 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jSpinner12 = new javax.swing.JSpinner();
        name3 = new javax.swing.JLabel();
        prices3 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jSpinner13 = new javax.swing.JSpinner();
        name4 = new javax.swing.JLabel();
        prices4 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jSpinner14 = new javax.swing.JSpinner();
        name5 = new javax.swing.JLabel();
        prices5 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nm = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(67, 80, 91));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jTextFieldtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 120, -1));

        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Total");
        jPanel5.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        receiptText.setBackground(new java.awt.Color(67, 80, 91));
        receiptText.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        receiptText.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtime.setForeground(new java.awt.Color(255, 255, 255));
        receiptText.add(jtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 80, 70));

        jdates.setForeground(new java.awt.Color(255, 255, 255));
        receiptText.add(jdates, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 140, 70));

        jPanel5.add(receiptText, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 70));

        jTextArea.setBackground(new java.awt.Color(84, 85, 86));
        jTextArea.setColumns(20);
        jTextArea.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 270, 280));

        jPanel14.setBackground(new java.awt.Color(58, 175, 253));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                  Pay Now");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        jPanel5.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 140, 30));

        Jtable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Jtable.setSelectionBackground(new java.awt.Color(74, 154, 249));
        Jtable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(Jtable);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 220, 90));

        jPanel19.setBackground(new java.awt.Color(75, 108, 108));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("        Reset");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel19.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        jPanel5.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, -1, 40));

        jPanel23.setBackground(new java.awt.Color(75, 108, 108));
        jPanel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("        Receipt");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel23.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        jPanel5.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 580, -1, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 270, 630));

        jPanel16.setBackground(new java.awt.Color(204, 204, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(67, 80, 91));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(61, 61, 61));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setForeground(new java.awt.Color(204, 255, 204));
        jLabel10.setText("Name:");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, -1));

        jLabel11.setForeground(new java.awt.Color(204, 255, 204));
        jLabel11.setText("Price:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, -1));

        jLabel12.setForeground(new java.awt.Color(204, 255, 204));
        jLabel12.setText("Quantity:");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 20));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel6.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 50, -1));

        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setText("Jordan");
        jPanel6.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        prices.setForeground(new java.awt.Color(59, 177, 59));
        prices.setText("250");
        jPanel6.add(prices, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jPanel1.setBackground(new java.awt.Color(58, 175, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Add to cart");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/01.jpg"))); // NOI18N
        jPanel6.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 90));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 210));

        jPanel7.setBackground(new java.awt.Color(61, 61, 61));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setForeground(new java.awt.Color(204, 255, 204));
        jLabel16.setText("Name:");
        jLabel16.setToolTipText("");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, -1));

        jLabel71.setForeground(new java.awt.Color(204, 255, 204));
        jLabel71.setText("Price:");
        jPanel7.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, -1));

        jLabel72.setForeground(new java.awt.Color(204, 255, 204));
        jLabel72.setText("Quantity:");
        jPanel7.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 20));

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel7.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 50, -1));

        name1.setForeground(new java.awt.Color(255, 255, 255));
        name1.setText("Nike Zoom Vomero 5");
        jPanel7.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        prices1.setForeground(new java.awt.Color(59, 177, 59));
        prices1.setText("8,895");
        jPanel7.add(prices1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NIKE+ZOOM+VOMERO+5.jpg"))); // NOI18N
        jPanel7.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 90));

        jPanel12.setBackground(new java.awt.Color(58, 175, 253));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Add to cart");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel7.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 170, 210));

        jPanel11.setBackground(new java.awt.Color(61, 61, 61));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setForeground(new java.awt.Color(204, 255, 204));
        jLabel77.setText("Name:");
        jPanel11.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, -1));

        jLabel78.setForeground(new java.awt.Color(204, 255, 204));
        jLabel78.setText("Price:");
        jPanel11.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, -1));

        jLabel79.setForeground(new java.awt.Color(204, 255, 204));
        jLabel79.setText("Quantity:");
        jPanel11.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 20));

        jSpinner11.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel11.add(jSpinner11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 50, -1));

        name2.setForeground(new java.awt.Color(240, 240, 240));
        name2.setText("Air Jordan 1 Low");
        jPanel11.add(name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        prices2.setForeground(new java.awt.Color(59, 177, 59));
        prices2.setText("6,395");
        jPanel11.add(prices2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AIR+JORDAN+1+LOW.jpg"))); // NOI18N
        jPanel11.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 90));

        jPanel9.setBackground(new java.awt.Color(58, 175, 253));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Add to cart");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));

        jPanel11.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 170, 210));

        jPanel13.setBackground(new java.awt.Color(61, 61, 61));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setForeground(new java.awt.Color(204, 255, 204));
        jLabel84.setText("Name:");
        jPanel13.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, -1));

        jLabel85.setForeground(new java.awt.Color(204, 255, 204));
        jLabel85.setText("Price:");
        jPanel13.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, -1));

        jLabel86.setForeground(new java.awt.Color(204, 255, 204));
        jLabel86.setText("Quantity:");
        jPanel13.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 20));

        jSpinner12.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel13.add(jSpinner12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 50, -1));

        name3.setForeground(new java.awt.Color(255, 255, 255));
        name3.setText("Adidas Samba OG");
        jPanel13.add(name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 90, -1));

        prices3.setForeground(new java.awt.Color(59, 177, 59));
        prices3.setText("6,800");
        jPanel13.add(prices3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Samba_OG_Shoes_Black_B75807_db07_standard.tiff.jpg"))); // NOI18N
        jPanel13.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 90));

        jPanel8.setBackground(new java.awt.Color(58, 175, 253));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Add to cart");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel13.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, 210));

        jPanel15.setBackground(new java.awt.Color(61, 61, 61));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel91.setForeground(new java.awt.Color(204, 255, 204));
        jLabel91.setText("Name:");
        jPanel15.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, -1));

        jLabel92.setForeground(new java.awt.Color(204, 255, 204));
        jLabel92.setText("Price:");
        jLabel92.setToolTipText("");
        jPanel15.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, -1));

        jLabel93.setForeground(new java.awt.Color(204, 255, 204));
        jLabel93.setText("Quantity:");
        jPanel15.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 20));

        jSpinner13.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel15.add(jSpinner13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 50, -1));

        name4.setForeground(new java.awt.Color(255, 255, 255));
        name4.setText("New Balance 530");
        jPanel15.add(name4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        prices4.setForeground(new java.awt.Color(59, 177, 59));
        prices4.setText("5,995");
        jPanel15.add(prices4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NEW-BALANCE-530-WHITE-SILVER-NAVY-MR530SG-1_1200x1200.jpg"))); // NOI18N
        jPanel15.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 90));

        jPanel4.setBackground(new java.awt.Color(58, 175, 253));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Add to cart");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel15.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel3.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 170, 210));

        jPanel17.setBackground(new java.awt.Color(61, 61, 61));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel98.setForeground(new java.awt.Color(204, 255, 204));
        jLabel98.setText("Name:");
        jPanel17.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 50, -1));

        jLabel99.setForeground(new java.awt.Color(204, 255, 204));
        jLabel99.setText("Price:");
        jPanel17.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, -1));

        jLabel100.setForeground(new java.awt.Color(204, 255, 204));
        jLabel100.setText("Quantity:");
        jPanel17.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 50, 20));

        jSpinner14.setModel(new javax.swing.SpinnerNumberModel(0, 0, 40, 1));
        jPanel17.add(jSpinner14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 50, -1));

        name5.setForeground(new java.awt.Color(255, 255, 255));
        name5.setText("Puma Speedcat OG");
        jPanel17.add(name5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        prices5.setForeground(new java.awt.Color(59, 177, 59));
        prices5.setText("5,500");
        jPanel17.add(prices5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Speedcat-Archive-Sneakers-Unisex.jpg"))); // NOI18N
        jPanel17.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 90));

        jPanel10.setBackground(new java.awt.Color(58, 175, 253));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Add to cart");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel17.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel3.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 170, 210));

        jPanel18.setBackground(new java.awt.Color(75, 108, 108));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(50, 83, 84));
        jPanel22.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Main Menu");
        jPanel22.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jPanel18.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 680, 60));

        jPanel3.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 70));

        jPanel16.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 680, 570));

        jPanel2.setBackground(new java.awt.Color(61, 61, 61));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Driphorizon");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, 60));

        nm.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(nm, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 50, 20));

        jPanel21.setBackground(new java.awt.Color(75, 108, 108));
        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("        Profile");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        jPanel2.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, 40));

        jPanel16.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 60));

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 public void DripHorizon() {
    jTextArea.setText("************** DripHorizon ******************\n" +
                     "Time: " + jtime.getText() + " | Date: " + jdates.getText() + "\n" +
                     "*********************************************\n");
}
    
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
     addToCart(jSpinner1, name.getText(), 250.0, 0);

    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
      addToCart(jSpinner13, name4   .getText(), 8895.0, 1);

    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
     addToCart(jSpinner12, name3.getText(), 6800.0, 3);

    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
     addToCart(jSpinner11, name2.getText(), 6395.0, 2);

    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
       addToCart(jSpinner14, name5.getText(), 5500.0, 5);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        addToCart(jSpinner2, name1.getText(), 8895.0, 1);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       int userID = config.loggedInAID;
    
    if (total <= 0) {
        JOptionPane.showMessageDialog(this, "Your cart is empty!", "DripHorizon", JOptionPane.WARNING_MESSAGE);
        return;
    }

    java.awt.Color bgWhite = new java.awt.Color(255, 255, 255);
    javax.swing.JPanel paymentPanel = new javax.swing.JPanel();
    paymentPanel.setLayout(new javax.swing.BoxLayout(paymentPanel, javax.swing.BoxLayout.Y_AXIS));
    paymentPanel.setBackground(bgWhite);

    String[] deliveryOptions = {"Standard Delivery (Free)", "Express Delivery (₱50.00)", "Pick-up at Store (Free)"};
    javax.swing.JComboBox<String> deliveryBox = new javax.swing.JComboBox<>(deliveryOptions);
    paymentPanel.add(new javax.swing.JLabel("Shipping Method:"));
    paymentPanel.add(deliveryBox);

    String[] paymentOptions = {"GCash", "Maya", "Cash on Delivery", "Credit Card"};
    javax.swing.JComboBox<String> paymentBox = new javax.swing.JComboBox<>(paymentOptions);
    paymentPanel.add(new javax.swing.JLabel("Payment Method:"));
    paymentPanel.add(paymentBox);

    int result = JOptionPane.showConfirmDialog(this, paymentPanel, 
            "DripHorizon Secure Checkout", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        // 1. Calculate Fees
        String selectedDel = (String) deliveryBox.getSelectedItem();
        double deliveryFee = selectedDel.contains("₱50.00") ? 50.00 : 0.00;
        double finalTotal = total + deliveryFee;
        String selectedPayment = (String) paymentBox.getSelectedItem();

        boolean purchaseSaved = savePurchaseToHistory(userID, finalTotal, selectedDel, selectedPayment);
        
        if (!purchaseSaved) {
            JOptionPane.showMessageDialog(this, 
                "Failed to save purchase history. Please try again.", 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateDatabaseStock();

        loadOrderData();

        jTextArea.append("\n--------------------------------");
        jTextArea.append("\n Shipping: " + selectedDel);
        jTextArea.append("\n Payment: " + selectedPayment);
        jTextArea.append("\n FINAL TOTAL: ₱" + String.format("%.2f", finalTotal));
        jTextArea.append("\n--------------------------------");
        
        JOptionPane.showMessageDialog(this, "Purchase Successful!");
        
        sessionQuantities = new int[6];
        total = 0.0;
    }


    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
     reset();        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        PurchaseHistory s = new PurchaseHistory();
        s.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
     String receiptContent = jTextArea.getText();
        boolean isPaid = receiptContent.contains("FINAL TOTAL");
        
        if (!isPaid) {
            JOptionPane.showMessageDialog(this,
                "You have to pay first to get your receipt!\n\n" +
                "Please complete your purchase by clicking 'Pay Now'.",
                "DripHorizon",
                JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        showReceiptPanel(receiptContent);
    }

    private void showReceiptPanel(String receiptContent) {
       
        javax.swing.JDialog receiptDialog = new javax.swing.JDialog(this, "Transaction Receipt", true);
        receiptDialog.setSize(450, 550);
        receiptDialog.setLocationRelativeTo(this);
        receiptDialog.setResizable(false);
        
        javax.swing.JPanel mainPanel = new javax.swing.JPanel();
        mainPanel.setBackground(new java.awt.Color(240, 240, 240));
        mainPanel.setLayout(new java.awt.BorderLayout(10, 10));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        javax.swing.JTextArea receiptArea = new javax.swing.JTextArea();
        receiptArea.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 13));
        receiptArea.setBackground(new java.awt.Color(255, 255, 255));
        receiptArea.setForeground(new java.awt.Color(50, 50, 50));
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(false);
        receiptArea.setWrapStyleWord(false);
        receiptArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        StringBuilder fullReceipt = new StringBuilder();
        fullReceipt.append("************** DripHorizon ******************\n");
        fullReceipt.append("Time: ").append(jtime.getText()).append(" | Date: ").append(jdates.getText()).append("\n");
        fullReceipt.append("*********************************************\n\n");
        
        String[] lines = receiptContent.split("\n");
        for (String line : lines) {
            if (line.contains("Item:")) {
                fullReceipt.append(line.trim()).append("\n");
            }
        }
        
        fullReceipt.append("\n--------------------------------\n");
        
        String shipping = "Standard Delivery (Free)";
        String payment = "Cash";
        
        for (String line : lines) {
            if (line.contains("Shipping:")) {
                shipping = line.substring(line.indexOf("Shipping:") + 9).trim();
            }
            if (line.contains("Payment:")) {
                payment = line.substring(line.indexOf("Payment:") + 8).trim();
            }
        }
        
        fullReceipt.append(" Shipping: ").append(shipping).append("\n");
        fullReceipt.append(" Payment: ").append(payment).append("\n");
        
        double finalTotal = 0;
        if (receiptContent.contains("FINAL TOTAL: ₱")) {
            int start = receiptContent.indexOf("FINAL TOTAL: ₱") + 14;
            int end = receiptContent.indexOf("\n", start);
            if (end == -1) end = receiptContent.length();
            try {
                finalTotal = Double.parseDouble(receiptContent.substring(start, end).trim());
            } catch (Exception e) { }
        }
        
        fullReceipt.append(" FINAL TOTAL: ₱").append(String.format("%.2f", finalTotal)).append("\n");
        fullReceipt.append("--------------------------------\n\n");
        
        fullReceipt.append("Transaction ID: ").append(generateUniqueCode()).append("\n");
        fullReceipt.append("Cashier: ").append(nm.getText()).append("\n");
        fullReceipt.append("Status: PAID\n");
        fullReceipt.append("\nThank you for your purchase!\n");
        fullReceipt.append("Please come again soon.\n");
        
        receiptArea.setText(fullReceipt.toString());
        
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(receiptArea);
        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        
        javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 0));
        
        javax.swing.JButton printBtn = new javax.swing.JButton("Print");
        printBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        printBtn.setBackground(new java.awt.Color(58, 175, 253));
        printBtn.setForeground(java.awt.Color.WHITE);
        printBtn.setFocusPainted(false);
        printBtn.setPreferredSize(new java.awt.Dimension(80, 35));
        printBtn.addActionListener(e -> {
            try {
                receiptArea.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Print error: " + ex.getMessage());
            }
        });
        
        javax.swing.JButton closeBtn = new javax.swing.JButton("Close");
        closeBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        closeBtn.setBackground(new java.awt.Color(150, 160, 170));
        closeBtn.setForeground(java.awt.Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.setPreferredSize(new java.awt.Dimension(80, 35));
        closeBtn.addActionListener(e -> receiptDialog.dispose());
        
        buttonPanel.add(printBtn);
        buttonPanel.add(closeBtn);
        
        mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        mainPanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
        
        receiptDialog.add(mainPanel);
        receiptDialog.setVisible(true);
    
    }//GEN-LAST:event_jLabel7MouseClicked
      
private void updateDatabaseStock() {
       
        String sql = "UPDATE tble_buyers SET quantitys = quantitys - ? WHERE id_buyers = ?";
        config conf = new config();

        try (Connection cn = conf.connectDB();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            for (int i = 0; i < sessionQuantities.length; i++) {
                if (sessionQuantities[i] > 0) {
                    pst.setInt(1, sessionQuantities[i]);
                    pst.setInt(2, i + 1); // Maps index 0-5 to database IDs 1-6
                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }
/**
 * Saves purchase details to tble_buyer SQLite table
 * Columns: buyer_id (auto), name (product name), product, quantity
 */
/**
 * Saves purchase details to tble_buyer SQLite table
 * Columns: buyer_id (auto), name (product name), product, quantity, id_generate
 */
private boolean savePurchaseToHistory(int userId, double totalAmount, String shipping, String payment) {
  
    String uniqueCode = generateUniqueCode();
    
    StringBuilder productsBuilder = new StringBuilder();
    int totalQuantity = 0;
    String[] productNames = {"Jordan", "Nike Zoom Vomero 5", "Air Jordan 1 Low", 
                            "Adidas Samba OG", "New Balance 530", "Puma Speedcat OG"};
    
    for (int i = 0; i < sessionQuantities.length; i++) {
        if (sessionQuantities[i] > 0) {
            if (productsBuilder.length() > 0) productsBuilder.append(", ");
            productsBuilder.append(productNames[i]).append("(").append(sessionQuantities[i]).append(")");
            totalQuantity += sessionQuantities[i];
        }
    }
    
    String insertSQL = "INSERT INTO tble_buyer (name, product, quantity, total_price, " +
                      "shipping_method, payment_method, purchase_date, user_id, id_generate) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    config conf = new config();
    String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    try (Connection cn = conf.connectDB();
         PreparedStatement pst = cn.prepareStatement(insertSQL)) {
        
        pst.setString(1, nm.getText()); 
        pst.setString(2, productsBuilder.toString()); 
        pst.setInt(3, totalQuantity); 
        pst.setDouble(4, totalAmount); 
        pst.setString(5, shipping);
        pst.setString(6, payment);
        pst.setString(7, currentDateTime);
        pst.setInt(8, userId);
        pst.setString(9, uniqueCode); 
        
        pst.executeUpdate();
        return true;
        
    } catch (SQLException e) {
        System.err.println("Error saving purchase: " + e.getMessage());
        return false;
    }
}
   /**
 * Generates a unique 3 letters + 2 special characters code
 * Format: ABC#$ (3 random letters + 2 random special characters)
 */
private String generateUniqueCode() {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String specialChars = "!@#$%^&*";
    StringBuilder code = new StringBuilder();
    java.util.Random random = new java.util.Random();
    
    for (int i = 0; i < 3; i++) {
        code.append(letters.charAt(random.nextInt(letters.length())));
    }
    
    for (int i = 0; i < 2; i++) {
        code.append(specialChars.charAt(random.nextInt(specialChars.length())));
    }
    
    return code.toString();
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(orderss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orderss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orderss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orderss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orderss().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Jtable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner11;
    private javax.swing.JSpinner jSpinner12;
    private javax.swing.JSpinner jSpinner13;
    private javax.swing.JSpinner jSpinner14;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldtotal;
    private javax.swing.JLabel jdates;
    private javax.swing.JLabel jtime;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel name2;
    private javax.swing.JLabel name3;
    private javax.swing.JLabel name4;
    private javax.swing.JLabel name5;
    private javax.swing.JLabel nm;
    private javax.swing.JLabel prices;
    private javax.swing.JLabel prices1;
    private javax.swing.JLabel prices2;
    private javax.swing.JLabel prices3;
    private javax.swing.JLabel prices4;
    private javax.swing.JLabel prices5;
    private javax.swing.JPanel receiptText;
    // End of variables declaration//GEN-END:variables
}
