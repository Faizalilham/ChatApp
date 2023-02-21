package android.faizal.openai.view_model

import android.faizal.openai.repository.ChatRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class ViewModelFactory(private val chatRepository : ChatRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(chatRepository) as T
        }

        throw IllegalArgumentException("Unknow view model class ${modelClass.name}")
    }
}