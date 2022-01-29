package com.initsrc.common.base;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "PageResult")
@Data
public class PageResult<T> implements Serializable {
    @ApiModelProperty(value = "总条数")
    long total;

    @ApiModelProperty(value = "列表项")
    List<T> pageList;

    @ApiModelProperty(value = "单页显示数目")
    int size;

    @ApiModelProperty(value = "总页数")
    int pages;


    public PageResult(long total, int pages, int size, List<T> pageList) {
        this.total = total;
        this.pageList = pageList;
        this.size = size;
        this.pages = pages;
    }

    public static PageResult buildPageResult(Page page) {
        return new PageResult(page.getTotal(), page.getPages(), page.getPageSize(), page.getResult());
    }

}
