package net.aykutaras.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import net.aykutaras.commands.StoreRightHandler
import net.aykutaras.lambda.request.StoreRequest
import net.aykutaras.lambda.response.LambdaResponse
import net.aykutaras.messages.commands.RightStore
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication(scanBasePackages = ["net.aykutaras"])
open class RightStoreLambdaHandler : RequestHandler<StoreRequest, LambdaResponse> {
    override fun handleRequest(input: StoreRequest?, context: Context?): LambdaResponse {
        if (input == null) {
            throw NoSuchElementException()
        }

        val storeRightHandler = getApplicationContext().getBean(StoreRightHandler::class.java)
        val response = storeRightHandler.handle(RightStore(input.id, input.data))
        return LambdaResponse(statusCode = 200, body = response.message)
    }

    private fun getApplicationContext(): ConfigurableApplicationContext {
        return SpringApplication.run(RightStoreLambdaHandler::class.java)
    }
}
