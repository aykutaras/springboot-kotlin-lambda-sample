package net.aykutaras.controller

import io.swagger.annotations.ApiOperation
import net.aykutaras.commands.StoreLeftHandler
import net.aykutaras.commands.StoreRightHandler
import net.aykutaras.controller.request.DataStoreRequest
import net.aykutaras.messages.commands.LeftStore
import net.aykutaras.messages.commands.RightStore
import net.aykutaras.messages.commands.StoreResponse
import net.aykutaras.messages.queries.GetResult
import net.aykutaras.messages.queries.GetResultResponse
import net.aykutaras.queries.GetResultHandler
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["v1/diff"])
class ApiController(private val leftStoreHandler: StoreLeftHandler,
                    private val rightStoreHandler: StoreRightHandler,
                    private val getResultHandler: GetResultHandler) {
    @ApiOperation(
            value = "Left Service",
            notes = "Adds data to left side of comparer. It will return error if comparison still in progress",
            response = StoreResponse::class,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            tags = ["String Comparer"])
    @PostMapping(value = ["/{id}/left"])
    fun left(@Valid @PathVariable("id") id: String, @Valid @RequestBody leftStore: DataStoreRequest): StoreResponse {
        return leftStoreHandler.handle(LeftStore(id, leftStore.data))
    }

    @ApiOperation(
            value = "Right Service",
            notes = "Adds data to right side of comparer. It will return error if comparison still in progress",
            response = StoreResponse::class,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            tags = ["String Comparer"])
    @PostMapping(value = ["/{id}/right"])
    fun right(@Valid @PathVariable("id") id: String, @Valid @RequestBody rightStore: DataStoreRequest): StoreResponse {
        return rightStoreHandler.handle(RightStore(id, rightStore.data))
    }

    @ApiOperation(
            value = "Process Diff",
            notes = "Processes comparison operation and returns necessary results",
            response = GetResultResponse::class,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            tags = ["String Comparer"])
    @GetMapping(value = ["/{id}"])
    fun diff(@Valid @PathVariable("id") id: String): GetResultResponse {
        return getResultHandler.handle(GetResult(id))
    }
}