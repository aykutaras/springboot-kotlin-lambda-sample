package net.aykutaras.queries

import net.aykutaras.domain.ComparableData
import net.aykutaras.domain.ComparableDataStore
import net.aykutaras.domain.ComparerService
import net.aykutaras.domain.Handler
import net.aykutaras.messages.queries.GetResult
import net.aykutaras.messages.queries.GetResultResponse
import org.springframework.stereotype.Service

@Service
class GetResultHandler(private val store: ComparableDataStore,
                       private val comparer: ComparerService) : Handler<GetResult, GetResultResponse> {
    override fun handle(request: GetResult): GetResultResponse {
        val data = lockAndGetData(request.id)
        val comparisonResult = comparer.compare(data)
        unLockData(request.id)

        return GetResultResponse(comparisonResult)
    }

    private fun lockAndGetData(id: String): ComparableData {
        val comparableData = store.get(id)
        comparableData.lock()
        store.put(id, comparableData)

        return comparableData
    }

    private fun unLockData(id: String) {
        val comparableData = store.get(id)
        comparableData.unlock()
        store.put(id, comparableData)
    }
}