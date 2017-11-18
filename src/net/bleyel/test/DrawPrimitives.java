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


    public void drawLine(int xStart, int yStart, int xEnd, int yEnd, Color c, boolean algorithm) {
        // Implement line drawing
        //DDA
        //algo true = DDA
        //algo false = Bresenham

        ArrayList<Object> data = new ArrayList<Object>();

        int sumRows;
        int cols;
        int xPixel = 0;
        int yPixel = 0;

        int dx = xEnd - xStart;
        int dy = yEnd - yStart;

        if (Math.abs(dx) > Math.abs(dy))
            sumRows = Math.abs(dx);
        else
            sumRows = Math.abs(dy);


        canvas.setRGB(xStart, 600 - yStart, Color.green.getRGB());

        if (algorithm) {
            //DDA

            data.add(0);
            data.add(xStart);
            data.add(yStart);
            data.add(xStart);
            data.add(yStart);

            cols = 5;
            float xk1 = xStart;
            float yk1 = yStart;

            float xIncrement = dx / (float) sumRows;
            float yIncrement = dy / (float) sumRows;

            for (int k = 1; k < sumRows; k++) {
                xk1 += xIncrement;
                yk1 += yIncrement;

                xPixel = Math.round(xk1);
                yPixel = Math.round(yk1);

                System.out.println("xpix: " + xPixel + " ypix: " + yPixel);
                canvas.setRGB(xPixel, 600 - yPixel, c.getRGB());

                data.add(k);
                data.add(xk1);
                data.add(yk1);
                data.add(xPixel);
                data.add(yPixel);
            }

            data.add(sumRows);
            data.add(xEnd);
            data.add(yEnd);
            data.add(xEnd);
            data.add(yEnd);

        } else {
            //Bresenham

            cols = 4;
            int dx2 = 2 * Math.abs(dx); // slope scaling factors to
            int dy2 = 2 * Math.abs(dy); // avoid floating point

            int ix = xStart < xEnd ? 1 : -1; // increment direction
            int iy = yStart < yEnd ? 1 : -1;

            int p = dy2 - Math.abs(dx);

            data.add("-");
            data.add("-");
            data.add(xStart);
            data.add(yStart);

            xPixel = xStart;
            yPixel = yStart;
            for (int i = 0; i < Math.abs(dx); i++) {
                if (p < 0) {
                    xPixel += ix;
                    p = p + dy2;
                } else {
                    xPixel += ix;
                    yPixel += iy;
                    p = p + dy2 - dx2;
                }

                data.add(i);
                data.add(p);
                data.add(xPixel);
                data.add(yPixel);

                canvas.setRGB(xPixel, 600 - yPixel, c.getRGB());
            }
        }


        canvas.setRGB(xEnd, 600 - yEnd, Color.red.getRGB());

        new SimpleTableClass(sumRows, cols, data, algorithm, 'l');
        repaint();
    }

    public void drawCircle(int xCenter, int yCenter, int radius, boolean algorithm){
        System.out.println(xCenter+" "+yCenter+" "+radius+" "+algorithm);
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
