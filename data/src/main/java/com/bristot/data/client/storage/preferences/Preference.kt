package com.bristot.data.client.storage.preferences

import java.lang.reflect.Type

interface Preference {
    @Throws(NotFoundException::class)
    fun <T> get(key: String, type: Type): T

    fun set(key: String, value: Any?)

    fun clear()

    class NotFoundException : Exception()
}