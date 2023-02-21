package android.faizal.openai.ui.components

import android.faizal.openai.R
import android.faizal.openai.ui.theme.OpenAITheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    OpenAITheme {
        CustomTextField(value = "", onValueChange = {} ) {

        }
    }
}

@Composable
fun CustomTextField(
    modifier : Modifier = Modifier,
    value : String,
    onValueChange :(String) -> Unit,
    onValueClear : () -> Unit
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(25.dp),
        placeholder = {
            Text(stringResource(id = R.string.placeholder),color = Color.White)
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.leadingIconTextField),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onValueClear() }
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.EmojiEmotions,
                contentDescription = stringResource(id = R.string.trailingIconTextField),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { }
            )
        },
        keyboardActions = KeyboardActions(KeyboardActions.Default.onNext),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )

}