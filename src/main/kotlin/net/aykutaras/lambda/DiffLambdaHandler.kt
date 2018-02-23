package net.aykutaras.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import net.aykutaras.lambda.request.GetResultRequest
import net.aykutaras.lambda.response.LambdaResponse
import net.aykutaras.messages.queries.GetResult
import net.aykutaras.queries.GetResultHandler
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication(scanBasePackages = ["net.aykutaras"])
open class DiffLambdaHandler : RequestHandler<GetResultRequest, LambdaResponse> {
    override fun handleRequest(input: GetResultRequest?, context: Context?): LambdaResponse {
        if (input == null) {
            throw NoSuchElementException()
        }

        val getResulttHandler = getApplicationContext().getBean(GetResultHandler::class.java)
        val response = getResulttHandler.handle(GetResult(input.id))
        return LambdaResponse(statusCode = 200, body = response.response)
    }

    private fun getApplicationContext(): ConfigurableApplicationContext {
        return SpringApplication.run(DiffLambdaHandler::class.java)
    }
}