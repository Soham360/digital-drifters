package com.nighthawk.spring_portfolio.mvc.fibo;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.io.File;

class Fibo {
    private BufferedImage image;
    private Graphics graphics;
    private Random random;
    private Supplier<BufferedImage> generator;

    private static final float SQRT5 = (float)Math.sqrt(5);
    // private static final int CANVAS_WIDTH = 600;
    // private static final int CANVAS_HEIGHT = 400;
    private static final int CANVAS_WIDTH = 5000;
    private static final int CANVAS_HEIGHT = 5000;

    public Fibo(String type) {
        final ArrayList<Supplier<BufferedImage>> list = new ArrayList<>();
        list.add(this::recursiveArt);
        list.add(this::recursiveArtWithBinet);
        list.add(this::iterativeArt);
        list.add(this::maxtrixArt);

        image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
        random = new Random();

        if(type.equals("random")) {
            generator = list.get(random.nextInt(list.size()));
        } else {
            generator = switch (type) {
                case "recursive" -> list.get(0);
                case "recursiveWithBinet" -> list.get(1);
                case "iterative" -> list.get(2);
                case "matrix" -> list.get(3);
                default -> list.get(0);
            };
        }
    }

    public BufferedImage generate() {
        return generator.get();
    }

    private BufferedImage recursiveArt() {
        final int centerX = CANVAS_WIDTH / 2;
        final int centerY = CANVAS_HEIGHT / 2;
        recursiveDraw(centerX, centerY, 1, 1, 0, 20);
        return image;
    }

    private void recursiveDraw(int x, int y, int a, int b, int count, int maxInterations) {
        if(count < maxInterations) {
            graphics.setColor(randomHSLColor());
            graphics.fillArc(x, y, (int)(a * 2.5), (int)(a * 2.5), 0, radianToDegree(2*Math.PI));
            final int nextX = x + (int)(b * 3 * Math.cos((double)count));
            final int nextY = y + (int)(b * 3 * Math.sin((double)count));
            recursiveDraw(nextX, nextY, b, a + b, count + 1, maxInterations);
        }
    }

    private int radianToDegree(double radian) {
        return (int)(radian * 180 / Math.PI);
    }

    private Color randomHSLColor() {
        float h = random.nextFloat() * 360;
        float s = 70.0f;
        float l = 50.0f;
        return Color.getHSBColor(h, s, l);
    }

    private BufferedImage recursiveArtWithBinet() {
        final int centerX = CANVAS_WIDTH / 2;
        final int centerY = CANVAS_HEIGHT / 2;
        recursiveDrawWithBinet(centerX - 300, centerY - 200, 0.5, 20, 0.0);
        return image;
    }

    private void recursiveDrawWithBinet(int x, int y, double size, int remainingIterations, double angle) {
        if(remainingIterations > 0) {
            graphics.setColor(randomHSLColor());
            graphics.fillArc(x, y, (int)(size), (int)(size), 0, radianToDegree(2*Math.PI));
            final int newSize = fibonacciBinet(remainingIterations);
            final int nextX = x + (int)(newSize * 0.02 * Math.cos(angle));
            final int nextY = y + (int)(newSize * 0.02 * Math.sin(angle));
            final double newAngle = angle + Math.PI / 3;
            recursiveDrawWithBinet(nextX, nextY, newSize * 0.1, remainingIterations - 1, newAngle);
        }
    }

    private int fibonacciBinet(int n) {
        return Math.round(
            (1.0f / SQRT5) *
            (pow((1 + SQRT5)/2, n) - pow((1 - SQRT5)/2, n))
        );
    }

    private float pow(float a, int n) {
        if(n <= 0) {
            return 1;
        }
        float output = 1;
        for(int i = 0; i < n; i++) {
            output *= a;
        }
        return output;
    }

    private BufferedImage iterativeArt() {
        final int centerX = CANVAS_WIDTH / 2;
        final int centerY = CANVAS_HEIGHT / 2;
        int x = centerX;
        int y = centerY;
        int a = 1;
        int b = 1;
        int count = 0;
        for(int i = 0; i < CANVAS_WIDTH; i++) {
            graphics.setColor(randomHSLColor());
            graphics.fillArc(x, y, (int)(a * 2.5), (int)(a * 2.5), 0, radianToDegree(2*Math.PI));
            final int nextX = x + (int)(b * 3 * Math.cos((double)count));
            final int nextY = y + (int)(b * 3 * Math.sin((double)count));
            x = nextX;
            y = nextY;
            a = b;
            b = a + b;
            count++;
        }
        return image;
    }

    private int fibonacciMatrix(int n) {
        if (n == 0) {
            return 0;
        }

        final int[][] baseMatrix = {
            {1, 1},
            {1, 0}
        };

        final int[][] result = matrixPower(baseMatrix, n - 1);
        return result[0][0];
    }

    private int[][] matrixPower(int[][] matrix, int power) {
        int[][] result = {{1, 0}, {0, 1}};

        if (power == 0) {
            return result;
        }

        while(power > 0) {
            if ((power & 1) == 1) {
                result = multiplyMatrix(result, matrix);
            }
            matrix = multiplyMatrix(matrix, matrix);
            power >>= 1;
        }
        return result;
    }

    private int[][] multiplyMatrix(int[][] a, int[][] b) {
        final int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    private BufferedImage maxtrixArt() {
        final int centerX = CANVAS_WIDTH / 2;
        final int centerY = CANVAS_HEIGHT / 2;
        double angle = 0;
        final double scale = 0.8;
        for(int i = 1; i < 20; i++) {
            final int fibNumber = fibonacciMatrix(i);
            final int size = (int)(fibNumber * scale);
            final int x = centerX + (int)(size * 1 * Math.cos(angle));
            final int y = centerY + (int)(size * 1 * Math.sin(angle));
            graphics.setColor(randomHSLColor());
            graphics.fillArc(x, y, size, size, 0, radianToDegree(2*Math.PI));
            angle += Math.PI / 6;
        }
        return image;
    }
}