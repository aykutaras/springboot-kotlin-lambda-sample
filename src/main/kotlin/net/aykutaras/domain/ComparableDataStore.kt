package net.aykutaras.domain

interface ComparableDataStore {
    fun get(id: String): ComparableData
    fun put(id: String, data: ComparableData)
}