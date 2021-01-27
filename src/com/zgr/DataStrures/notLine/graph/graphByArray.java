package com.zgr.DataStrures.notLine.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图结构
 *
 * @Author: aidawone
 * @Description:
 * @Date: Create in 19:04 2020/1/22
 */
public class graphByArray {

    private List<String> list = null;
    private int[][] edges;//存储图对应的领节矩阵
    private int numofEdges;//表示边的数目

    public static void main(String[] args) {
        System.out.println("测试");
        int n = 5;
        String vertex[] = {"A", "B", "C", "D", "E"};
        graphByArray graph = new graphByArray(n);
        for (String obj : vertex) {
            graph.insertVertex(obj);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);
        graph.show();

    }

    public graphByArray(int n) {
        list = new ArrayList<>();
        edges = new int[n][n];
        numofEdges = 0;
    }

    //显示图
    public void show() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));

        }
    }

    //插入顶点
    public void insertVertex(String vertex) {
        list.add(vertex);
    }

    //返回节点的个数
    public int getNumofVertex() {
        return list.size();
    }

    //返回边的数目
    public int getNumberofEdges() {
        return numofEdges;
    }

    //返回下标v1和v2的值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //返回节点i下标对应的数据 0->A, 1->B 2->C
    public String getValueByIndex(int index) {
        return list.get(index);
    }
    //添加边

    /**
     * @param v1     表示第一个顶点的下标
     * @param v2     表示第二个顶点的下标
     * @param weight 边的值，1或0
     */
    public void addEdges(int v1, int v2, int weight) {
        //无向图
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;

    }
}
