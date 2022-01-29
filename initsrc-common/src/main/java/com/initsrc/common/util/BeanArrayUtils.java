package com.initsrc.common.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 复制List对象
 * 作者：INITSRC (启源)
 */
public class BeanArrayUtils extends BeanUtil {

    /**
     * list复制
     *
     * @param sources
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toBeanList(List sources, Class<T> clazz) {
        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<T>();
        }
        List<T> list = new ArrayList<>();
        for (Object source : sources) {
            T bean = toBean(source, clazz, null);
            list.add(bean);
        }
        return list;
    }

}
