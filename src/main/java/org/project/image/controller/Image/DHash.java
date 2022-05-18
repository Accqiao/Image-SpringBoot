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
        int ImgWidth = 9;
        int ImgHeight = 8;
        int index = 0;
        //改变尺寸
        BufferedImage image = this.newSize(src);
        //保存简化色彩的信息
        int[] ints = new int[ImgWidth * ImgHeight];
        for (int i = 0; i < ImgHeight; i++) {
            for (int j = 0; j < ImgWidth; j++) {
                ints[index++] = this.simplifyColor(image.getRGB(j, i));
            }
        }

        StringBuilder hashBuilder = new StringBuilder();
        for (int i = 0;i < ImgHeight;i++){
            for (int j = 0;j < ImgWidth - 1;j++){
                if (ints[9 * j + i] < ints[9 * j + i + 1]){
                    hashBuilder.append(0);
                }else {
                    hashBuilder.append(1);
                }
            }
        }
        return hashBuilder.toString();
    }

    /**
     * 简化色彩
     * @param rgb
     * @return
     */
    private int simplifyColor(int rgb) {
        //低位分量 (蓝色) 0-7
        int colorBlue = rgb & 0xff;
        //中位分量 (绿色) 5-15
        int colorGreen = (rgb >> 8) & 0xff;
        //高位分量 (红色) 16-23
        int colorRed = (rgb >> 16) & 0xff;
        //最高位分量 灰色（24-31）的信息
        int colorGray = rgb & 0xff000000;
        rgb = (colorRed * 77 + colorGreen * 151 + colorBlue * 28) >> 8;

        return colorGray | (rgb << 16) | (rgb << 8) | rgb;
    }


    private BufferedImage newSize(BufferedImage src ) {
        //将图片缩小到9*8像素点的统一格式，方面计算
        BufferedImage buffImg = new BufferedImage(9, 8, BufferedImage.TYPE_INT_BGR);
        Graphics graph = buffImg.createGraphics();
        graph.drawImage(src, 0, 0, 9, 8, null);
        return buffImg;
    }


}
