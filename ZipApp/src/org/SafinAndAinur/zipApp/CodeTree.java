package org.SafinAndAinur.zipApp;

import java.util.ArrayList;
import java.util.List;

public class CodeTree {

    private final InternalNode root;

    private List<List<Integer>> codes; //коды символов, если символа нет, то null

    public CodeTree(InternalNode root, int symbolLimit) {
        if (root == null){
            throw new NullPointerException("root is null");
        }
        this.root = root;
        codes = new ArrayList<>();
        for (int i = 0; i < symbolLimit ; i++) {
            codes.add(null);
        }

    }

    private void buildCodeList(Node node, List<Integer> prefix){
        //если не лист дерева
        if (node instanceof InternalNode){

            InternalNode internalNode = (InternalNode)node;
            prefix.add(0);
            buildCodeList(internalNode.getLeftChild(),prefix);
            prefix.remove(prefix.size()-1);

            prefix.add(1);
            buildCodeList(internalNode.getRightChild(),prefix);
            prefix.remove(prefix.size()-1);

        }else {

            Leaf leaf = (Leaf)node;
            if (leaf.getSymbol() >= codes.size()){
                throw new IllegalArgumentException("символ преодолел предел ");
            }
        }
    }

    //метод, котрый возвращает код для символа
    public List<Integer> getCode(int symbol){
        if (symbol < 0){
            throw new IllegalArgumentException("символ не может быть меньше 0");
        }else if(codes.get(symbol) == null){
            throw new IllegalArgumentException("code to symbol is null");
        }
        return codes.get(symbol);
    }


    private static void toString(String prefix, Node node,StringBuilder stringBuilder){
        if(node instanceof InternalNode){

            InternalNode internalNode = (InternalNode)node;
            toString(prefix+"0",internalNode.getLeftChild(),stringBuilder);
            toString(prefix+"1",internalNode.getRightChild(),stringBuilder);

        }else if(node instanceof Leaf){
            stringBuilder.append(String.format("Code %s: Symbol %d%n",prefix,((Leaf) node).getSymbol()));
        }
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        toString("",root,stringBuilder);
        return stringBuilder.toString();
    }
}
