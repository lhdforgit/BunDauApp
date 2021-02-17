package com.example.bundauapp.common.ktx

import android.text.SpannableStringBuilder
import android.text.Spanned

/**
 * SpannableStringBuilder("Thanks Jetbrains, ")
 * .withSpan(StyleSpan(android.graphics.Typeface.BOLD)) { append("Kotlin saves time") }
 * .append(" and is great.")
 */
inline fun SpannableStringBuilder.withSpan(
    span: Any,
    action: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder {
    val from = length
    action()
    setSpan(span, from, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}