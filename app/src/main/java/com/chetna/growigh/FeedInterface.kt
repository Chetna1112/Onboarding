package com.chetna.growigh


import android.os.Build.VERSION_CODES.BASE
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=56e0afcc78724d7089f518edf73115ce
//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=56e0afcc78724d7089f518edf73115ce
const val BASE_URL="https://newsapi.org/"
const val API_KEY="cadf19e2e3c14880bebd5c6eb49330bd"
interface FeedInterface {
    @GET("/v2/everything?apiKey=$API_KEY")
    fun gateHeadlines(@Query("q")q:String,@Query("page")page:Int):Call<News>
    //https://newsapi.org/v2/top-headlines?apiKey=56e0afcc78724d7089f518edf73115ce&country=in&page=1
}
//singleton/ retrofit object
object NewsService{
    val newsInstance:FeedInterface
    init{
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(FeedInterface::class.java)
    }
}