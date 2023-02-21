package android.faizal.openai.ui.components

import android.faizal.openai.ui.theme.Gray
import android.faizal.openai.ui.theme.Green
import android.faizal.openai.ui.theme.OpenAITheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ChatItemPreview() {
    OpenAITheme {
        ChatItem(chatUser = "Hello World",chatBot = "Hello World", visibilityBot = true, visibilityUser = false)
    }
}

@Composable
fun ChatItem(
    modifier : Modifier = Modifier,
    chatUser : String,
    chatBot : String,
    visibilityBot : Boolean,
    visibilityUser : Boolean
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if(visibilityBot) Arrangement.Start else Arrangement.End
    ) {
        ChatSection(
            modifier = Modifier.padding(start = 10.dp,top = 10.dp, bottom = 10.dp, end = 30.dp),
            text = chatBot,
            shape = RoundedCornerShape(
                bottomStart = 12.dp,
                bottomEnd = 12.dp,
                topEnd = 12.dp
            ),
            color = Gray,
            visibility = visibilityBot
        )

        ChatSection(
            modifier = Modifier.padding(start = 30.dp,end = 10.dp,top = 10.dp, bottom = 10.dp),
            text = chatUser,
            shape = RoundedCornerShape(
                bottomStart = 12.dp,
                bottomEnd = 12.dp,
                topStart = 12.dp
            ),
            color = Green,
            visibility = visibilityUser,
        )

    }


}

@Composable
fun ChatSection(
    modifier : Modifier = Modifier,
    text : String,
    shape : Shape,
    color : Color,
    visibility : Boolean
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = visibility,
        enter = fadeIn() + slideInHorizontally()
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier
                .clip(shape = shape)
                .background(color = color)
                .padding(10.dp),
        )
    }

}