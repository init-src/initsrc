package com.initsrc.common.util.tree;

import java.util.List;

/**
 * 树形生成器接口
 * 作者：INITSRC (启源)
 */
public interface TreeEntityImpl<E> {
    public String getId();

    public String getParentId();

    public void setChildren(List<E> children);
}
