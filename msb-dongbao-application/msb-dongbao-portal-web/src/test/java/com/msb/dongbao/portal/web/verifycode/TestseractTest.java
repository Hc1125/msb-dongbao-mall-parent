package com.msb.dongbao.portal.web.verifycode;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TestseractTest {
    public static void main(String[] args) throws TesseractException {
        ITesseract iTesseract = new Tesseract();
        // 语言包加进来
        iTesseract.setDatapath("E:\\Tessdata");
        iTesseract.setLanguage("eng");

        File fileDir = new File("E:\\codedata");
        for (File file : fileDir.listFiles()) {
            String s = iTesseract.doOCR(file);
            System.out.println(file.getName() + "识别后的数字是：" + s);
        }
    }
}
