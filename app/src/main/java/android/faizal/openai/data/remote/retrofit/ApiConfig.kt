package android.faizal.openai.data.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private const val BASE_URL = "https://api.openai.com/v1/"

   fun getApiService():ApiService{
       val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
       val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
       val retrofit = Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .client(client)
           .build()
       
       return retrofit.create(ApiService::class.java)
   }
}