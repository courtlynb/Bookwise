package com.example.bookwise.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book (
    val title: String,
    val author: String,
    @SerialName("thumbnail") val imgSrc: String,
    @SerialName("description") val description: String?
)