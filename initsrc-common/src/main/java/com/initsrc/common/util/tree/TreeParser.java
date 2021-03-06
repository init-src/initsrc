package com.initsrc.common.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形转换实现
 * 作者：INITSRC (启源)
 */
public class TreeParser {
    /**
     * * 解析树形数据
     *
     * @param topId
     * @param entityList
     * @return
     */
    public static <E extends TreeEntityImpl<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        // 获取顶层元素集合
        String parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        // 获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    /**
     * * 获取子数据集合
     *
     * @param id
     * @param entityList
     * @return
     */
    private static <E extends TreeEntityImpl<E>> List<E> getSubList(String id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        String parentId;
        // 子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        // 子集的间接子对象
        for (E entity : childList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
