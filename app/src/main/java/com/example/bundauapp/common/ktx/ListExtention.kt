package com.example.bundauapp.common.ktx

fun <T> MutableList<T>.toMutableListNotEmpty(): MutableList<T>? {
    return if (this.isEmpty()) null else this
}


/**
 * Lấy phần tử đầu tiên theo perdiacate if ko thoả mãn trả lại phần tử đầu tiên.
 * */
fun <T> MutableList<T>.getFirstOfPredicateOrFirst(predicate: (t: T) -> Boolean): T? {
    return if (this.isEmpty()) null else {
        val list = mutableListOf<T>()
        for (element in this) if (predicate(element)) list.add(element)
        if (list.isEmpty()) this.first() else list.first()
    }
}

fun <T> List<T>.listGetFirstOfPredicateOrFirst(predicate: (t: T) -> Boolean): T? {
    return if (this.isEmpty()) null else {
        val list = mutableListOf<T>()
        for (element in this) if (predicate(element)) list.add(element)
        if (list.isEmpty()) this.first() else list.first()
    }
}

fun <T> List<T>.toListNotEmpty(): MutableList<T>? {
    return if (this.isEmpty()) null else this.toMutableList()
}

fun <T> MutableList<T>?.lastOrNull(): T? {
    return if (this.isNullOrEmpty()) null else this.last()
}