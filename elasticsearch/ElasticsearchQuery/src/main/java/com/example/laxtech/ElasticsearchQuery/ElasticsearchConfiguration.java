package com.example.laxtech.ElasticsearchQuery;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;
import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.laxtech.ElasticsearchQuery")
public class ElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clustername}")
    private String elasticsearchClusterName;

    @Resource
    private Environment environment;

    @Bean
    public Client client() throws Exception {

        Settings settings = Settings.builder()
                .put("cluster.name", elasticsearchClusterName).build();
        TransportClient transportClient = new PreBuiltTransportClient(settings);

        transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(environment.getProperty("elasticsearch.host")),
                Integer.parseInt(environment.getProperty("elasticsearch.port"))));

        return transportClient;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }


}