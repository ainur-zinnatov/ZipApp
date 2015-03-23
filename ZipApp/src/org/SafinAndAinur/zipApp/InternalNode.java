package org.SafinAndAinur.zipApp;

public class InternalNode extends Node {

    private final Node leftChild;

    private final Node rightChild;

    public InternalNode(Node leftChild, Node rightChild) {
        if (leftChild == null || rightChild == null){
            throw new NullPointerException("leftChild or rightChild is null");
        }
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }
}
