package com.initsrc.common.base;

import com.initsrc.common.util.tree.TreeEntityImpl;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：列表树型
 * 作者：INITSRC
 */
@Data
public class NodeEntity extends BaseEntity implements TreeEntityImpl<NodeEntity>, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;
    @ApiModelProperty(value = "父类")
    private String parentId;
    @ApiModelProperty(value = "名称")
    private String label;
    @ApiModelProperty(value = "子节点")
    private List<NodeEntity> children;
}
