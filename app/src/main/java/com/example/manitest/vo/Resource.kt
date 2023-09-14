package com.example.manitest.vo

import android.util.Log


data class Resource<T>(var status: Status, var data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> notSetup(data: T?,message: String?): Resource<T> {
            return Resource(Status.NOT_SETUP, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            Log.d("gbut",data.toString())
            return Resource(Status.LOADING, data, null)
        }

    }
}