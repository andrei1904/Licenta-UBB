package com.example.tasksapplication.api.model

import android.content.res.Resources
import com.example.tasksapplication.utils.Constants
import io.reactivex.SingleEmitter

open class BackEndResponse<T>(val code: Int, val message: String, val result: T) {}

fun <T : Any> SingleEmitter<T>.handleResponse(response: BackEndResponse<T>?) = when {
    response == null -> onError(Resources.NotFoundException(Constants.API_NOT_FOUND))
    response.code == Constants.API_ERROR_CODE -> onError(Resources.NotFoundException(response.message))
    else -> onSuccess(response.result)
}
