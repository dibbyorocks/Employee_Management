package com.example.Employee_Management.Config;

import org.springframework.core.env.Environment;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.apache.tomcat.jni.File.getInfo;

@Configuration
//@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket getDocketInstance(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo()).select().
                apis(RequestHandlerSelectors.basePackage("com.example.Employee_Management.Controller"))
                .paths(PathSelectors.any()).build();
    }
//@Bean
//public Docket api() {
//    return new Docket(DocumentationType.OAS_30)
//            .apiInfo(getInfo())
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.example.Employee_Management.Controller"))
//            .paths(PathSelectors.any())
//            .build();

////
    private ApiInfo getInfo() {
        return new ApiInfo("Blogging application:","This project is developed by dibbyo",
                "2.0","Terms of Service",new Contact("Dibbyo",
                "https://swaggerDocument.com","dibbyo@gmail.com"),
                "License of APIs","Api License Url", Collections.emptyList());
    }
////@Bean
////public Docket api() {
////
////    return new Docket(DocumentationType.SWAGGER_2)
////            .apiInfo(getInfo())
////           // .securityContexts(securityContexts())
////           // .securitySchemes(Arrays.asList(apiKey()))
////            .select()
////            .apis(RequestHandlerSelectors.any())
////            .paths(PathSelectors.any()).build();
////}
//
//    private ApiInfo getInfo() {
//
//        return new ApiInfo("Blogging Application Backend", "Project is developed by Krishna Lagad", "1.0",
//                "Terms of Service", new Contact("Krishna Lagad", "https://bit.ly/3x2D8oj", "krishnalagad2@gmail.com"),
//                "License of API's", "API License URL", Collections.emptyList());
//    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }


    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
