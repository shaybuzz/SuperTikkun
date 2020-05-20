package com.supertikkun.network.response


import com.google.gson.annotations.SerializedName

data class Narrator(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("updated_at")
    val updatedAt: String
)