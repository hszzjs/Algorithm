package com.company;

import java.util.*;

/**
 * Created by Administrator on 2018/10/7 0007.
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {
    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        private int N;
        private boolean color;//父链接的颜色

        public Node(Key key,Value val,boolean color,int N){
            this.key=key;
            this.value=val;
            this.color=color;
            this.N=N;
        }
    }

    public RedBlackBST(){
    }

    private boolean isRed(Node x){
        if(x==null) return false;
        return x.color==RED;
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x==null) return 0;
        return x.N;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public Value get(Key key){
        if(key==null) throw new NullPointerException("argument to get() is null");
        return get(root,key);
    }

    private Value get(Node x,Key key){
        while (x!=null){
            int cmp=key.compareTo(x.key);
            if(cmp<0) x=x.left;
            else if(cmp>0) x=x.right;
            else return x.value;
        }
        return null;
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    public void put(Key key,Value value){
        if(key==null) throw new NullPointerException("first argument to put is null");
        if(value==null){
            delete(key);
            return;
        }
        root=put(root,key,value);
        root.color=BLACK;
    }

    private Node put(Node x,Key key,Value value){
        if(x==null) return new Node(key,value,RED,1);
        int cmp=key.compareTo(x.key);
        if(cmp<0) x.left=put(x.left,key,value);
        else if(cmp>0) x.right=put(x.right,key,value);
        else x.value=value;

        if(isRed(x.right) && !isRed(x.left)) x=rotateLeft(x);
        if(isRed(x.left) && isRed(x.left.left)) x=rotateRight(x);
        if(isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N=size(x.left)+size(x.right)+1;

        return x;
    }

    private Node rotateRight(Node x){
        Node t=x.left;
        x.left=t.right;
        t.right=x;
        t.color=t.right.color;
        t.right.color=RED;
        t.N=x.N;
        x.N=size(x.left)+size(x.right)+1;
        return t;
    }

    private Node rotateLeft(Node x){
        Node t=x.right;
        x.right=t.left;
        t.left=x;
        t.color=t.left.color;
        t.left.color=RED;
        t.N=x.N;
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    private void flipColors(Node x){
        x.color=!x.color;
        x.left.color=!x.left.color;
        x.right.color=!x.right.color;
    }

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException("BST underflow");
        if(!isRed(root.left) && isRed(root.right))
            root.color=RED;

        root=deleteMin(root);
        if(!isEmpty()) root.color=BLACK;
    }

    private Node deleteMin(Node x){
        if(x.left==null) return null;
        if(!isRed(x.left) && !isRed(x.left.left)) x=moveRedLeft(x);

        x.left=deleteMin(x.left);
        return balance(x);
    }

    private Node moveRedLeft(Node x){
        flipColors(x);
        if(isRed(x.right.left)){
            x.right=rotateRight(x.right);
            x=rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node balance(Node x){
        if(isRed(x.right)) x=rotateLeft(x);
        if(isRed(x.left)&& isRed(x.left.left)) x=rotateRight(x);
        if(isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N=size(x.right)+size(x.left)+1;
        return x;
    }

    public void deleteMax(){
        if(isEmpty()) throw new NoSuchElementException("BST underflow");
        if(!isRed(root.left) && !isRed(root.right)) root.color=RED;
        root=deleteMax(root);
        if(!isEmpty()) root.color=BLACK;
    }

    private Node deleteMax(Node x){
        if(isRed(x.left)) x=rotateRight(x);
        if(x.right==null) return null;
        if(!isRed(x.right) && !isRed(x.right.left)) x=moveRedRight(x);

        x.right=deleteMax(x.right);
        return balance(x);
    }

    private Node moveRedRight(Node x){
        flipColors(x);
        if(isRed(x.left.left)){
            x=rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    public void delete(Key key){
        if(key==null)throw new NullPointerException("argument to delete() is null");
        if(!contains(key)) return;

        if(!isRed(root.left) && !isRed(root.right)) root.color=RED;
        root=delete(root,key);
        if(isEmpty()) root.color=BLACK;
    }

    private Node delete(Node x,Key key){
        if(key.compareTo(x.key)<0){
            if(!isRed(x.left) && !isRed(x.left.left))
                x=moveRedLeft(x);
            x.left=delete(x.left,key);
        }else {
            if(isRed(x.left)) x=rotateRight(x);
            if(key.compareTo(x.key)==0 && (x.right==null)) return null;
            if(!isRed(x.right) && !isRed(x.right.left)) x=moveRedRight(x);
            if(key.compareTo(x.key)==0){
                Node t=min(x.right);
                x.key=t.key;
                x.value=t.value;
                x.right=deleteMin(x.right);
            }
            else x.right=delete(x.right,key);
        }
        return balance(x);
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left==null) return x;
        else return min(x.left);
    }

    public Key max(){
        if(isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right==null) return x;
        else return max(x.right);
    }

    public int height(){
        return height(root);
    }

    private int height(Node x){
        if(x==null) return -1;
        return 1+Math.max(height(x.left),height(x.right));
    }

    public Key floor(Key key){
        if(key==null) throw new NullPointerException("argument to floor() is null");
        if(isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x=floor(root,key);
        if(x==null) return null;
        else return x.key;
    }

    private Node floor(Node x,Key key){
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0) return x;
        if(cmp<0) return floor(x.left,key);
        Node t=floor(x.right,key);
        if(t!=null) return t;
        else return x;
    }

    public Key ceiling(Key key){
        if(key==null) throw new NullPointerException("argument to ceiling() is null");
        if(isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x=ceiling(root,key);
        if(x==null) return null;
        else return x.key;
    }

    private Node ceiling(Node x,Key key){
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0) return x;
        if(cmp>0) return ceiling(x.right,key);
        Node t=ceiling(x.left,key);
        if(t!=null) return t;
        else return x;
    }

    public Key select(int k){
        if(k<0||k>=size()) throw new IllegalArgumentException();
        Node x=select(root,k);
        return x.key;
    }

    private Node select(Node x,int k){
        int t=size(x.left);
        if(t>k) return select(x.left,k);
        else if(t<k) return select(x.right,k-t-1);
        else return x;
    }

    public int rank(Key key){
        if(key==null) throw new NullPointerException("argument to rank() is null");
        return rank(key,root);
    }

    private int rank(Key key,Node x){
        if(x==null) return 0;
        int cmp=key.compareTo(x.key);
        if(cmp<0) return rank(key,x.left);
        else if(cmp>0) return 1+size(x.left)+rank(key,x.right);
        else return size(x.left);
    }

    public Iterable<Key> keys(){
        if(isEmpty()) return new Queue<Key>();
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo,Key hi){
        if(lo==null) throw new NullPointerException("first argument to keys() is null");
        if(hi==null) throw new NullPointerException("second argument to keys() is null");
        Queue<Key> queue=new Queue<Key>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node x,Queue<Key> queue,Key lo,Key hi){
        if(x==null) return;
        int cmplo=lo.compareTo(x.key);
        int cmphi=hi.compareTo(x.key);
        if(cmplo<0) keys(x.left,queue,lo,hi);
        if(cmphi>=0 && cmplo<=0) queue.enqueue(x.key);
        if(cmphi>0) keys(x.right,queue,lo,hi);
    }

    public int size(Key lo,Key hi){
        if(lo==null) throw new NullPointerException("first argument to keys() is null");
        if(hi==null) throw new NullPointerException("second argument to keys() is null");
        if(lo.compareTo(hi)>0) return 0;
        if(contains(hi)) return rank(hi)-rank(lo)+1;
        else return rank(hi)-rank(lo);
    }

    @SuppressWarnings("unused")
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        if (!is23())             StdOut.println("Not a 2-3 tree");
        if (!isBalanced())       StdOut.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }
    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }
    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }
    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }
    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }
    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }
    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }
}
