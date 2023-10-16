package org.example;

import com.google.gson.Gson;
import java.io.FileReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EjercicioB {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Sales App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Car");
        model.addColumn("Price");
        model.addColumn("State");

        try {
            // Especifica el path absoluto del archivo JSON
            String filePath = "C:/Users/cecyh/Downloads/car_sales.json";

            FileReader reader = new FileReader(filePath);
            EstCarSale[] carSales = new Gson().fromJson(reader, EstCarSale[].class);

            for (EstCarSale sale : carSales) {
                model.addRow(new Object[]{
                        sale.getId(),
                        sale.getFirst_name(),
                        sale.getLast_name(),
                        sale.getCar(),
                        sale.getPrice(),
                        sale.getState()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.pack();
        frame.setVisible(true);
    }
}
