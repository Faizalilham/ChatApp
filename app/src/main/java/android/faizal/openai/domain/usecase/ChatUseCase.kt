package android.faizal.openai.domain.usecase

import android.faizal.openai.domain.model.Chat
import android.faizal.openai.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ChatUseCase {

    suspend fun addList(id : Int,text : String, user : User)

    fun getAllChat(): Flow<MutableList<Chat>>
}