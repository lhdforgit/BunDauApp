package com.example.bundauapp.common.ktx

import com.google.gson.Gson


fun Any?.serialize(clazz: Class<*>): String {
    return try {
        if (this == null) {
            ""
        } else Gson().toJson(this, clazz)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}