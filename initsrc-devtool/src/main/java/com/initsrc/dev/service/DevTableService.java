package com.initsrc.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import com.initsrc.dev.entity.table.DevTable;
import com.initsrc.dev.entity.table.dto.DevTableQueryDto;
import com.initsrc.dev.entity.table.dto.DevTableSaveDto;
import com.initsrc.dev.entity.table.vo.DevTableDetailVo;
import com.initsrc.dev.entity.table.vo.DevTableListVo;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author INITSRC
 * @since 2021-01-25
 */
public interface DevTableService extends IService<DevTable> {

    //查询数据库表列表
    List<DevTableListVo> pageData(DevTableQueryDto dto);

    //查询数据库表详情
    Result<DevTableDetailVo> detail(String id);

    //同步表的数据库信息
    Result SyncDbByTableName(String tableName);

    //预览代码生成
    Result<Map<String, String>> previewData(String id) throws Exception;

    //更新数据库表数据
    Result updateData(DevTableSaveDto dto);

    //下载代码
    byte[] downloadCode(String id) throws IOException, TemplateException;
}
