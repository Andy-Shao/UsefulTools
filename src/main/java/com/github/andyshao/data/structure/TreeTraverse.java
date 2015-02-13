package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Bitree.BitreeNode;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 13, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class TreeTraverse {
    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Linked<DATA , E> inorder(final BitreeNode<DATA> node , final T linked) {
        //Load the list with an inorder listing of the tree.
        if (!Bitree.bitree_is_eob(node)) {
            if (!Bitree.bitree_is_eob(node.left())) TreeTraverse.inorder(node.left() , linked);
            linked.list_ins_next(linked.tail() , node.data());
            if (!Bitree.bitree_is_eob(node.right())) TreeTraverse.inorder(node.right() , linked);
        }

        return linked;
    }

    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Linked<DATA , E> postorder(final BitreeNode<DATA> node , final T linked) {
        //Load the list with an inorder listing of the tree.
        if (!Bitree.bitree_is_eob(node)) {
            if (!Bitree.bitree_is_eob(node.left())) TreeTraverse.postorder(node.left() , linked);
            if (!Bitree.bitree_is_eob(node.right())) TreeTraverse.postorder(node.right() , linked);
            linked.list_ins_next(linked.tail() , node.data());
        }

        return linked;
    }

    public static
        <DATA , E extends Linked.LinkedElmt<DATA , E> , T extends Linked<DATA , E> & SingleLinkedOperation<DATA , E>>
        Linked<DATA , E> preorder(final BitreeNode<DATA> node , final T linked) {
        //Load the list with a preorder listing of the tree.
        if (!Bitree.bitree_is_eob(node)) {
            linked.list_ins_next(linked.tail() , node.data());
            if (!Bitree.bitree_is_eob(node.left())) TreeTraverse.preorder(node.left() , linked);
            if (!Bitree.bitree_is_eob(node.right())) TreeTraverse.preorder(node.right() , linked);
        }

        return linked;
    }

    private TreeTraverse() {
        throw new AssertionError("No com.github.andyshao.data.structure.TreeTraverse instances for you!");
    }
}
