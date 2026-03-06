package com.appointy.designsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform