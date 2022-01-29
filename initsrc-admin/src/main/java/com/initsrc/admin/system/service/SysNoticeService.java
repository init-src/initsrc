package com.initsrc.admin.system.service;

import com.initsrc.admin.system.entity.notice.SysNotice;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeListVo;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeDetailVo;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeQueryDto;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeSaveDto;
import javax.servlet.http.HttpServletResponse;
import com.initsrc.common.base.BaseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;

/**
 * <p>
 * 通知公告-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-06-07 17:02:27
 */
public interface SysNoticeService extends IService<SysNotice>{

    //查询通知公告列表
    List<SysNoticeListVo> pageData(SysNoticeQueryDto dto);

    //导出通知公告数据
    void export(HttpServletResponse response, SysNoticeQueryDto dto);

    //通知公告导入
    Result<String> batchImport(MultipartFile file, BaseEntity dto, HttpServletResponse response) throws Exception;

    //查询通知公告详情
    Result<SysNoticeDetailVo> detail(String id);

    //保存通知公告数据
    Result saveData(SysNoticeSaveDto dto);

    //更新通知公告数据
    Result updateData(SysNoticeSaveDto dto);

    //删除通知公告数据
    Result deleteData(String id);

    // 批量删除通知公告数据
    Result deletesData(List<String> ids);

}
