package com.hknusc.web.util.type

enum class StoreCategory(val label: String, val code: Short) {
    KOREAN("한식", 101),
    CHINESE("중식", 201),
    WESTERN("양식", 301),
    JAPANESE("일식", 401);

    companion object {
        fun find(code: Short) = StoreCategory.values().find { it.code == code }
    }
}
