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
    private JButton btn_reset;
    private JButton btn_ok;
    private JButton btn_line;
    private JButton btn_circle;
    private JButton btn_elipse;
    private JLabel lbl_radius;
    private JTextField txt_radius;
    private JLabel lbl_centerX;
    private JTextField txt_centerX;
    private JLabel lbl_centerY;
    private JTextField txt_centerY;
    private JLabel lbl_rx;
    private JTextField txt_rx;
    private JLabel lbl_ry;
    private JTextField txt_ry;
    private JComboBox cBox_circleElipse;
    private char primitive;
    private int width = 800;
    private int height = 600;

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
                    JFrame drawingFrame = new JFrame("Drawing Frame");
                    DrawPrimitives panel = new DrawPrimitives(width, height, Color.WHITE);

                    boolean algorithm;
                    switch (primitive) {
                        case 'l':
                            int xStartLine = Integer.parseInt(txt_xStart.getText());
                            int xEndLine = Integer.parseInt(txt_xEnd.getText());
                            int yStartLine = Integer.parseInt(txt_yStart.getText());
                            int yEndLine = Integer.parseInt(txt_yEnd.getText());

                            String methodLine = cBox_line.getSelectedItem().toString();
                            if (methodLine.matches("DDA")) algorithm = true;
                            else algorithm = false;

                           panel.drawLine(xStartLine, yStartLine, xEndLine, yEndLine, Color.black, algorithm);

                            break;

                        case 'c':
                            /*int xCenterCircle = Integer.parseInt(txt_centerX.getText());
                            int yCenterCircle = Integer.parseInt(txt_centerY.getText());
                            int radiusCircle = Integer.parseInt(txt_radius.getText());
*/
                            int xCenterCircle = 37;
                            int yCenterCircle = 86;
                            int radiusCircle = 17;
                            String methodCircle = cBox_circleElipse.getSelectedItem().toString();
                            if (methodCircle.matches("Ecuaciones parametricas")) algorithm = true;
                            else algorithm = false;

                            panel.drawCircle(xCenterCircle,yCenterCircle,radiusCircle,algorithm,Color.black);

                            break;
                        case 'e':
                            break;
                        default:
                            break;
                    }

                    drawingFrame.add(panel);
                    drawingFrame.pack();
                    drawingFrame.setVisible(true);
                    drawingFrame.setResizable(false);
                    drawingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
                primitive = 'l';

                txt_xStart.setVisible(true);
                txt_xEnd.setVisible(true);
                txt_yStart.setVisible(true);
                txt_yEnd.setVisible(true);
                lbl_lineXstart.setVisible(true);
                lbl_lineXend.setVisible(true);
                lbl_lineYstart.setVisible(true);
                lbl_lineYend.setVisible(true);
                cBox_line.setVisible(true);
            }
        });
        btn_circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hideAllElements();
                primitive = 'c';

                lbl_radius.setVisible(true);
                txt_radius.setVisible(true);
                lbl_centerX.setVisible(true);
                txt_centerX.setVisible(true);
                lbl_centerY.setVisible(true);
                txt_centerY.setVisible(true);
                cBox_circleElipse.setVisible(true);

            }
        });
        btn_elipse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hideAllElements();
                primitive = 'e';

                lbl_radius.setVisible(true);
                txt_radius.setVisible(true);
                lbl_centerX.setVisible(true);
                txt_centerX.setVisible(true);
                lbl_centerY.setVisible(true);
                txt_centerY.setVisible(true);

                lbl_rx.setVisible(true);
                txt_rx.setVisible(true);
                lbl_ry.setVisible(true);
                txt_ry.setVisible(true);

                cBox_circleElipse.setVisible(true);

            }
        });
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
        cBox_line.setVisible(false);

        lbl_radius.setVisible(false);
        txt_radius.setVisible(false);
        lbl_centerX.setVisible(false);
        txt_centerX.setVisible(false);
        lbl_centerY.setVisible(false);
        txt_centerY.setVisible(false);

        lbl_rx.setVisible(false);
        txt_rx.setVisible(false);
        lbl_ry.setVisible(false);
        txt_ry.setVisible(false);

        cBox_circleElipse.setVisible(false);
    }
}
