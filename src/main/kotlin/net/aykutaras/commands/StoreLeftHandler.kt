package net.aykutaras.commands

import net.aykutaras.domain.ComparableDataStore
import net.aykutaras.domain.Handler
import net.aykutaras.messages.commands.LeftStore
import net.aykutaras.messages.commands.StoreResponse
import org.springframework.stereotype.Service

@Service
class StoreLeftHandler(private val store: ComparableDataStore) : Handler<LeftStore, StoreResponse> {
    override fun handle(request: LeftStore): StoreResponse {
        val data = store.get(request.id)
        data.addDataToLeft(request.data)
        store.put(request.id, data)

        return StoreResponse("SUCCESS", "Data stored to left successfully")
    }
}