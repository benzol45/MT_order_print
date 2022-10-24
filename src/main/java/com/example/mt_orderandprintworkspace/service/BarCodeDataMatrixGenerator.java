package com.example.mt_orderandprintworkspace.service;

import uk.org.okapibarcode.backend.DataMatrix;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.output.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarCodeDataMatrixGenerator {
    //Общая концепция генерации DataMatrix //https://blog.jayway.com/2016/06/30/gs1-datamatrix-codes-java/
    //TODO NB При выводе ОБЯЗАТЕЛЬНО добавлять "тихую зону" минимум 3 модуля кода. Иначе !!! НЕ ЧИТАЕТСЯ КОД

    public static final int MODULE_SIZE_FOR_30X20 = 3;

    public static BufferedImage generateBarCodeDataMatrix(String code) {
        //Код должен начинаться с <FNC1>. Только при этом проверка проходится в "Чекмарк" хотя ЧЗ сказал - не обязательно.
        //https://github.com/woo-j/OkapiBarcode
        //https://stackoverflow.com/questions/33521900/how-to-handle-fnc1-gs-when-encoding-gs1-compatible-datamatrix-code

        // Set up the DataMatrix object
        DataMatrix dataMatrix = new DataMatrix();
        //Размер всего получаемого изображения. По сути масштабируется.
        dataMatrix.setModuleWidth(MODULE_SIZE_FOR_30X20);
        //Включить разделитель между группами <GS>
        dataMatrix.setGs1SeparatorGs(true);
        // We need a GS1 DataMatrix barcode.
        dataMatrix.setDataType(Symbol.DataType.GS1);
        // 0 means size will be set automatically according to amount of data (smallest possible).
        dataMatrix.setPreferredSize(0);
        // Don't want no funky rectangle shapes, if we can avoid it.
        //dataMatrix.forceSquare(true);
        dataMatrix.setContent(code);

        int width = dataMatrix.getWidth();
        int height = dataMatrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = image.createGraphics();
        //Ниже это неудачные попытки добавить поля "тихой зоны" прямо в ихображение кода
     /*   g2d.setColor(Color.YELLOW);
        int borderedImageWidth = 10;
        int borderedImageHeight = 10;
        g2d.fillRect(0, 0, borderedImageWidth, borderedImageHeight);
        g2d.drawImage(image, null, 10, 10);
        //g.drawImage(source, borderLeft, borderTop, width + borderLeft, height + borderTop, 0, 0, width, height, Color.YELLOW, null);*/
        Java2DRenderer renderer = new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
        renderer.render(dataMatrix);

        //Можно вот так сохранить прямо картинкой на диск. Это на случай если хранить готовые а не генерить каждый раз
        //ImageIO.write(image, "png", new File("C:\\Users\\benzo\\GS1_datamatrix.png"));

        return image;
    }
}
