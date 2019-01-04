package com.bristot.domain

interface Logger {

    // Logger to Verbose
    fun v(message: String)
    fun v(message: String, throwable: Throwable)

    // Logger to Debug
    fun d(message: String)
    fun d(message: String, throwable: Throwable)

    // Logger to Information
    fun i(message: String)
    fun i(message: String, throwable: Throwable)

    // Logger to Warnings
    fun w(message: String)
    fun w(message: String, throwable: Throwable)
    fun w(throwable: Throwable)

    // Logger to Errors
    fun e(message: String)
    fun e(message: String, throwable: Throwable)
    fun e(throwable: Throwable)
}