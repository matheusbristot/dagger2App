package com.bristot.data.mapper

abstract class Mapper<in I, out O> {
    abstract fun transform(i: I): O

    fun transformElements(items: List<I>?): List<O> = items?.map(this::transform) ?: emptyList()
}
