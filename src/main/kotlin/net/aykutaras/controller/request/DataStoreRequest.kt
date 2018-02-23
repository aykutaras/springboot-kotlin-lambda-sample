package net.aykutaras.controller.request

class DataStoreRequest() {
    lateinit var data: String

    constructor(data: String): this() {
        this.data = data
    }
}
