package com.voronkin.kmpfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
                contentDescription = "My avatar",
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .offset(x = 10.dp, y = 10.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,

                placeholder = ColorPainter(Color.Gray.copy(alpha = 0.3f)),
                error = ColorPainter(Color.Red.copy(alpha = 0.1f))
            )
            //Text("\uD83D\uDC64", fontSize = 60.sp, modifier = Modifier.offset(x = 10.dp, y = 10.dp))
        }

        // Skills card
        BentoCard(
            modifier = Modifier.weight(1f),
            title = "",
            subtitle = "",
            backgroundGradient = listOf(Color(0xFF00c6ff), Color(0xFF0072ff)),
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    "Skills",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                for (skill in resume.skills.technicalSkills) {
                    Text(
                        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                        text = "- $skill",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { navigator.push(DetailScreen()) }) {
                    Text("Go to showroom")
                }
            }

        }

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
                        .fillMaxWidth(1.0f)
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

        BentoCard(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            title = "About",
            subtitle = resume.additionalInfo.aboutMe,
            backgroundGradient = listOf(Color(0xFFfc4a1a), Color(0xFFf7b733)),
        )
    }
}
