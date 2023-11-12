package com.qrcard.iu.fragment.modelview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qrcard.data.Retrofit.RestaurantRetrofit
import com.qrcard.domain.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantViewModel(
) : ViewModel() {

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> get() = _restaurant

    init {
        _restaurant.value = Restaurant()
    }

    fun updateRestaurant(newRestaurant: Restaurant) {
        _restaurant.value = newRestaurant
    }

    private val restRetrofit = RestaurantRetrofit()

    class NomeDoRestauranteIncorretoException(message: String) : Exception(message)

    fun setRestaurant(context: Context?, restaurantName: String) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            try {
                val testRestaurant = restRetrofit.getRestaurant()

                withContext(Dispatchers.Main) {
                    Log.e("Verificando : ", "recuperado : '${testRestaurant.name}' \n doQrcode : '${restaurantName}'")
                    if (testRestaurant.name == restaurantName) {
                        updateRestaurant(testRestaurant)
                    } else {
                        Toast.makeText(context, "QRCode inválido", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: NomeDoRestauranteIncorretoException) {
                Log.e("erro Restaurant : ", e.toString())
            } catch (e: Exception) {
                Log.e("erro Restaurant : ", e.toString())
            }
        }
    }

    fun restaurantName() : String{
        return restaurant.value?.name ?: ""
    }

    fun restaurantTable() : String{
        return restaurant.value?.table ?: ""
    }


}
