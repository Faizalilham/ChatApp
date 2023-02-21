package android.faizal.openai.view_model

import android.faizal.openai.data.remote.response.AiBody
import android.faizal.openai.model.Chat
import android.faizal.openai.model.User
import android.faizal.openai.repository.ChatRepository
import android.faizal.openai.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val chatRepository : ChatRepository
) :ViewModel(){

    private var _uiState : MutableStateFlow<UiState<MutableList<Chat>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<MutableList<Chat>>> = _uiState

    fun addChat(id : Int,text : String,user : User){
      CoroutineScope(Dispatchers.IO).launch {
           chatRepository.addList(id,text,user)

       }
    }

    fun getAllChat(){
        viewModelScope.launch {
            chatRepository.getAllChat().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect{ chats ->
               _uiState.value = UiState.Success(chats)
            }
        }
    }
}