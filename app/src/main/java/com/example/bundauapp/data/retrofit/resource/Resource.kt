/*
 * Copyright 10/10/2018 Hahalolo. All rights reserved.
 *
 * https://help.hahalolo.com/commercial_terms/
 */

package com.example.bundauapp.data.retrofit.resource

import com.example.bundauapp.data.retrofit.resource.StatusNetwork.LOADING
import com.example.bundauapp.data.retrofit.resource.StatusNetwork.SUCCESS

/**
 * A generic class that holds a value with its loading statusNetwork.
</T> */
class Resource<T>
constructor(
    val statusNetwork: Int,
    val data: T?,
    val message: String?
) {

    val isUnauthorized: Boolean
        get() = statusNetwork == 401

    val isLoading: Boolean
        get() = statusNetwork == LOADING

    val isError: Boolean
        get() = statusNetwork != LOADING && statusNetwork != SUCCESS

    val isSuccess: Boolean
        get() = statusNetwork == SUCCESS

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(error: Int, msg: String?, data: T?): Resource<T> {
            return Resource(error, data, msg)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return error(400, throwable.message, null)
        }

        fun <T> notFound(): Resource<T> {
            return error(404, "Resource not found", null)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }

    override fun toString(): String {
        return "Resource(statusNetwork=$statusNetwork, data=$data, message=$message)"
    }
}
