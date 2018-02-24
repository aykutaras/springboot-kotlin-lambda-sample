package net.aykutaras.repository

import net.aykutaras.domain.ComparableData
import net.aykutaras.domain.ComparableDataStore
import org.springframework.stereotype.Component

@Component
class CacheStore : ComparableDataStore {
    companion object {
        val store = mutableMapOf<String, ComparableData>()
    }

    override fun get(id: String): ComparableData {
        val defaultValue = { ComparableData() }
        return store.getOrElse(id, defaultValue)
    }

    override fun put(id: String, data: ComparableData) {
        store[id] = data
    }
}