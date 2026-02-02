package com.voronkin.kmpfolio.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.voronkin.kmpfolio.ui.components.ResumeBentoGrid

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel { HomeScreenModel() }
        val state by model.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when (val current = state) {
                is ResumeState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }

                is ResumeState.Error -> {
                    Text(
                        text = "Error: ${current.message}",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                is ResumeState.Success -> {
                    val resume = current.resume
                    Text(
                        resume.personalInformation.name,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
                    )
                    Text(
                        resume.jobPreferences.desiredPosition,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                    ResumeBentoGrid(resume = resume)
                }
            }
        }
    }
}
