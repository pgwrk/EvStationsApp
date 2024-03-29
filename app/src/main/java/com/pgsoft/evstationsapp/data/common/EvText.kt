package com.pgsoft.evstationsapp.data.common

import android.content.Context
import androidx.annotation.StringRes

sealed class EvText {
    abstract fun resolve(context: Context): String

    data class PlainText(val value: String) : EvText() {
        override fun resolve(context: Context): String = value
    }

    data class ResText(@StringRes val resId: Int, private val formatArgs: List<Any>? = null) : EvText() {
        override fun resolve(context: Context): String =
            if (formatArgs == null) context.resources.getString(resId)
            else {
                val resolvedArgs = formatArgs.map { arg -> if (arg is EvText) arg.resolve(context) else arg }
                context.resources.getString(resId, *resolvedArgs.toTypedArray())
            }
    }
}