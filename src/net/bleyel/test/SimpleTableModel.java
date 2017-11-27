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
    private Object[] mirrorCords;
    private boolean algorithm;
    private char primitive;

    private String[] headersDDALine = new String[]{"K", "xk", "yk", "(xk", "yk)"};
    private String[] headersBresLine = new String[]{"K", "pk", "xk+1", "yk+1"};
    private String[] headersEcuacionCircle = new String[]{"K", "ϴ", "xk", "yk", "(xk,", "yk)"};
    private String[] headersPuntoCircle = new String[]{"K", "pk", "xk+1", "yk+1", "2xk+1,", "2yk+1)"};
    private String[] headersMirrorCords = new String[]{"x", "y"};
    private String[] headersEcuacionElipse = new String[]{"K", "ϴ", "xk", "yk", "(xk,","yk)"};
    private String[] headersPuntoElipse = new String[]{"K", "p1k", "(xk+1,", "yk+1)", "2ry² * xk+1","2rx² * yk+1"};


    public SimpleTableModel(int rows, int cols, ArrayList<Object> data, boolean algorithm, char primitive) {
        super();
        setRows(rows);
        setCols(cols);
        setData(data);
        setAlgorithm(algorithm);
        setPrimitive(primitive);
        rowData = new Object[cols];
        mirrorCords = new Object[2];
        initModelData();
    }

    private void initModelData() {

        for (int i = 0; i < cols; i++) {
            switch (primitive) {
                case 'l':
                    if (algorithm)
                        this.addColumn(headersDDALine[i]);
                    else
                        this.addColumn(headersBresLine[i]);

                    break;
                case 'c':
                    if (algorithm)
                        this.addColumn(headersEcuacionCircle[i]);
                    else
                        this.addColumn(headersPuntoCircle[i]);

                    break;
                case 'e':
                    if(algorithm)
                        this.addColumn(headersEcuacionElipse[i]);
                    else
                        this.addColumn(headersPuntoElipse[i]);
                    break;
                default:
                    break;
            }

        }

        switch (primitive) {
            case 'l':
                fillTable();
                break;
            case 'c':
                if (algorithm) {
                    //Ecu parametricas
                    fillTable();
                } else {
                    int k=fillTable();
                    //punto medio
                    this.addRow(headersMirrorCords);

                    while (k<data.size()){
                        for (int m =0; m<2;m++) {
                            mirrorCords[m] = data.get(k);
                            k++;
                        }
                        this.addRow(mirrorCords);
                    }
                }
                break;
            case 'e':
                if (algorithm){
                    //Ecu
                    fillTable();
                }else{
                    //punto
                    int k = fillTable();
                    this.addRow(headersMirrorCords);

                    while (k<data.size()){
                        for (int m =0; m<2;m++) {
                            mirrorCords[m] = data.get(k);
                            k++;
                        }
                        this.addRow(mirrorCords);
                    }
                }
                break;
            default:
                break;
        }
    }

    private int fillTable() {
        int k = 0;
        for (int j = 0; j < rows; j++) {
            for (int n = 0; n < cols; n++) {
                rowData[n] = data.get(k);
                k++;
            }
            this.addRow(rowData);
        }
        return k;
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

    public char getPrimitive() {
        return primitive;
    }

    public void setPrimitive(char primitive) {
        this.primitive = primitive;
    }

    public Object[] getMirrorCords() {
        return mirrorCords;
    }

    public void setMirrorCords(Object[] mirrorCords) {
        this.mirrorCords = mirrorCords;
    }
}
