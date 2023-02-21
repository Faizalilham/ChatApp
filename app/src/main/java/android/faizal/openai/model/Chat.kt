package android.faizal.openai.model

data class Chat(
    val id : Int,
    val text : String,
    val user : User  = User.Human
)

sealed class User{
    object Human : User()
    object Bot : User()
}