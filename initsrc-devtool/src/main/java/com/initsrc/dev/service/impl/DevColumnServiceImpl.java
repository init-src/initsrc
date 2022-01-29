package com.initsrc.dev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.initsrc.dev.dao.DevColumnMapper;
import com.initsrc.dev.entity.column.DevColumn;
import com.initsrc.dev.service.DevColumnService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author INITSRC
 * @since 2021-01-25
 */
@Service
public class DevColumnServiceImpl extends ServiceImpl<DevColumnMapper, DevColumn> implements DevColumnService {
}
