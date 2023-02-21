package android.faizal.openai.di

import android.faizal.openai.data.remote.retrofit.ApiConfig
import android.faizal.openai.domain.repository.IChatRepository
import android.faizal.openai.domain.usecase.ChatInteractor
import android.faizal.openai.domain.usecase.ChatUseCase
import android.faizal.openai.repository.ChatRepository

object ChatInjection {

    private fun provideChatRepository():IChatRepository {
        val apiService = ApiConfig.getApiService()
        return ChatRepository.getInstance(apiService)
    }

    fun provideChatUseCase():ChatUseCase{
        val repository = provideChatRepository()
        return ChatInteractor(repository)
    }
}