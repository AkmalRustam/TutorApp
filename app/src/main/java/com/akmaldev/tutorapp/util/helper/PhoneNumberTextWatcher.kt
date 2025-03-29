package com.akmaldev.tutorapp.util.helper

import android.text.Editable
import android.text.TextWatcher

class PhoneNumberTextWatcher : TextWatcher {

    private var isFormatting: Boolean = false
    private var deletingHyphen: Boolean = false
    private var hyphenStart: Int = 0
    private var deletingBackward: Boolean = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        if (count == 1 && after == 0) {
            val charAt = s?.get(start)
            if (charAt == ' ') {
                deletingHyphen = true
                hyphenStart = start
            } else {
                deletingHyphen = false
            }
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (before > 0 && count == 0) {
            deletingBackward = true
        } else {
            deletingBackward = false
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (isFormatting || deletingBackward || s.isNullOrEmpty()) {
            return
        }

        isFormatting = true
        val formatted = formatPhoneNumber(s.toString().replace(" ", ""))
        s.replace(0, s.length, formatted, 0, formatted.length)
        isFormatting = false
    }

    private fun formatPhoneNumber(number: String): String {
        val cleanNumber = number.filter { it.isDigit() } // Remove non-digit characters
        val sb = StringBuilder()

        for (i in cleanNumber.indices) {
            if (i == 2 || i == 5 || i == 7) {
                sb.append(" ")
            }
            sb.append(cleanNumber[i])
        }

        return sb.toString()
    }
}
