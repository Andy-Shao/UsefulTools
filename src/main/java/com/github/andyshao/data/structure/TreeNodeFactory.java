package com.github.andyshao.data.structure;

import com.github.andyshao.data.structure.Bitree.BitreeNode;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 14, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 * @param <T> tree node type
 */
@FunctionalInterface
public interface TreeNodeFactory<D , T extends BitreeNode<D>> {

    public T build();
}
