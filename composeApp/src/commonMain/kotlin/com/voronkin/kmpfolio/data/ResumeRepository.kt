package com.voronkin.kmpfolio.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class ResumeRepository {

    private val client = HttpClient()

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun loadResume(): Resume {
        val text = client.get(RESUME_URL).bodyAsText()
        return json.decodeFromString<Resume>(text)
    }

    companion object {
        private const val RESUME_URL =
            "https://gist.githubusercontent.com/voronkin/ca35f901d446b5a17256290279c3ee95/raw/d203da078eb2cb1c295a25ccf1a8b19aa01acffc/resume.json?cb=12345"
    }
}
