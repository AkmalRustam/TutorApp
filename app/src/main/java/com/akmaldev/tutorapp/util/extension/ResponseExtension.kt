package com.akmaldev.tutorapp.util.extension

import com.akmaldev.tutorapp.data.model.response.common.ErrorResponseData
import com.akmaldev.tutorapp.util.helper.WrappedResponse
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException

fun <T> Response<T>.toWrappedResponse(): WrappedResponse<T> {
    return try {
        when (this.code()) {
            in 200..299 -> {
                val body = this.body()
                if (body != null) {
                    WrappedResponse.Success(body)
                } else {
                    WrappedResponse.Error("Bo'sh javob", this.code())
                }
            }

            in 400..499 -> {
                val errorBody = this.errorBody()?.string()
                val gson = Gson()
                val errorResponseData = gson.fromJson(errorBody, ErrorResponseData::class.java)
                val errorMessage = errorResponseData.message
                WrappedResponse.Error(errorMessage, this.code())
            }

            in 500..599 -> {
                WrappedResponse.Error("Serverda xatolik", this.code())
            }

            else -> {
                WrappedResponse.Error("Xatolik", this.code())
            }
        }
    } catch (e: IOException) {
        // Tarmoq yoki ulanish istisnosi
        WrappedResponse.Error("Internetga ulanishda xatolik")
    } catch (e: Exception) {
        // Boshqa umumiy istisnolar
        WrappedResponse.Error("Xatolik")
    }
}