package com.soft.one.ewms.business.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Title：引用Elasticsearch
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/20
 */
@Configuration
@ComponentScan({"com.soft.one.ewms.configuration.elasticsearch"}) //包名
public class EsConfig {
}
