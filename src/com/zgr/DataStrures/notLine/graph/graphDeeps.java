package com.zgr.DataStrures.notLine.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: aidawone
 * @Description:
 * @Date: Create in 20:47 2020/1/22
 */
public class graphDeeps {

    private List<String> list = null;
    private int[][] edges;//�洢ͼ��Ӧ����ھ���
    private int numofEdges;//��ʾ�ߵ���Ŀ

    //TODO ���
    public boolean[] isVisited;//��¼�Ƿ����

    public static void main(String[] args) {
        System.out.println("����");
        int n = 5;
        String vertex[] = {"A", "B", "C", "D", "E"};
        graphDeeps graph = new graphDeeps(n);
        for (String obj : vertex) {
            graph.insertVertex(obj);
        }
        //��ӱ�
        //A-B A-C B-C B-D B-E
        graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);
        graph.show();
        System.out.println("��ȱ���");
        graph.dfs();
    }

    public graphDeeps(int n) {
        list = new ArrayList<>();
        edges = new int[n][n];
        numofEdges = 0;
        //TODO ���
        isVisited = new boolean[5];
    }

    //todo ���
    private void bfs(boolean[] isVisited, int i) {
        int u; //��ʾ���е�ͷ�ڵ��Ӧ�±�
        int w; //�ڽӽڵ�
        //����
        LinkedList queue = new LinkedList();
        //���ʽڵ㣬���
        System.out.println(getValueByIndex(i) + " =>");
        //���óɷ���
        isVisited[i] = true;
        //���ѷ��ʵļ������
        queue.addLast(i);


    }


    //todo ���

    /**
     * �õ���һ���ڽӽڵ���±�
     *
     * @param index �����Ľڵ��±�
     * @return
     */
    public int getFirstNeighBar(int index) {
        for (int i = 0; i < list.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //todo ���
    //������һ���ڽ��������һ��
    public int getNextNeighBar(int v1, int v2) {
        //+1����Ϊ��һ��
        for (int j = v2 + 1; j < list.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;

            }
        }
        return -1;
    }
    //todo ��� dfs

    /**
     * @param isVisited �Ƿ񱻷���
     * @param i         ���ʵ��±� ��һ����0
     */
    public void dfs(boolean[] isVisited, int i) {
        //�������Ƿ��ʸýڵ�
        System.out.println(getValueByIndex(i) + " -> ");
        //���ڵ����ó��Ѿ����ʹ�
        isVisited[i] = true;//����ķ��ʾ�������ڵ��Ѿ����,��������ͼҲ��
        //���ҽڵ�i�ĵ�һ���ڽӽڵ�w
        int w = getFirstNeighBar(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //w���ڣ����Ѿ������ʹ�
            w = getNextNeighBar(i, w);
        }
    }

    //todo ���
    //��dfs�������أ��������еĽڵ㣬������dfs
    public void dfs() {
        for (int i = 0; i < getNumofVertex(); i++) {
            //û�����ʹ�
            if (!isVisited[i]) {

                dfs(isVisited, i);
            }
        }
    }

    //��ʾͼ ���
    public void show() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));

        }
    }

    //���붥��
    public void insertVertex(String vertex) {
        list.add(vertex);
    }

    //���ؽڵ�ĸ���
    public int getNumofVertex() {
        return list.size();
    }

    //���رߵ���Ŀ
    public int getNumberofEdges() {
        return numofEdges;
    }

    //�����±�v1��v2��ֵ
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //���ؽڵ�i�±��Ӧ������ 0->A, 1->B 2->C
    public String getValueByIndex(int index) {
        return list.get(index);
    }
    //��ӱ�

    /**
     * @param v1     ��ʾ��һ��������±�
     * @param v2     ��ʾ�ڶ���������±�
     * @param weight �ߵ�ֵ��1��0
     */
    public void addEdges(int v1, int v2, int weight) {
        //����ͼ
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;

    }
}
