package com.mabo.sparsearray;

import java.io.*;

/**
 * @author MaBo
 * @create 2020-03-07 12:50
 */

public class SparseArray {

    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组 11 * 11
        //0：表示没有棋子，1 表示黑子， 2 表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[2][5] = 9;

        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转换成稀疏数组
        //1.遍历二维数组得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2. 创建相应的稀疏数组
        int[][] sparseArr1 = new int[sum + 1][3];
        int[][] sparseArr2 = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr1[0][0] = 11;
        sparseArr1[0][1] = 11;
        sparseArr1[0][2] = sum;

        //遍历二维数组，将非零的值传递到稀疏数组中
        int count = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr1[count][0] = i;
                    sparseArr1[count][1] = j;
                    sparseArr1[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }

        //打印输出稀疏数组
        System.out.println("得到的稀疏数组为");
        for (int i = 0; i < sparseArr1.length; i++) {
            for (int j = 0; j < sparseArr1[i].length; j++) {
                System.out.printf("%d\t", sparseArr1[i][j]);
            }
            System.out.println();
        }

        //将得到的稀疏数组保存到磁盘上
        File file = new File("C:\\Users\\Masama\\Desktop\\map.data");

        FileWriter out = new FileWriter(file);

        //将数组中的数据写入文件中，每行个数据用 tab 隔开
        for (int i = 0; i < sparseArr1.length; i++) {
            for (int j = 0; j < sparseArr1[i].length; j++) {
                out.write(sparseArr1[i][j] + "\t");
            }
            out.write("\n");
        }
        out.close();

        //从磁盘上读取文件，将数据恢复稀疏数组
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        int rows = 0;
        //逐行读取，将每个数据放入数组中
        while ((line = in.readLine()) != null) {
            String[] temp = line.split("\t");
            for (int j = 0; j < temp.length; j++) {
                sparseArr2[rows][j] = Integer.parseInt(temp[j]);
            }
            rows++;
        }


        //将稀疏数组 恢复成 原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr1[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        //打印输出原始的二维数组
        System.out.println("恢复的原始的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
