package android.faizal.openai.ui.components

import android.faizal.openai.ui.theme.Green
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonSend(
    modifier : Modifier = Modifier,
    onClick : () -> Unit
) {

    Button(
        modifier = modifier.padding(10.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
        onClick = { onClick() }
    ) {

        Icon(
            imageVector = Icons.Filled.Send,
            tint = Color.White,
            contentDescription = "Send")
    }

}