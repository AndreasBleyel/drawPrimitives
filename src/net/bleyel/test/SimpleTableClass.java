package net.bleyel.test;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

/**
 * Created by andi on 08.11.17.
 */
public class SimpleTableClass extends JFrame {

    private JTable table;

    public SimpleTableClass(int rows, int cols, ArrayList<Object> data, boolean algorithm, char primitive) {
        table = new JTable(new SimpleTableModel(rows, cols,data,algorithm,primitive));
        setColumnWidth();
        JScrollPane sPane = new JScrollPane(table);

        getContentPane().add(sPane);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void setColumnWidth() {
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn c = table.getColumnModel().getColumn(i);
            c.setPreferredWidth(JTable.AUTO_RESIZE_ALL_COLUMNS);

        }
    }
}
