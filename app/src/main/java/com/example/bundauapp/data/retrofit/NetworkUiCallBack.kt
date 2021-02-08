package com.example.bundauapp.data.retrofit

interface NetworkUiCallBack<T> {
    fun loading()

    fun success(data: T?)

    fun error()
}