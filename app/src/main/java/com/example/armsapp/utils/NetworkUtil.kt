package com.example.armsapp.utils

import io.ktor.client.network.sockets.SocketTimeoutException

suspend fun <T> safeApiCall(
    logger: Logger,
    block: suspend () -> T,
): Result<T> =
    try {
        Result.success(block())
    } catch (e: SocketTimeoutException) {
        logger.e("ApiCall", "Timeout occurred: ${e.message}")
        Result.failure(e)
    } catch (e: Exception) {
        logger.e("ApiCall", "Generic error: ${e.message}")
        Result.failure(e)
    }
