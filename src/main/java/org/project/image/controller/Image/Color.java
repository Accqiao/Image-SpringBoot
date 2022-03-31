package org.project.image.controller.Image;

import cn.hutool.core.img.ImgUtil;

import java.awt.image.BufferedImage;


public class Color {

    //输入一张图片 返回该图片的均值色彩
    public String getAvgRGB(BufferedImage image){
        float colorAvgVal = 53.5f;
        BufferedImage bi = image;
        int w = bi.getWidth();
        int h = bi.getHeight();
        float[] dots = new float[]{0.15f, 0.35f, 0.5f, 0.7f, 0.85f};
        int R = 0;int G = 0;int B = 0;
        for(float dw : dots){
            for(float dh : dots){
                int rgbVal = bi.getRGB((int)(w*dw), (int)(h*dh));
                java.awt.Color color = ImgUtil.getColor(rgbVal);
                R += color.getRed();
                G += color.getGreen();
                B += color.getBlue();
            }
        }
        int cn = dots.length * dots.length;
        java.awt.Color color = new java.awt.Color( R / cn, G / cn, B / cn );

        int r = Math.round(color.getRed()/colorAvgVal);
        int g = Math.round(color.getGreen()/colorAvgVal);
        int b = Math.round(color.getBlue()/colorAvgVal);
        String colorKey = String.format("%s%s%s",r ,g ,b);
        return ColorMap.getColorMap().get( colorKey );
    }

}


