package com.voronkin.kmpfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.voronkin.kmpfolio.data.Resume
import com.voronkin.kmpfolio.screen.DetailScreen

@Composable
fun ResumeBentoGrid(
    resume: Resume,
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.currentOrThrow
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = 2,
    ) {
        // Personal info card — full width
        BentoCard(
            modifier = Modifier.fillMaxWidth().height(160.dp),
            title = resume.personalInformation.name,
            subtitle = "${resume.personalInformation.location} | ${resume.personalInformation.contact.email}",
            backgroundGradient = listOf(Color(0xFF434343), Color(0xFF000000)),
        ) {
            AsyncImage(
                model = "https://raw.githubusercontent.com/voronkin/kmpfolio/refs/heads/main/me.jpg",
                contentDescription = "Project Screenshot",
                modifier = Modifier
                    .size(80.dp, 80.dp) // Размер картинки внутри карточки
                    .offset(x = 10.dp, y = 10.dp) // Смещаем в угол, как в Bento-стиле
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop, // Обрезает картинку, чтобы заполнила область
                // Индикатор загрузки (пока качается картинка)
                placeholder = ColorPainter(Color.Gray.copy(alpha = 0.3f)),
                error = ColorPainter(Color.Red.copy(alpha = 0.1f))
            )
            //Text("\uD83D\uDC64", fontSize = 60.sp, modifier = Modifier.offset(x = 10.dp, y = 10.dp))
        }

        // Skills card
        BentoCard(
            modifier = Modifier.weight(1f).height(180.dp),
            title = "Skills",
            subtitle = resume.skills.technicalSkills.take(50).joinToString(", "),
            onClick = { navigator.push(DetailScreen()) },
            backgroundGradient = listOf(Color(0xFF00c6ff), Color(0xFF0072ff)),
        )

        // Languages card
        BentoCard(
            modifier = Modifier.weight(1f).height(180.dp),
            title = "Languages",
            subtitle = resume.skills.languages.entries.joinToString("\n") { "${it.key}: ${it.value}" },
            backgroundGradient = listOf(Color(0xFFf953c6), Color(0xFFb91d73)),
        )

        // Education card — full width
        BentoCard(
            modifier = Modifier.fillMaxWidth().height(140.dp),
            title = "Education",
            subtitle = "${resume.education.institution}\n${resume.education.faculty} (${resume.education.year})",
            backgroundGradient = listOf(Color(0xFF4e54c8), Color(0xFF8f94fb)),
        )


        // Work experience cards\
        Text(
            "Work experience",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        )
        for (job in resume.workExperience) {
            BentoCard(

                title = job.position,

                backgroundGradient = listOf(Color(0xFF11998e), Color(0xFF38ef7d)),
            )
            {

                Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = job.position,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${job.company}\n${job.period}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 2.dp)
                        .fillMaxWidth(1.0f) // Линия только на половину экрана
                        .height(2.dp)
                        .background(
                            color = Color.White
                        )
                )

                for (resp in job.responsibilities) {
                    Text(
                        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                        text = "- $resp",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                }
                    }

            }
        }

        // About me card
        BentoCard(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            title = "About",
            subtitle = resume.additionalInfo.aboutMe,
            backgroundGradient = listOf(Color(0xFFfc4a1a), Color(0xFFf7b733)),
        )
    }
}
