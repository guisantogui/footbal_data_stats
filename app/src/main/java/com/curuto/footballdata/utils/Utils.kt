package com.curuto.footballdata.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ktx.BuildConfig
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


private fun initSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
}

fun getStringSharedPreferences(context: Context, tag: String): String {
    return initSharedPreferences(context).getString(tag, "")!!
}

fun updateSharedPreferences(context: Context, tag: String, value: String) {
    initSharedPreferences(context).edit().putString(tag, value).apply()
}

fun updateSyncSharedPreferences(context: Context, tag: String, value: String) : Boolean {
    return initSharedPreferences(context).edit().putString(tag, value).commit()
}

fun logD(text: String?) {
    if (text?.isNotEmpty()!!) {
        Log.d(TAG_D, text)
    }
}

fun logE(text: String?) {
    if (text?.isNotEmpty()!!) {
        Log.e(TAG_E, text)
    }
}

fun logE(text: String?, exception: Throwable) {
    if (text?.isNotEmpty()!!) {
        Log.e(TAG_E, text, exception)
    }
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun getInflater(context: Context) : LayoutInflater {
    return LayoutInflater.from(context)
}

fun showSnackbar(v: View, message: String, length: Int){
    Snackbar.make(v, message, length).show()
}