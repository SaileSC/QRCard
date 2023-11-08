package com.qrcard.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Item (
    val id : String,
    val nome : String,
    val categoria: String,
    val preco : String,
    val descricao : String,
    val urlPhoto : String,
    var isFavorite : Boolean
) : Parcelable