package ru.nsu.cocktailDB.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nsu.cocktailDB.data.model.CocktailCategory
import ru.nsu.cocktailDB.data.model.DrinkCompactInfo
import ru.nsu.cocktailDB.data.model.DrinkDetailedInfo
import ru.nsu.cocktailDB.data.model.response.DrinksResponse

interface CocktailDBAPI {
    companion object {
        const val baseUrl = "https://thecocktaildb.com/api/json/v1/1/"
    }

    @GET("list.php?c=list")
    fun getCocktailCategories(): Single<DrinksResponse<CocktailCategory>>

    @GET("filter.php")
    fun getCocktailsByCategory(@Query("c") category: String): Single<DrinksResponse<DrinkCompactInfo>>

    @GET("lookup.php")
    fun getCocktailById(@Query("i") id: String): Single<DrinksResponse<DrinkDetailedInfo>>
}