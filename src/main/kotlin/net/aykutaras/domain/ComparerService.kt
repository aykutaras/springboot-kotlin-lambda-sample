package net.aykutaras.domain

interface ComparerService {
    fun compare(comparableData: ComparableData): String
}