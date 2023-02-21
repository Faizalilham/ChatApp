package android.faizal.openai.ui.screen

import android.faizal.openai.R
import android.faizal.openai.di.ChatInjection
import android.faizal.openai.model.Chat
import android.faizal.openai.model.User
import android.faizal.openai.ui.common.UiState
import android.faizal.openai.ui.components.ButtonSend
import android.faizal.openai.ui.components.ChatItem
import android.faizal.openai.ui.components.CustomTextField
import android.faizal.openai.ui.theme.OpenAITheme
import android.faizal.openai.view_model.HomeViewModel
import android.faizal.openai.view_model.ViewModelFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel : HomeViewModel = viewModel(factory = ViewModelFactory(ChatInjection.provideChatRepository()))
) {

    var inputText by remember { mutableStateOf("")}
    val id by remember { mutableStateOf(0)}
    val scope = rememberCoroutineScope()
    val lazyState = rememberLazyListState()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {  uiState ->
        when(uiState){
            is  UiState.Loading -> {
                viewModel.getAllChat()
            }
            is UiState.Success -> {
                HomeContent(
                    id = id + 2,
                    chats = uiState.data,
                    addChat = { id,text,user ->
                        viewModel.addChat(id,text,user)
                        inputText = ""
                        scope.launch {
                            lazyState.animateScrollToItem(uiState.data.size -1)
                        }
                    },
                    inputText = inputText,
                    onValueChange = { input ->
                        inputText = input
                    },
                    onClearChange = {
                        inputText = ""
                    }
                )
            }
            is UiState.Error -> {}
        }
    }

}

@Preview(showBackground = true,device = Devices.PIXEL_4)
@Composable
fun HomeContentPreview() {
    OpenAITheme {
        HomeContent(id = 0,chats = mutableListOf<Chat>(),
            addChat = {_,_,_ -> },
            inputText = "",
            onValueChange = {_ -> },
            onClearChange = {})
    }
}

@Composable
fun HomeContent(
    modifier : Modifier = Modifier,
    id : Int,
    chats : MutableList<Chat>,
    addChat : (id : Int,text : String,user : User) -> Unit,
    inputText : String,
    onValueChange: (String) -> Unit,
    onClearChange : () -> Unit
) {

    Box {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "background",
            modifier = Modifier
                .width(450.dp)
                .height(1200.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ListChatSection(
                modifier = Modifier.weight(1f,fill = true),
                chats = chats)
            InputChatSection(
                value = inputText,
                onValueChange = onValueChange,
                onClearChange = onClearChange
            ) {

                addChat(
                    id,
                    inputText,
                    User.Human,
                )
            }
        }
    }

}

@Composable
fun ListChatSection(
    modifier : Modifier = Modifier,
    chats : MutableList<Chat>,
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = state,
        modifier = modifier){

        items(chats){ chat ->
            when(chat.user){
                User.Human -> {
                    ChatItem(
                        chatUser = chat.text,
                        chatBot = "",
                        visibilityBot = false,
                        visibilityUser = true)
                }
                User.Bot -> {
                    ChatItem(
                        chatUser = "",
                        chatBot = chat.text,
                        visibilityBot = true,
                        visibilityUser = false)
                }
            }
        }

    }

}

@Composable
fun InputChatSection(
    modifier : Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    onClearChange : () -> Unit,
    onSendChange : () -> Unit
) {

    Row(
        modifier = modifier.height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){

        CustomTextField(
            modifier = Modifier.weight(1f,fill = true),
            value = value,
            onValueChange = onValueChange,
        ) {
           onClearChange()
        }

        ButtonSend(
            modifier = Modifier.size(80.dp)
        ) {
           onSendChange()
        }

    }

}