package com.initsrc.core.module.mybaitsplus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by initsrc on 2020/02/26
 */
@Configuration
public class MybatisPlusConfig {


    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.addInterceptor(new com.github.pagehelper.PageInterceptor());
            }
        };
    }

    /**
     * 乐观锁插件
     * {@link https://mp.baomidou.com/guide/optimistic-locker-plugin.html}
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
