package dashboard;

import Config.config;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class AddProductForm extends JFrame {

    private JTextField txtName, txtBrand, txtSize, txtPrice, txtQuantity;

    public AddProductForm() {

        setTitle("Add Product");
        setSize(350, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout(10,10));

        JPanel panel = new JPanel(new GridLayout(6,2,8,8));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        panel.add(new JLabel("Product Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Brand:"));
        txtBrand = new JTextField();
        panel.add(txtBrand);

        panel.add(new JLabel("Size:"));
        txtSize = new JTextField();
        panel.add(txtSize);

        panel.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        panel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        panel.add(txtQuantity);

        JButton btnAdd = new JButton("Add");
        panel.add(new JLabel()); // empty space
        panel.add(btnAdd);

        add(panel, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addProduct());
    }

    private void addProduct() {
        try {
            String name = txtName.getText();
            String brand = txtBrand.getText();
            String size = txtSize.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            config con = new config();
            Connection cn = con.connectDB();
            String sql = "INSERT INTO tble_order (PName, Brand, Size, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, brand);
            pst.setString(3, size);
            pst.setDouble(4, price);
            pst.setInt(5, quantity);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Product added.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }
}