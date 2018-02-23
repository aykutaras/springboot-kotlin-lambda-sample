package net.aykutaras

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@ComponentScan("net.aykutaras")
@SpringBootApplication
open class Application {
    @Bean
    open fun starterApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.aykutaras.controller"))
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Springboot Kotlin")
                .description("Springboot Kotlin Sample with Heroku integration")
                .version("1.0.0")
                .build()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
