package com.prod.springboot.prod.utils.consul;


import com.google.common.base.Strings;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Slf4j
@EnableSwagger2
public class ServerConfig {

    /** 定义类**/
    @Bean(name = "consulConfigProperties")
    public ConsulConfigProperties consulConfigProperties() {
        return new ConsulConfigProperties();
    }

    @Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //basePackage是去掉默认带的测试的Basic Error Controller
                .apis(RequestHandlerSelectors.basePackage("com.prod.springboot.prod.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口列表 v1.1.0") // 任意，请稍微规范点
                .description("接口测试") // 任意，请稍微规范点
                .version("1.1.0")
                .build();
    }


    @Bean("consul")
    /**Consul
     * com.orbitz.consul*/
    public Consul getConsul(ConsulConfigProperties p) {
        String serviceTag = p.getServiceTag();
        if (Strings.isNullOrEmpty(serviceTag)) {
            log.error("service.tag not found");
            throw new RuntimeException("Cannot start service without service.tag");
        }
        try {
            //consul 默认端口号：8500
            log.info("Connect to consul agent: " + p.getHostIp() + ':' + 8500);
            return Consul.builder().withHostAndPort(HostAndPort.fromString(p.getHostIp()+":8500")).build();
        } catch (Exception e) {
            // if service.tag=local,可以忽略consul
            if (!serviceTag.equalsIgnoreCase("local")) {
                log.error("Connect to consul failed, boot aborted!");
                throw new RuntimeException(e);
            }
            log.info("Connect to consul failed. service.tag={}, load utils from classpath", "local");
            return null;
        }
    }


}
