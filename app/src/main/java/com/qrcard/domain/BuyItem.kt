package com.qrcard.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



data class BuyItem (
    val id : String,
    val nome : String,
    val categoria: String,
    var quantidade : Int,
    val preco : String,
    val descricao : String,
    val urlPhoto : String,
    var isFavorite : Boolean
) {
    fun increment(){
        quantidade += 1;
    }


    fun decrement(){
        if(quantidade > 0){
            quantidade -= 1;
        }
    }
}