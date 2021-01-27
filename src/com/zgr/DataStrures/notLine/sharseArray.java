package com.zgr.DataStrures.notLine;

import java.io.*;
import java.lang.reflect.Array;

/**
 * 稀疏数组演化棋盘
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 22:20 2020/1/11
 */
public class sharseArray {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\array.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

        //0:无棋子  1:黑子 2：白子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[1][3] = 2;
        System.out.println("原始的二位数组:");
        for (int[] row : chessArray) {
            for (int col : row) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }
        //遍历二维数组
        //取得稀疏数组的sum
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        //创建稀疏数组
        int shareArray[][] = new int[sum + 1][3];
        //赋值
        shareArray[0][0] = chessArray.length;
        shareArray[0][1] = chessArray[0].length;
        shareArray[0][2] = sum;
        //遍历赋值
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    shareArray[count][0] = i;
                    shareArray[count][1] = j;
                    shareArray[count][2] = chessArray[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("稀疏数组:");
        for (int i = 0; i < shareArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", shareArray[i][0], shareArray[i][1], shareArray[i][2]);
        }
        FileWriter writer = null;


        writer = new FileWriter("E:\\Save1.txt");
        for (int i = 0; i < shareArray.length; i++) {
            for (int j = 0; j < 3; j++) {
                writer.write(shareArray[i][j]);
            }
//				writer.write("\n");
//				写入的时候不需要换行！！我在这里摔倒了就不希望有人再在同一个地方摔倒。
//				如果你发现写入和读取的数字不对，第一件事情请看看你有没有把换行符之类的也写入了
//				导致reader把你的换行符也读取了。
        }

        writer.flush();


        //读取稀疏数组-->转换成二维数组
        System.out.println("取出文件的值：");
        FileReader reader = null;
        int shareArray2[][] = new int[sum + 1][3];
        int getNum = 0;
        reader = new FileReader("E:\\Save1.txt");
        for (int i = 0; i < shareArray2.length; i++) {
            for (int j = 0; j < 3; j++) {
                getNum = reader.read();
                shareArray2[i][j] = getNum;
            }
        }

        System.out.println();
        System.out.println("读取后稀疏数组为");
        for (int i = 0; i < shareArray2.length; i++) {
            System.out.printf("%d\t%d\t%d\n", shareArray2[i][0], shareArray2[i][1], shareArray2[i][2]);
            //格式化输出
        }
        System.out.println();

        int chessArray2[][] = new int[shareArray2[0][0]][shareArray2[0][1]];
        for (int i = 1; i < shareArray2.length; i++) {
            chessArray2[shareArray2[i][0]][shareArray2[i][1]] = shareArray2[i][2];

        }
        System.out.println();
        System.out.println("恢复的稀疏数组:");
        for (int[] row : chessArray2) {
            for (int col : row) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }


    }
}
