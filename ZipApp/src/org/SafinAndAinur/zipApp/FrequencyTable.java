package org.SafinAndAinur.zipApp;

import java.util.PriorityQueue;
import java.util.Queue;

public class FrequencyTable {

    private int frequencies[];

    public FrequencyTable(int[] frequencies) {

        if (frequencies == null){
            throw new NullPointerException("frequencies is null");
        }else if (frequencies.length < 2){
            throw new IllegalArgumentException("должно быть по крайней мере 2 символа");
        }
        for (int val : frequencies){
            if (val < 0){
                throw new IllegalArgumentException("частота встречаемости символа не может быть отрицательной");
            }
        }
        this.frequencies = frequencies.clone();
    }

    //предел кол-ва символов
    public int getSymbolLimit(){
        return frequencies.length;
    }

    public int get(int symbol){
        if (symbol < 0 || symbol >= frequencies.length){
            throw new IllegalArgumentException("символ за пределами границ массива");
        }
        return frequencies[symbol];
    }

    //устанавливаем для символа кол-во встречаемостей
    public void set(int symbol, int freq){
        if (symbol < 0 || symbol >= frequencies.length){
            throw new IllegalArgumentException("символ за пределами границ массива");
        }
        frequencies[symbol] = freq;
    }

    //увеличиваем кол-во встречаемостей символа
    public void increment(int symbol){
        if (symbol < 0 || symbol >= frequencies.length){
            throw new IllegalArgumentException("символ за пределами границ массива");
        }
        frequencies[symbol]++;
    }

    public CodeTree buildCodeTree(){

        Queue<NodeWithFrequency> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < frequencies.length ; i++) {
            if (frequencies[i] > 0){
                priorityQueue.add(new NodeWithFrequency(new Leaf(i),i,frequencies[i]));
            }
        }

        for (int i = 0; i < frequencies.length && priorityQueue.size() < 2; i++) {
            if (i >= frequencies.length || frequencies[i] == 0){
                priorityQueue.add(new NodeWithFrequency(new Leaf(i),i,0));
            }
        }
        if(priorityQueue.size() < 2){
            throw new AssertionError();
        }

        //связываем два узла с наименьшими частотами встречаемости символов
        while (priorityQueue.size() > 1){
            NodeWithFrequency nodeWithFrequency1 = priorityQueue.remove();
            NodeWithFrequency nodeWithFrequency2 = priorityQueue.remove();

            priorityQueue.add(new NodeWithFrequency(new InternalNode(nodeWithFrequency1.node,nodeWithFrequency2.node),
                    Math.min(nodeWithFrequency1.lowestSymbol,nodeWithFrequency2.lowestSymbol),
                    nodeWithFrequency1.frequency+nodeWithFrequency2.frequency));
        }

        return new CodeTree((InternalNode)priorityQueue.remove().node,frequencies.length);
    }


    private class NodeWithFrequency implements Comparable<NodeWithFrequency>{

        private final Node node;

        private final int lowestSymbol;

        private final int frequency;

        public NodeWithFrequency(Node node,int lowestSymbol, int frequency) {
            this.lowestSymbol = lowestSymbol;
            this.node = node;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(NodeWithFrequency o) {
            if (frequency < o.frequency){
                return -1;
            }else if (frequency > o.frequency){
                return 1;
            }else if (lowestSymbol < o.lowestSymbol){
                return -1;
            }else if (lowestSymbol > o.lowestSymbol){
                return 1;
            }
            return 0;
        }
    }


}
