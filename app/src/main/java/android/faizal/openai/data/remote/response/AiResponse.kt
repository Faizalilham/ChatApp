package android.faizal.openai.data.remote.response

import com.google.gson.annotations.SerializedName

data class AiResponse(
    val id : String,
    @SerializedName("object")
    val objected : String,
    val created : Int,
    val model : String,
    val choices : MutableList<Choices>,
    val usage : Usage
)

data class Choices(
    val text : String,
    val index : Int,
    val logprobs : Any?,
    val finish_reason : String
)

data class Usage(
    val prompt_tokens : Int,
    val completion_tokens : Int,
    val total_token : Int
)


