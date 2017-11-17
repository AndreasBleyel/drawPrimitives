package net.bleyel.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andi on 16.11.17.
 */
public class InputValues {
    private JPanel mainPanel;
    private JTextField txt_xStart;
    private JTextField txt_xEnd;
    private JTextField txt_yStart;
    private JTextField txt_yEnd;
    private JComboBox comboBox1;
    private JLabel lbl_lineXstart;
    private JLabel lbl_lineXend;
    private JLabel lbl_lineYstart;
    private JLabel lbl_lineYend;
    private JLabel lbl_typOfPrimitive;
    private JButton btn_reset;
    private JButton btn_ok;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputValues");
        frame.setContentPane(new InputValues().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public InputValues() {
        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                 try {
                    /*int xStart = Integer.parseInt(txt_xStart.getText());
                    int xEnd = Integer.parseInt(txt_xEnd.getText());
                    int yStart = Integer.parseInt(txt_yStart.getText());
                    int yEnd = Integer.parseInt(txt_yEnd.getText());*/
                    int xStart=50;
                    int yStart=71;
                    int xEnd=32;
                    int yEnd=79;
                    showDrawing(xStart, xEnd, yStart, yEnd);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                    e.printStackTrace();
                }
            }
        });
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txt_xStart.setText("");
                txt_xEnd.setText("");
                txt_yStart.setText("");
                txt_yEnd.setText("");
            }
        });
    }

    private void showDrawing(int xStart, int xEnd, int yStart, int yEnd){
        int width = 800;
        int height = 600;

        JFrame drawingFrame = new JFrame("Drawing Frame");
        DrawPrimitives panel = new DrawPrimitives(width, height, Color.WHITE);
        //panel.drawLine(xStart,yStart,xEnd,yEnd,Color.black,false); //Bres
        panel.drawLine(xStart,yStart,xEnd,yEnd,Color.black,true); //DDA

        drawingFrame.add(panel);
        drawingFrame.pack();
        drawingFrame.setVisible(true);
        drawingFrame.setResizable(false);
        drawingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
