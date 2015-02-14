package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

public interface Tree<D , N extends Tree.TreeNode<D , N>> extends Cleanable {

    public interface TreeNode<DATA , N extends TreeNode<DATA , N>> {
        public DATA data();

        public void data(DATA data);

        public N left();

        public void left(N left);

        public N right();

        public void right(N right);
    }

    public static <DATA , N extends Tree.TreeNode<DATA , N>> boolean bitree_is_eob(N node) {
        return node == null;
    }

    public static <DATA , N extends Tree.TreeNode<DATA , N>> boolean bitree_is_leaf(N node) {
        return node.left() == null && node.right() == null;
    }

    public static
        <DATA , N extends Tree.TreeNode<DATA , N> , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Linked<DATA , E> inorder(final N node , final T linked) {
        //Load the list with an inorder listing of the tree.
        if (!Tree.bitree_is_eob(node)) {
            if (!Tree.bitree_is_eob(node.left())) Tree.inorder(node.left() , linked);
            linked.list_ins_next(linked.tail() , node.data());
            if (!Tree.bitree_is_eob(node.right())) Tree.inorder(node.right() , linked);
        }

        return linked;
    }

    public static
        <DATA , N extends Tree.TreeNode<DATA , N> , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Linked<DATA , E> postorder(final N node , final T linked) {
        //Load the list with an inorder listing of the tree.
        if (!Tree.bitree_is_eob(node)) {
            if (!Tree.bitree_is_eob(node.left())) Tree.postorder(node.left() , linked);
            if (!Tree.bitree_is_eob(node.right())) Tree.postorder(node.right() , linked);
            linked.list_ins_next(linked.tail() , node.data());
        }

        return linked;
    }

    public static
        <DATA , N extends Tree.TreeNode<DATA , N> , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Linked<DATA , E> preorder(final N node , final T linked) {
        //Load the list with a preorder listing of the tree.
        if (!Tree.bitree_is_eob(node)) {
            linked.list_ins_next(linked.tail() , node.data());
            if (!Tree.bitree_is_eob(node.left())) Tree.preorder(node.left() , linked);
            if (!Tree.bitree_is_eob(node.right())) Tree.preorder(node.right() , linked);
        }

        return linked;
    }

    @Override
    public abstract void clean();

    public abstract N root();

    public abstract int size();
}