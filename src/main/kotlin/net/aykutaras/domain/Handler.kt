package net.aykutaras.domain

interface Handler<in Request, out Response> {
    fun handle(request: Request): Response
}