package com.zgr.DataStrures.notLine.tree;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 赫夫曼编码
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 11:59 2020/1/21
 */
public class HeffumanEncoded {
    public static void main(String[] args) {
        //测试解压文件

        String scrFile = "D:\\a.zip";
        String dstFile = "D:\\a.png";
        unZipFiles(scrFile, dstFile);
        System.out.println("解压文件成功！");


//        //带编码的字符串
//        String str = "i like like like java do you like a java";
//        //获得十进制的字节数组
//        byte[] bytes = str.getBytes();
//        List<Nodes> nodesList = getNodesList(bytes);
//        Nodes nodes = heffumanTree(nodesList);
//        nodes.preOrder();
//        //进行测试
////       heffumanEncod(nodes,"",stringBuilder);
//        heffumanEncod(nodes);
//        System.out.println("赫夫曼编码" + huffumanMap);
//        System.out.println("测试二进制：");
//        byte[] bytes1 = heffmanByTable(bytes, huffumanMap);

        //编码
//        byte[] bytes1 = heffmanZip(bytes);
//        System.out.println(Arrays.toString(bytes1));
//        //解码
//        byte[] decode = decode(bytes1, huffumanMap);
//        System.out.println("解压后的数据" + new String(decode));

        //测试压缩文件

//        String scrFile="D:\\src.bmp";
//        String dstFile="D:\\src.zip";
//        zipFilter(scrFile,dstFile);
//        System.out.println("压缩文件成功");


    }

    static Map<Byte, String> huffumanMap = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    //通过流编码和解码

    /**
     * @param scrFile 读取的文件
     * @param dstFile 写入文件
     */
    public static void zipFilter(String scrFile, String dstFile) {
        //输出流
        OutputStream os = null;
        FileInputStream is = null;
        ObjectOutputStream oos = null;


        try {
            //读取文件内容
            is = new FileInputStream(scrFile);
            //创建字节数组
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            //压缩赫夫曼编码
            byte[] heffmanBytes = heffmanZip(bytes);
            //写入赫夫曼编码
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(heffmanBytes);
            //写入赫夫曼编码
            oos.writeObject(huffumanMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                is.close();
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void unZipFile(String srcFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            //读取文件
            is = new FileInputStream(srcFile);
            //创建去读对象流
            ois = new ObjectInputStream(is);
            //读取对象压缩的字节数组
            byte[] hefumanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> map = (Map<Byte, String>) ois.readObject();
            //然后就是解压
            byte[] decode = decode(hefumanBytes, map);
            //将得到的字节数组写入到磁盘
            os = new FileOutputStream(dstFile);
            os.write(decode);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void unZipFiles(String zipFile, String dstFile) {


        InputStream is = null;

        ObjectInputStream ois = null;

        OutputStream os = null;
        try {

            is = new FileInputStream(zipFile);

            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();

            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();


            byte[] bytes = decode(huffmanBytes, huffmanCodes);

            os = new FileOutputStream(dstFile);

            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }
        }
    }

    /**
     * @param bytes        编码后的字节数组
     * @param heffmanCodes 赫夫曼编码表
     * @return 原来字符串的二进制字符串
     */
    private static byte[] decode(byte[] bytes, Map<Byte, String> heffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            //获取字节
            byte b = bytes[i];
            //是否补
            boolean flag = (i == bytes.length - 1);
            stringBuilder.append(byteTo(!flag, b));

        }
//        System.out.println("解码二进制数组："+stringBuilder.toString());
        //得到这个数组之后就利用赫夫曼表进行映射存储,
        Map<String, Byte> decodeMap = new HashMap<>();
        for (Map.Entry<Byte, String> map : huffumanMap.entrySet()) {
            decodeMap.put(map.getValue(), map.getKey());
        }
        //集合存放byte
        List<Byte> list = new ArrayList<>();
        //通过扫描stringbuilder，i就是下标
        //去掉i++,不然会少扫描 i+=count，再++的话就会出错
        for (int i = 0; i < stringBuilder.length(); ) {
            //不断查询
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //i不动，通过count来控制移动
                String key = stringBuilder.substring(i, i + count);
                b = decodeMap.get(key);
                if (b == null) {//没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }

    /**
     * 完成对数据的解压
     * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 1.先转成赫夫曼编码对应的二进制的字符串
     * 2.对照赫父曼表
     *
     * @param flag true，需要补高位，最后一个字节无需补高位
     * @param b
     * @return b对应的字符串 补码返回
     */
    private static String byteTo(boolean flag, byte b) {
        int temp = b;
        //如果是正数，就存在补高位
        if (flag) {
            temp |= 256; //按位与  256是 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String s = Integer.toBinaryString(temp);//返回的是二进制的补码
        //截取最后的8位
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }


    /**
     * 赫夫曼编码
     *
     * @param bytes 原始的数据
     * @return 压缩后的数据
     */
    private static byte[] heffmanZip(byte[] bytes) {
        List<Nodes> nodesList = getNodesList(bytes);
        Nodes nodes = heffumanTree(nodesList);
        Map<Byte, String> byteStringMap = heffumanEncod(nodes);
        byte[] bytes1 = heffmanByTable(bytes, byteStringMap);
        return bytes1;
    }

    //接受字符串数组转出的byte数组
    //利用赫夫曼编码huffumanMap表进行压缩成byte数组
    public static byte[] heffmanByTable(byte[] bytes, Map<Byte, String> huffumanMap) {
        //首先需要先转成二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte obj : bytes) {
            stringBuilder.append(huffumanMap.get(obj));
        }
        //得到之后将其每8位放入
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] heffmanbByte = new byte[len];
        int index = 0;//控制byte下标
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            heffmanbByte[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return heffmanbByte;
    }

    //重载赫夫曼编码
    public static Map<Byte, String> heffumanEncod(Nodes nodes) {
        if (nodes == null) {
            return null;
        } else {
            heffumanEncod(nodes.left, "0", stringBuilder);
            heffumanEncod(nodes.right, "1", stringBuilder);
        }
        return huffumanMap;
    }
    //将赫夫曼树转变成赫夫曼编码

    /**
     * @param nodes         赫夫曼根
     * @param code          路径
     * @param stringBuilder 字符串拼接
     */
    public static void heffumanEncod(Nodes nodes, String code, StringBuilder stringBuilder) {

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //拼接
        stringBuilder2.append(code);
        //递归编码
        if (nodes != null) {
            //继续
            //判断是否到最后,data是非叶子节点才有的
            if (nodes.data == null) {
                //没有到头
                //左递归
                heffumanEncod(nodes.left, "0", stringBuilder2);
                //右递归
                heffumanEncod(nodes.right, "1", stringBuilder2);
            } else {
                huffumanMap.put(nodes.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * @param bytes 接受的字节数组
     * @return 统计好的List
     */
    public static List<Nodes> getNodesList(byte[] bytes) {
        List<Nodes> list = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        //统计了每个byte出现的次数
        for (byte obj : bytes) {
            Integer counts = map.get(obj);
            if (counts == null) {
                map.put(obj, 1);
            } else {
                map.put(obj, counts + 1);
            }
        }
        //遍历map得出list集合
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Nodes(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    //遍历转变成赫夫曼树
    public static Nodes heffumanTree(List<Nodes> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            //取出最小的两个数
            Nodes leftNodes = list.get(0);
            Nodes rightNodes = list.get(1);
            //创建父节点
            Nodes parent = new Nodes(null, leftNodes.weight + rightNodes.weight);
            parent.left = leftNodes;
            parent.right = rightNodes;
            list.remove(leftNodes);
            list.remove(rightNodes);
            list.add(parent);

        }
        return list.get(0);
    }

    //前序
    public static void preOrders(Nodes root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树！");
        }

    }
}

//创建Node,数据和权值
class
Nodes implements Comparable<Nodes> {
    Byte data;
    int weight;
    Nodes left;
    Nodes right;

    public Nodes(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Nodes o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
