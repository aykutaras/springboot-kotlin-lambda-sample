package net.aykutaras.domain

interface ComparableDataStore {
    fun get(id: String): ComparableData

    fun setLeft(id: String, data: String): Boolean
    fun setRight(id: String, data: String): Boolean
}