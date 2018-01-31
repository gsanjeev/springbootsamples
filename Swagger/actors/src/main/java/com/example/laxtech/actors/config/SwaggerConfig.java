package com.example.laxtech.actors.config;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {

               return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();

/*        Docket docket=new Docket(DocumentationType.SWAGGER_2);

        docket.groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
        docket.globalResponseMessage(RequestMethod.GET, ImmutableList.of(new ResponseMessageBuilder()
                .code(400)
                .message("Bad Request")
                .responseModel(new ModelRef("Error")).build(),new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error")).build()));
        return docket;
*/


    }

    private Predicate<String> postPaths() {
        return or( regex("/actors.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Actors API")
                .description("Actors API reference for developers")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact("Sanjeev Tripathi", "www.laxtech.com", "gsanjeevtripathi@gmail.com")).license("Laxtech License")
                .licenseUrl("www.laxtech.com").version("1.0.0").build();
    }

}