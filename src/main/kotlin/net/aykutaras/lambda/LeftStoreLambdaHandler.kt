package net.aykutaras.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import net.aykutaras.commands.StoreLeftHandler
import net.aykutaras.lambda.request.StoreRequest
import net.aykutaras.lambda.response.LambdaResponse
import net.aykutaras.messages.commands.LeftStore
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication(scanBasePackages = ["net.aykutaras"])
open class LeftStoreLambdaHandler : RequestHandler<StoreRequest, LambdaResponse> {
    override fun handleRequest(input: StoreRequest?, context: Context?): LambdaResponse {
        if (input == null) {
            throw NoSuchElementException()
        }

        val storeLeftHandler = getApplicationContext().getBean(StoreLeftHandler::class.java)
        val response = storeLeftHandler.handle(LeftStore(input.id, input.data))
        return LambdaResponse(statusCode = 200, body = response.message)
    }

    private fun getApplicationContext(): ConfigurableApplicationContext {
        return SpringApplication.run(LeftStoreLambdaHandler::class.java)
    }
}