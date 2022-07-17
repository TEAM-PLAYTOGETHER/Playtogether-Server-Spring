package com.deploy.playtogether.config;

import com.deploy.playtogether.common.util.EnumMapper;
import com.deploy.playtogether.domain.light.LightCategory;
import com.deploy.playtogether.domain.user.UserSocialType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnumMapperConfig {
    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();

        //user
        enumMapper.put("UserSocialType", UserSocialType.class);

        //light
        enumMapper.put("LightCategory", LightCategory.class);
        return enumMapper;
    }
}
