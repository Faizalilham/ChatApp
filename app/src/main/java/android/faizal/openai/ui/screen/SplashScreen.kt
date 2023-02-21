package android.faizal.openai.ui.screen


import android.faizal.openai.R
import android.faizal.openai.ui.navigation.Screen
import android.faizal.openai.ui.theme.OpenAITheme
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
   OpenAITheme {
       SplashScreen()
   }
}

@Composable
fun AnimatedSplashScreen(
    navController : NavController
) {

    var isAnimated by remember {mutableStateOf(false)}
    val animated = animateFloatAsState(
        targetValue = if(isAnimated) 1f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )

    LaunchedEffect(key1 = true){
        isAnimated = true
        delay(3000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    SplashScreen(alpha = animated.value)

}


@Composable
fun SplashScreen(
    modifier : Modifier = Modifier,
    alpha : Float = 0f
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            RoundImage(alpha = alpha)

            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    color =  Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp)
                    .alpha(alpha),
                textAlign = TextAlign.Center

            )

        }
    }

}

@Composable
fun RoundImage(
    alpha : Float
) {

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.image_splash),
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .shadow(10.dp, shape = CircleShape)
            .alpha(alpha),
        contentScale = ContentScale.Crop,
    )

}