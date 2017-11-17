package net.bleyel.test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andi on 08.11.17.
 */
public class Main {

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        JFrame frame = new JFrame("Direct draw demo");

        DrawPrimitives panel = new DrawPrimitives(width, height, Color.WHITE);
        //panel.drawRect(Color.RED,20,20,40,40);
        panel.drawLine(100,27,120,200,Color.black);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
