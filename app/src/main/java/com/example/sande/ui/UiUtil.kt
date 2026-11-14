package com.example.sande.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/** Money is stored as integer cents. 22000 -> "220.00". */
fun formatKes(cents: Long): String {
    val sign = if (cents < 0) "-" else ""
    val abs = kotlin.math.abs(cents)
    return "$sign${abs / 100}.${(abs % 100).toString().padStart(2, '0')}"
}

/** Builds a ViewModel from a plain lambda so screens can pass repositories in. */
fun <T : ViewModel> viewModelFactoryOf(build: () -> T): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <V : ViewModel> create(modelClass: Class<V>): V = build() as V
    }
