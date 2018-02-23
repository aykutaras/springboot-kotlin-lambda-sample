package net.aykutaras.domain

data class ComparableData(
        private var left: String? = null,
        private var right: String? = null,
        private var locked: Boolean = false
) {
    fun addDataToLeft(data: String) {
        checkIfLocked()

        this.left = data
    }

    fun addDataToRight(data: String) {
        checkIfLocked()

        this.right = data
    }

    fun lock() {
        this.locked = true
    }

    fun unlock() {
        this.locked = false
    }

    private fun checkIfLocked() {
        if (locked) {
            throw IllegalStateException("Data is processing")
        }
    }
}
