package net.aykutaras.domain

data class ComparableData(
        private var left: String? = null,
        private var right: String? = null,
        private var locked: Boolean = false
) {
    fun addDataToLeft(data: String): ComparableData {
        checkIfLocked()

        this.left = data
        return this
    }

    fun addDataToRight(data: String): ComparableData {
        checkIfLocked()

        this.right = data
        return this
    }

    fun lock(): ComparableData {
        this.locked = true
        return this
    }

    fun unlock(): ComparableData {
        this.locked = false
        return this
    }

    private fun checkIfLocked() {
        if (locked) {
            throw IllegalStateException("Data is processing")
        }
    }
}
