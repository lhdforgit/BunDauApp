package com.example.bundauapp.common.ktx

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat

fun EditText.setPriceFormat() {
    var current = ""
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            kotlin.runCatching {
                if (s.toString() != current) {
                    removeTextChangedListener(this)
                    val cleanString: String = s?.replace("""[$,.]""".toRegex(), "") ?: ""
                    val parsed = cleanString.toDouble()
                    val formatted = NumberFormat.getCurrencyInstance().format((parsed / 100))
                    current = formatted
                    setText(formatted)
                    setSelection(formatted.length)
                    addTextChangedListener(this)
                }
            }.getOrElse {
                it.printStackTrace()
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    })
}