package android.faizal.openai.domain.repository

import android.faizal.openai.domain.model.Chat
import android.faizal.openai.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IChatRepository {

    suspend fun addList(id : Int, text : String, user : User)

    fun getAllChat(): Flow<MutableList<Chat>>
}