package com.initsrc.dev.controller;

import com.initsrc.common.base.Result;
import com.initsrc.dev.service.SystemHardwareInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务监控-前端控制器
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-27 11:31:29
 */

@RestController
@RequestMapping("/devApi/monitor")
@Api(tags = "服务监控模块")
public class MonitorController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 系统监控
     *
     * @param
     * @return Result
     */
    @GetMapping("/serverDetail")
    @ApiOperation(value = "系统监控", notes = "系统监控")
    @RequiresPermissions("p:dev:monitor:page")
    public Result<SystemHardwareInfo> serverDetail() throws Exception {
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.copyTo();
        return Result.success(systemHardwareInfo);
    }

    /**
     * 缓存监控
     *
     * @param
     * @return Result
     */
    @GetMapping("/cacheDetail")
    @ApiOperation(value = "缓存监控", notes = "缓存监控")
    @RequiresPermissions("p:dev:monitor:cache")
    public Result<Map<String,Object>> cacheDetail() throws Exception {
        Map<String, Object> result = new HashMap<>(3);
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
        result.put("info", info);
        result.put("dbSize", dbSize);

        Map<String, Object> pieList = new HashMap<>(2);
        List<Integer> data = new ArrayList<>();
        List<String> label = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            String property = commandStats.getProperty(key);
            label.add(StringUtils.removeStart(key, "cmdstat_"));
            data.add(Integer.valueOf(StringUtils.substringBetween(property, "calls=", ",usec")));
        });
        pieList.put("label",label);
        pieList.put("data",data);
        result.put("commandStats", pieList);
        return Result.success(result);
    }


}
