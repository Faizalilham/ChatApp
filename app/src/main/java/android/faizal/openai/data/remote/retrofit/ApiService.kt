package android.faizal.openai.data.remote.retrofit

import android.faizal.openai.data.remote.response.AiBody
import android.faizal.openai.data.remote.response.AiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("completions")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer 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"
    )
    suspend fun getResponseAI(
        @Body body : AiBody
    ):AiResponse
}