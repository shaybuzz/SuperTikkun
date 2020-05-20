package com.supertikkun.network.response


import com.google.gson.annotations.SerializedName

data class TikkunTextResponse(
    val taamim: String,
    @SerializedName("text_pieces")
    val textPieces: List<Any>,
    val tikkun: String
)