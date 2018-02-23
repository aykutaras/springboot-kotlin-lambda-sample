package net.aykutaras.repository

import net.aykutaras.domain.ComparableData
import net.aykutaras.domain.ComparableDataStore

class CacheStore : ComparableDataStore {
    companion object {
        val store = mutableMapOf<String, ComparableData>()
    }

    override fun get(id: String): ComparableData {
        return store.getOrElse(id) { throw NoSuchElementException()}
    }

    override fun setLeft(id: String, data: String): Boolean {
        val comparableData = store.getOrElse(id) { ComparableData(null, null) }
        comparableData.left = data

        store[id] = comparableData
        return true
    }

    override fun setRight(id: String, data: String): Boolean {
        val comparableData = store.getOrElse(id) { ComparableData(null, null) }
        comparableData.right = data

        store[id] = comparableData
        return true
    }
}