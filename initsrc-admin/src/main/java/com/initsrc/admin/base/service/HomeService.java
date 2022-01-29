package com.initsrc.admin.base.service;

import com.initsrc.admin.base.entity.home.dto.HomeInfoDto;
import com.initsrc.admin.base.entity.home.vo.HomeInfoVo;
import com.initsrc.common.base.Result;

/**
 * <p>
 * 授权登录-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-21 15:25:48
 */
public interface HomeService {
    //获取首页信息
    Result<HomeInfoVo> getHomeInfo(HomeInfoDto dto);
}
