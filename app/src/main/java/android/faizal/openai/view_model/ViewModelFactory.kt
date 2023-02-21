package android.faizal.openai.view_model

import android.faizal.openai.di.ChatInjection
import android.faizal.openai.domain.usecase.ChatUseCase
import android.faizal.openai.repository.ChatRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class ViewModelFactory(private val chatUseCase: ChatUseCase):ViewModelProvider.NewInstanceFactory() {


    companion object{

        @Volatile
        private var instance : ViewModelFactory?  = null

        fun getInstance():ViewModelFactory{
            return instance ?: synchronized(this){
                instance ?: ViewModelFactory(ChatInjection.provideChatUseCase())
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(chatUseCase) as T
        }

        throw IllegalArgumentException("Unknown view model class ${modelClass.name}")
    }
}