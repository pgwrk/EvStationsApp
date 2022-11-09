package com.pgsoft.evstationsapp.extension

import androidx.annotation.StringRes
import com.pgsoft.evstationsapp.data.common.EvText

fun<T> Result<T>.asEvTextOnError(@StringRes defaultId: Int): EvText =
    exceptionOrNull()?.message?.let {
        EvText.PlainText(it)
    }  ?: run {
        EvText.ResText(defaultId)
    }