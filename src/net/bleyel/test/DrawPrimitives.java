package net.bleyel.test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPrimitives extends JPanel {

    private BufferedImage canvas;
    private int width;
    private int height;

    public DrawPrimitives(int width, int height, Color background) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(background);
        setWidth(width);
        setHeight(height);
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
        int xPixel;
        int yPixel;

        int dx = xEnd - xStart;
        int dy = yEnd - yStart;

        if (Math.abs(dx) > Math.abs(dy))
            sumRows = Math.abs(dx);
        else
            sumRows = Math.abs(dy);

        if (xStart >= 0 && xStart <= width && yStart >= 0 && yStart <= height) {
            canvas.setRGB(xStart, height - yStart, Color.green.getRGB());
        }

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
                if (xPixel >= 0 && xPixel <= width && yPixel >= 0 && yPixel <= height) {
                    canvas.setRGB(xPixel, height - yPixel, c.getRGB());
                }

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
            int dx2 = 2 * Math.abs(dx);
            int dy2 = 2 * Math.abs(dy);

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
                if (xPixel >= 0 && xPixel <= width && yPixel >= 0 && yPixel <= height) {
                    canvas.setRGB(xPixel, height - yPixel, c.getRGB());
                }
            }
        }

        if (xEnd >= 0 && xEnd <= width && yEnd >= 0 && yEnd <= height) {
            canvas.setRGB(xEnd, height - yEnd, Color.red.getRGB());
        }
        new SimpleTableClass(sumRows+1, cols, data, algorithm, 'l');
        repaint();
    }

    public void drawCircle(int xCenter, int yCenter, int radius, boolean algorithm, Color c) {

        ArrayList<Object> data = new ArrayList<Object>();
        int sumRows = 1;
        int cols = 6;
        double xk;
        double yk;
        double theta = 1 / (float) radius;
        int xPixel;
        int yPixel;
        int j = 0;
        ArrayList<Integer> coordsPosPos = new ArrayList<>();
        ArrayList<Integer> mirrorCords = new ArrayList<>();


        if (algorithm) {
            //Ecu parametricas

            data.add(0);
            data.add(0);
            data.add(xCenter + radius);
            data.add(yCenter);
            data.add(xCenter + radius);
            data.add(yCenter);

            while (theta < 2 * Math.PI) {
                xk = xCenter + radius * Math.cos(theta);
                yk = yCenter + radius * Math.sin(theta);
                xPixel = Math.round((float) xk);
                yPixel = Math.round((float) yk);

                data.add(++j);
                data.add(theta);
                data.add(xk);
                data.add(yk);
                data.add(xPixel);
                data.add(yPixel);

                System.out.println("x: " + xPixel + " y: " + yPixel);
                if (xPixel >= 0 && xPixel <= width && yPixel >= 0 && yPixel <= height) {
                    canvas.setRGB(xPixel, height - yPixel, c.getRGB());
                }
                theta += 1 / (float) radius;
                sumRows++;
            }

            /*mirrorCords = mirrorViertel(coordsPosPos,radius);

            int n=0;
            xPixel=0;
            for (Integer coord : mirrorCords) {
                if(n%2==0){
                    xPixel = coord;
                }else{
                    canvas.setRGB(xPixel, height - coord, c.getRGB());
                }
                n++;
            }

            data.addAll(mirrorCords);*/

        } else {
            //punto medio
        }

        new SimpleTableClass(sumRows, cols, data, algorithm, 'c');
        repaint();
    }

    private ArrayList<Integer> mirrorViertel(ArrayList<Integer> pospos, int radius) {
        ArrayList<Integer> coordsPosNeg = new ArrayList<>();
        ArrayList<Integer> coordsNegPos = new ArrayList<>();
        ArrayList<Integer> coordsNegNeg = new ArrayList<>();
        ArrayList<Integer> allCoords = new ArrayList<>();
        int i = 0;

        for (Integer coord : pospos) {
            if (i % 2 == 0)  // gerade
                coordsPosNeg.add(coord);
            else // ungerade
                coordsPosNeg.add(coord * -1);

            i++;
        }
        i = 0;
        for (Integer coord : pospos) {
            if (i % 2 == 0)  // gerade
                coordsNegPos.add(coord * -1);
            else // ungerade
                coordsNegPos.add(coord);
            i++;
        }

        for (Integer coord : pospos) {
            coordsNegNeg.add(coord * -1);
        }

        allCoords.addAll(coordsPosNeg);
        allCoords.addAll(coordsNegPos);
        allCoords.addAll(coordsNegNeg);

        return allCoords;
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


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
