package android.faizal.openai.di

import android.faizal.openai.data.remote.retrofit.ApiConfig
import android.faizal.openai.repository.ChatRepository

object ChatInjection {

    fun provideChatRepository():ChatRepository {
        val apiService = ApiConfig.getApiService()
        return ChatRepository.getInstance(apiService)
    }
}