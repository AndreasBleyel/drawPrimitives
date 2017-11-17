package net.bleyel.test;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by andi on 08.11.17.
 */
public class SimpleTableModel extends DefaultTableModel {

    private int rows, cols;
    private ArrayList<Object> data;
    private Object[] rowData;
    private boolean algorithm;

    private java.lang.String[] headersDDALine = new java.lang.String[]{"K", "xk", "yk", "(xk", "yk)"};
    private java.lang.String[] headersBresLine = new java.lang.String[]{"K", "pk", "xk+1", "yk+1"};

    public SimpleTableModel(int rows, int cols, ArrayList<Object> data, boolean algorithm) {
        super();
        setRows(rows);
        setCols(cols);
        setData(data);
        setAlgorithm(algorithm);
        rowData = new Object[cols];
        initModelData();
    }

    private void initModelData() {

        for (int i = 0; i < cols; i++) {
            if (algorithm)
                this.addColumn(headersDDALine[i]);
            else
                this.addColumn(headersBresLine[i]);
        }

        int k = 0;

        for (int j = 0; j <= rows; j++) {
            for (int i = 0; i < cols; i++) {
                System.out.println(data.size() + " rows: " + rows + " cols: " + cols + " k: " + k);
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

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public boolean isAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(boolean algorithm) {
        this.algorithm = algorithm;
    }
}
