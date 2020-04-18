package br.com.brlima.cursomc2.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Header
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    private val message201: ResponseMessage = this.create201CustomMessage()
    private val message204PUT: ResponseMessage = this.createSimpleMessage(204, "Atualização ok")
    private val message204DELETE: ResponseMessage = this.createSimpleMessage(204, "Exclusão ok")
    private val message403: ResponseMessage = this.createSimpleMessage(403, "Não autorizado")
    private val message404: ResponseMessage = this.createSimpleMessage(404, "Não encontrado")
    private val message422: ResponseMessage = this.createSimpleMessage(422, "Erro de validação")
    private val message500: ResponseMessage = this.createSimpleMessage(500, "Erro inesperado")

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, listOf(message403, message404, message500))
                .globalResponseMessage(RequestMethod.POST, listOf(message201, message403, message422, message500))
                .globalResponseMessage(RequestMethod.PUT, listOf(message204PUT, message403, message404, message422, message500))
                .globalResponseMessage(RequestMethod.DELETE, listOf(message204DELETE, message403, message404, message500))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("API do curso Spring Boot + Ionic")
                .description("Esta API é utilizada no projeto do curso de Spring Boot do prof. Nelio Alves")
                .version("Versão 1.0")
                .termsOfServiceUrl("https://www.udemy.com/terms")
                .contact(Contact("Nelio Alves", "udemy.com/user/nelio-alves", "nelio.cursos@gmail.com"))
                .license("Permitido uso para estudantes")
                .licenseUrl("https://www.udemy.com/terms")
                .extensions(emptyList())
                .build()
    }

    private fun createSimpleMessage(code: Int, message: String) = ResponseMessageBuilder().code(code).message(message).build()

    private fun create201CustomMessage(): ResponseMessage {
        val headers = hashMapOf<String, Header>()
        headers["location"] = Header("location", "URI do novo recurso", ModelRef("string"))
        return ResponseMessageBuilder()
                .code(201)
                .message("Recurso criado")
                .headersWithDescription(headers)
                .build()
    }
}
