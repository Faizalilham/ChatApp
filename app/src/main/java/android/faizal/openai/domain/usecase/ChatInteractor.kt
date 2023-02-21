package android.faizal.openai.domain.usecase

import android.faizal.openai.domain.model.Chat
import android.faizal.openai.domain.model.User
import android.faizal.openai.domain.repository.IChatRepository
import kotlinx.coroutines.flow.Flow

class ChatInteractor(private val iChatRepository : IChatRepository):ChatUseCase {
    override suspend fun addList(id: Int, text: String, user: User) {
        return iChatRepository.addList(id,text,user)
    }

    override fun getAllChat(): Flow<MutableList<Chat>> {
        return iChatRepository.getAllChat()
    }
}