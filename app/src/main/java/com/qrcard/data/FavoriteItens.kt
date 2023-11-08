package com.qrcard.data

class FavoriteItens {
    val _listItemFavorite = mutableListOf<Int>()

    val listItemFavorite : List<Int> = _listItemFavorite

    fun insertFavorite(id : Int) {
        if(_listItemFavorite.none { it == id }){
            _listItemFavorite.add(id)
        }
    }

    fun removeFavorite(id : Int){
        _listItemFavorite.remove(id)
    }

    fun getFavorites(): List<Int> {
        return listItemFavorite
    }
}