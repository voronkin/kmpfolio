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
            "https://gist.githubusercontent.com/voronkin/ca35f901d446b5a17256290279c3ee95/raw/c845e9273f939d91f581fc931821509e71993102/resume.json"
    }
}
