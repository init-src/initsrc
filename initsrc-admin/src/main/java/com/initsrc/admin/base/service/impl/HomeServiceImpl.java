package com.initsrc.admin.base.service.impl;

import com.initsrc.admin.base.entity.home.dto.HomeInfoDto;
import com.initsrc.admin.base.entity.home.vo.HomeInfoVo;
import com.initsrc.admin.base.service.HomeService;
import com.initsrc.admin.system.dao.SysLogMapper;
import com.initsrc.admin.system.entity.log.vo.SysLogListVo;
import com.initsrc.common.base.Result;
import com.initsrc.common.enums.LogOperateTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 授权登录-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since 2021-05-21 15:25:48
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public Result<HomeInfoVo> getHomeInfo(HomeInfoDto dto) {
        List<SysLogListVo> loginList = sysLogMapper.getLogByHomeInfo(dto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Integer> map = new LinkedHashMap<>();
        List<String> timeList = getBetweenDates(dto.getBeginTime(), dto.getEndTime());
        Collections.reverse(timeList);
        for (String item : timeList) {
            map.put(item, 0);
        }
        for (SysLogListVo item : loginList) {
            String date = formatter.format(item.getCreateTime());
            map.put(date, map.get(date) + 1);
        }

        List<Integer> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }

        int loginCount = this.sysLogMapper.getCountByOprType(LogOperateTypeEnum.LOGIN.getOperateCode());
        int editCount = this.sysLogMapper.getCountByOprType(LogOperateTypeEnum.EDIT.getOperateCode());
        int addCount = this.sysLogMapper.getCountByOprType(LogOperateTypeEnum.ADD.getOperateCode());
        int delCount = this.sysLogMapper.getCountByOprType(LogOperateTypeEnum.DEL.getOperateCode());
        HomeInfoVo homeInfoVo = new HomeInfoVo();
        homeInfoVo.setLoginList(list);
        homeInfoVo.setAddCount(addCount);
        homeInfoVo.setLoginCount(loginCount);
        homeInfoVo.setDelCount(delCount);
        homeInfoVo.setEditCount(editCount);
        homeInfoVo.setTotalCount(loginCount + editCount + addCount + delCount);
        return Result.success(homeInfoVo);
    }

    public List<String> getBetweenDates(String start, String end) {

        List<String> result = new ArrayList<String>();

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date start_date = sdf.parse(start);

            Date end_date = sdf.parse(end);

            Calendar tempStart = Calendar.getInstance();

            tempStart.setTime(start_date);

            Calendar tempEnd = Calendar.getInstance();

            tempEnd.setTime(end_date);

            while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {

                result.add(sdf.format(tempStart.getTime()));

                tempStart.add(Calendar.DAY_OF_YEAR, 1);

            }

        } catch (ParseException e) {

            e.printStackTrace();

        }

        Collections.reverse(result);

        return result;

    }
}
