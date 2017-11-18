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
    private JComboBox cBox_line;
    private JLabel lbl_lineXstart;
    private JLabel lbl_lineXend;
    private JLabel lbl_lineYstart;
    private JLabel lbl_lineYend;
    private JLabel lbl_typOfPrimitive;
    private JButton btn_reset;
    private JButton btn_ok;
    private JButton btn_line;
    private JButton btn_circle;
    private JButton btn_elipse;
    private char primitive;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InputValues");
        frame.setContentPane(new InputValues().mainPanel);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public InputValues() {
        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    boolean algorithm = true;
                    switch (primitive) {
                        case 'l':
                            /*int xStart = Integer.parseInt(txt_xStart.getText());
                    int xEnd = Integer.parseInt(txt_xEnd.getText());
                    int yStart = Integer.parseInt(txt_yStart.getText());
                    int yEnd = Integer.parseInt(txt_yEnd.getText());*/
                            String method = cBox_line.getSelectedItem().toString();
                            if (method.matches("DDA")) algorithm = true;
                            else algorithm = false;
                            break;
                        case 'c':
                            break;
                        case 'e':
                            break;
                        default:
                            break;
                    }

                    int xStart = 50;
                    int yStart = 71;
                    int xEnd = 32;
                    int yEnd = 79;
                    showDrawing(xStart, xEnd, yStart, yEnd,algorithm);
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
        btn_line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hideAllElements();
                txt_xStart.setVisible(true);
                txt_xEnd.setVisible(true);
                txt_yStart.setVisible(true);
                txt_yEnd.setVisible(true);
                lbl_lineXstart.setVisible(true);
                lbl_lineXend.setVisible(true);
                lbl_lineYstart.setVisible(true);
                lbl_lineYend.setVisible(true);
                lbl_typOfPrimitive.setVisible(true);
                cBox_line.setVisible(true);
                primitive = 'l';
            }
        });
    }

    private void showDrawing(int xStart, int xEnd, int yStart, int yEnd,boolean algorithm) {
        int width = 800;
        int height = 600;

        JFrame drawingFrame = new JFrame("Drawing Frame");
        DrawPrimitives panel = new DrawPrimitives(width, height, Color.WHITE);
        panel.drawLine(xStart, yStart, xEnd, yEnd, Color.black, algorithm); //Bres

        drawingFrame.add(panel);
        drawingFrame.pack();
        drawingFrame.setVisible(true);
        drawingFrame.setResizable(false);
        drawingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void hideAllElements() {
        txt_xStart.setVisible(false);
        txt_xEnd.setVisible(false);
        txt_yStart.setVisible(false);
        txt_yEnd.setVisible(false);
        lbl_lineXstart.setVisible(false);
        lbl_lineXend.setVisible(false);
        lbl_lineYstart.setVisible(false);
        lbl_lineYend.setVisible(false);
        lbl_typOfPrimitive.setVisible(false);
        cBox_line.setVisible(false);
    }
}
