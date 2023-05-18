package com.bme.kotlin.webclient.extensions

import java.util.*

/**
 * Generic option to unwrap Optional
 * @return the value or null
 */
fun <T> Optional<T>.unwrap():T?{
    return this.orElse(null)
}