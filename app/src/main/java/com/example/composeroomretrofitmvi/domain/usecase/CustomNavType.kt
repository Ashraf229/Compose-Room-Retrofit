package com.example.composeroomretrofitmvi.domain.usecase

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json


object CustomNavType {

    inline fun <reified T> genericNavType(serializer: KSerializer<T>) = object : NavType<T>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getString(key)?.let { Json.decodeFromString(serializer, it) }
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(serializer, Uri.decode(value))
        }

        override fun serializeAsValue(value: T): String {
            return Uri.encode(Json.encodeToString(serializer, value))
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(serializer, value))
        }
    }
}

