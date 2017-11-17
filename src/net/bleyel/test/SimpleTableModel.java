package net.bleyel.test;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by andi on 08.11.17.
 */
public class SimpleTableModel extends DefaultTableModel {

    private int rows, cols;
    private ArrayList<Float> data;
    private Object[] rowData;

    private java.lang.String[] headersDDALine = new java.lang.String[]{"K", "xk", "yk", "(xk","yk)"};
    private java.lang.String[] headersBresLine = new java.lang.String[]{"K", "pk", "xk+1", "yk+1"};

    public SimpleTableModel(int rows, int cols, ArrayList<Float> data) {
        super();
        setRows(rows);
        setCols(cols);
        setData(data);
        rowData = new Object[cols];
        initModelData();
    }

    private void initModelData() {

        for (int i = 0; i < cols; i++) {
            this.addColumn(headersDDALine[i]);
        }

        int k=0;
        for (int j = 0; j < rows; j++) {

            for (int i = 0; i < cols; i++) {
                rowData[i] = data.get(k);
                k++;
            }
            this.addRow(rowData);
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public ArrayList<Float> getData() {
        return data;
    }

    public void setData(ArrayList<Float> data) {
        this.data = data;
    }
}
