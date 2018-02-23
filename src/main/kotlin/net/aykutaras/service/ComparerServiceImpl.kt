package net.aykutaras.service

import net.aykutaras.domain.ComparableData
import net.aykutaras.domain.ComparerService
import org.springframework.stereotype.Service
import sun.plugin.dom.exception.InvalidStateException

@Service
class ComparerServiceImpl : ComparerService {
    override fun compare(comparableData: ComparableData) {
        if (comparableData.left == null || comparableData.right == null) {
            throw InvalidStateException("Missing field left/right")
        }

        if (comparableData.left?.length != comparableData.right?.length) {
//            return comparableData.right
        }

        if (comparableData.left.equals(comparableData.right)) {
//            return comparableData.right
        }
    }
}