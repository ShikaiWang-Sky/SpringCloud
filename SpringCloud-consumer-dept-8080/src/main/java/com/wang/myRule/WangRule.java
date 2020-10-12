package com.wang.myRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WangRule {

    @Bean
    public IRule myRule() {
        return new WangRandomRule();
    }

}
