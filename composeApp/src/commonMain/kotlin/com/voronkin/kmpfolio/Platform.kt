package com.voronkin.kmpfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform