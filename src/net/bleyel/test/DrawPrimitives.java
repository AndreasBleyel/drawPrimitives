package net.bleyel.test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPrimitives extends JPanel {

    private BufferedImage canvas;

    public DrawPrimitives(int width, int height, Color background) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(background);
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }


    public void drawLine(int xStart, int yStart, int xEnd, int yEnd, Color c) {
        // Implement line drawing
        //DDA

        ArrayList<Object> data = new ArrayList<Object>();

        int dx = xEnd - xStart;
        int dy = yEnd - yStart;
        int sumRows;
        int xPixel=0;
        int yPixel=0;
        float xk1=xStart;
        float yk1=yStart;

        if(Math.abs(dx) > Math.abs(dy))
            sumRows = Math.abs(dx);
        else
            sumRows=Math.abs(dy);

        float xIncrement = dx / (float) sumRows;
        float yIncrement = dy / (float) sumRows;

        data.add(0);
        data.add(xStart);
        data.add(600-yStart);
        data.add(xStart);
        data.add(600-yStart);
        canvas.setRGB(xStart, yStart, Color.green.getRGB());


        int lastK=0;
        for(int k=1; k < sumRows; k++){
            xk1+=xIncrement;
            yk1+=yIncrement;

            xPixel = Math.round(xk1);
            yPixel = Math.round(yk1);

            System.out.println("xpix: "+xPixel+" ypix: "+yPixel);
            canvas.setRGB(xPixel, yPixel, c.getRGB());

            data.add(k);
            data.add(xk1);
            data.add(600-yk1);
            data.add(xPixel);
            data.add(600-yPixel);
            lastK=k;
        }

        data.add(lastK++);
        data.add(xEnd);
        data.add(600-yEnd);
        data.add(xEnd);
        data.add(600-yEnd);
        canvas.setRGB(xEnd, yEnd, Color.red.getRGB());

        repaint();
        new SimpleTableClass(sumRows, 5, data);
    }

    public void drawRect(Color c, int x1, int y1, int width, int height) {
        int color = c.getRGB();
        // Implement rectangle drawing
        for (int x = x1; x < x1 + width; x++) {
            for (int y = y1; y < y1 + height; y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    public void drawOval(Color c, int x1, int y1, int width, int height) {
        // Implement oval drawing
        repaint();
    }
}
