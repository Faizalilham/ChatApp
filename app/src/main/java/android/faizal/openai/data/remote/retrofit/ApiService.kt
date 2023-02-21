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