package net.aykutaras.lambda.request

class StoreRequest() {
    lateinit var id: String
    lateinit var data: String

    constructor(id: String, data: String): this() {
        this.id = id
        this.data = data
    }
}
