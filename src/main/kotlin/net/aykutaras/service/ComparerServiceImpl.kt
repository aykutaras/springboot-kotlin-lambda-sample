package net.aykutaras.service

import net.aykutaras.domain.ComparableData
import net.aykutaras.domain.ComparerService
import org.springframework.stereotype.Service

@Service
class ComparerServiceImpl : ComparerService {
    override fun compare(comparableData: ComparableData): String {
        if (checkSidesAreFilled(comparableData)) return "One of the sides are null"

        val pairedData = convertToNonNullable(comparableData)
        if (isSameSize(pairedData.first, pairedData.second)) return "Sides are different length"
        if (isSameString(pairedData.first, pairedData.second)) return "Sides are same"

        return mapToString(diff(pairedData.first, pairedData.second))
    }

    private fun checkSidesAreFilled(data: ComparableData): Boolean {
        return data.left.isNullOrEmpty() || data.right.isNullOrEmpty()
    }

    private fun convertToNonNullable(comparableData: ComparableData): Pair<String, String> {
        val left = comparableData.left!!
        val right = comparableData.right!!

        return Pair(left, right)
    }

    private fun isSameSize(left: String, right: String): Boolean {
        return left.length != right.length
    }

    private fun isSameString(left: String, right: String): Boolean {
        return left == right
    }

    private fun diff(left: String, right: String): HashMap<Int, Int> {
        var currentOffset = -1
        val diffResult = hashMapOf<Int, Int>()

        for (i in left.indices) {
            val leftChar = left[i]
            val rightChar = right[i]

            if (charsDifferent(leftChar, rightChar)) {
                currentOffset = setCurrentOffset(currentOffset, i)

                increaseLength(currentOffset, diffResult)
            }

            currentOffset = setDefaultOffset(leftChar, rightChar, currentOffset)
        }

        return diffResult
    }

    private fun charsDifferent(left: Char, right: Char): Boolean {
        return left!= right
    }

    private fun setCurrentOffset(offset: Int, index: Int): Int {
        return if (offset == -1) index else offset
    }

    private fun increaseLength(offset: Int, diffResult: HashMap<Int, Int>) {
        diffResult.merge(offset, 1, Int::plus)
    }

    private fun setDefaultOffset(left: Char, right: Char, offset: Int): Int {
        return if (left == right) -1 else offset
    }

    private fun mapToString(diff: HashMap<Int, Int>): String {
        return diff.map { (k, v) -> "Offset: $k, Length: $v" }.joinToString("; ")
    }
}