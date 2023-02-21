package android.faizal.openai.view_model

import android.faizal.openai.domain.model.Chat
import android.faizal.openai.domain.model.User
import android.faizal.openai.domain.usecase.ChatUseCase
import android.faizal.openai.repository.ChatRepository
import android.faizal.openai.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val chatUseCase: ChatUseCase
) :ViewModel(){

    private var _uiState : MutableStateFlow<UiState<MutableList<Chat>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<MutableList<Chat>>> = _uiState

    fun addChat(id : Int,text : String,user : User){
      CoroutineScope(Dispatchers.IO).launch {
           chatUseCase.addList(id,text,user)

       }
    }

    fun getAllChat(){
        viewModelScope.launch {
            chatUseCase.getAllChat().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect{ chats ->
               _uiState.value = UiState.Success(chats)
            }
        }
    }
}