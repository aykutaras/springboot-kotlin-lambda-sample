package net.aykutaras.commands

import net.aykutaras.domain.ComparableDataStore
import net.aykutaras.domain.Handler
import net.aykutaras.messages.commands.RightStore
import net.aykutaras.messages.commands.StoreResponse
import org.springframework.stereotype.Service

@Service
class StoreRightHandler(private val store: ComparableDataStore) : Handler<RightStore, StoreResponse> {
    override fun handle(request: RightStore): StoreResponse {
        val data = store.get(request.id)
        data.addDataToRight(request.data)
        store.put(request.id, data)

        return StoreResponse("SUCCESS", "Data stored to right successfully")
    }
}