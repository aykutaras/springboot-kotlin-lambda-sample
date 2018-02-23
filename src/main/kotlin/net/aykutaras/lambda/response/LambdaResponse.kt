package net.aykutaras.lambda.response

class LambdaResponse() {
    var statusCode: Int = 500
    lateinit var body: String

    constructor(statusCode: Int, body: String): this() {
        this.statusCode = statusCode
        this.body = body
    }
}