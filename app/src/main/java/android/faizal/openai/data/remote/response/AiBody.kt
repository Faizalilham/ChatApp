package android.faizal.openai.data.remote.response

data class AiBody(
    val model : String = "text-davinci-003",
    val prompt : String,
    val max_tokens : Int = 4000,
    val temperature : Int = 0,
)
