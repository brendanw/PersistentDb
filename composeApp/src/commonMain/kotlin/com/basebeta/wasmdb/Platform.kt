package com.basebeta.wasmdb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform