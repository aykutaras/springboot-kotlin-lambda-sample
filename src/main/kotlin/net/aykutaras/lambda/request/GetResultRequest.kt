package net.aykutaras.lambda.request

class GetResultRequest() {
    lateinit var id: String

    constructor(id: String): this() {
        this.id = id
    }
}
