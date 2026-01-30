package com.voronkin.kmpfolio.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.voronkin.kmpfolio.ui.components.BentoCard

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            Text(
                "My Stack",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                maxItemsInEachRow = 2 // На мобилках будет 2 в ряд или 1
            ) {
                // Большая карточка (занимает всю ширину)
                BentoCard(
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    title = "Android & KMP",
                    subtitle = "10+ years of native development",
                    backgroundGradient = listOf(Color(0xFF434343), Color(0xFF000000))
                ) {
                    // Здесь может быть твоя кастомная иконка или Canvas-рисунок
                    Text("🤖", fontSize = 60.sp, modifier = Modifier.offset(x = 10.dp, y = 10.dp))
                }

                // Маленькая карточка 1
                BentoCard(
                    modifier = Modifier.weight(1f).height(140.dp),
                    title = "LLM",
                    subtitle = "AI Agents",
                    backgroundGradient = listOf(Color(0xFF00c6ff), Color(0xFF0072ff)),
                    onClick = { navigator.push(DetailScreen()) }
                )

                // Маленькая карточка 2
                BentoCard(
                    modifier = Modifier.weight(1f).height(140.dp),
                    title = "Clean Architecture",
                    backgroundGradient = listOf(Color(0xFFf953c6), Color(0xFFb91d73))

                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { navigator.pop() }) {
                    Text("Go back")
                }
            }
        }
    }
}
