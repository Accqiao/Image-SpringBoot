package org.project.image.controller.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DHash {
    /**
     * 差值哈希算法
     * @param src
     * @return
     */
    public String getDHash(BufferedImage src) {
        int width = 9;
        int height = 8;
        BufferedImage image = this.resize(src,height,width);
        int[] ints = new int[width * height];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                int gray = this.gray(pixel);
                ints[index++] = gray;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < height;i++){
            for (int j = 0;j < width - 1;j++){
                if (ints[9 * j + i] >= ints[9 * j + i + 1]){
                    builder.append(1);
                }else {
                    builder.append(0);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 简化色彩
     * @param rgb
     * @return
     */
    private int gray(int rgb) {
        int a = rgb & 0xff000000;//将最高位（24-31）的信息（alpha通道）存储到a变量
        int r = (rgb >> 16) & 0xff;//取出次高位（16-23）红色分量的信息
        int g = (rgb >> 8) & 0xff;//取出中位（8-15）绿色分量的信息
        int b = rgb & 0xff;//取出低位（0-7）蓝色分量的信息
        rgb = (r * 77 + g * 151 + b * 28) >> 8;    // NTSC luma，算出灰度值
        //(int)(r * 0.3 + g * 0.59 + b * 0.11)
        return a | (rgb << 16) | (rgb << 8) | rgb;//将灰度值送入各个颜色分量
    }

    /**
     * 改变图片尺寸
     * @param src 原图片
     * @param height 目标高度
     * @param width 目标宽度
     * @return
     */
    private BufferedImage resize(BufferedImage src, int height, int width) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(src, 0, 0, width, height, null);
        return image;
    }


}
