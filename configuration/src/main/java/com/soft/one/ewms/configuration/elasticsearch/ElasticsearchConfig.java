package com.soft.one.ewms.configuration.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Title：Elasticsearch配置类
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/20
 */
@Data
@Configuration
@ConfigurationProperties(prefix="spring.elasticsearch.jest") // 配置文件前缀
@PropertySource("classpath:elasticsearch.properties") // 引入变量所需配置文件
public class ElasticsearchConfig {

    // 自动对应配置文件，名字相同，值自动注入
    private String url;
    private Integer readTimeout;
    private Integer connectionTimeout;

    // 使用引擎注入类
    @Bean
    public JestClient getJestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(this.url)
                .multiThreaded(true)
                .connTimeout(this.connectionTimeout)
                .readTimeout(this.readTimeout)
                .build());
        return factory.getObject();
    }
}