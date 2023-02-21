package android.faizal.openai.repository

import android.faizal.openai.data.remote.response.AiBody
import android.faizal.openai.data.remote.retrofit.ApiService
import android.faizal.openai.domain.model.Chat
import android.faizal.openai.domain.model.User
import android.faizal.openai.domain.repository.IChatRepository
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class ChatRepository(
   private val  api : ApiService
):IChatRepository {

    private val listChat : MutableList<Chat> = mutableStateListOf()


    override suspend fun addList(id : Int,text : String,user : User){
        try{
            listChat.add(Chat(id,text,user))
            delay(1000L)
            withContext(Dispatchers.Main){
                listChat.add(Chat(1,"Mengetik ...",User.Bot))
                val response = api.getResponseAI(AiBody(prompt = text))
                if(response.choices.isNotEmpty() && response.choices[0].text.isNotBlank()){
                    listChat.removeAt(listChat.size - 1)
                    listChat.add(Chat(id + 1,response.choices[0].text,User.Bot))
                }
            }
        }catch(e : Exception){
            listChat.removeAt(listChat.size - 1)
            listChat.add(Chat(1,"Timeout, sorry",User.Bot))
        }
    }


    override fun getAllChat(): Flow<MutableList<Chat>>{
        return flowOf(listChat)
    }

    companion object{
        @Volatile
        private var instance : ChatRepository? = null

        fun getInstance(
            apiService: ApiService
        ):ChatRepository{
            return instance ?: synchronized(this){
                ChatRepository(apiService).apply {
                    instance = this
                }
            }
        }
    }


}