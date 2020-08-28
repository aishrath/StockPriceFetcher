package io.github.aishrath.stockfetcher.util

import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpGet

class RestKlient {
    companion object {
        @JvmStatic
        fun get(url: String, params: Map<String, String>?): Any {
            val response = url
                    .httpGet(params!!.keys zip params.values)
                    .response()
                    .second
            return determineResponseSuccess(response)
        }

        @JvmStatic
        fun get(url: String): Any {
            val response = url
                    .httpGet()
                    .response()
                    .second
            return determineResponseSuccess(response)
        }

        private fun determineResponseSuccess(response: Response): Any {
            return if (response.isSuccessful) {
                response.body().asString("application/json")
            } else {
                false
            }
        }
    }
}