package com.voronkin.kmpfolio.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voronkin.kmpfolio.data.Resume

@Composable
fun ResumeBentoGrid(
    resume: Resume,
    modifier: Modifier = Modifier,
) {
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
            Text("\uD83D\uDC64", fontSize = 60.sp, modifier = Modifier.offset(x = 10.dp, y = 10.dp))
        }

        // Skills card
        BentoCard(
            modifier = Modifier.weight(1f).height(180.dp),
            title = "Skills",
            subtitle = resume.skills.technicalSkills.take(8).joinToString(", "),
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

        // Work experience cards
        for (job in resume.workExperience) {
            BentoCard(
                modifier = Modifier.fillMaxWidth().height(140.dp),
                title = job.position,
                subtitle = "${job.company}\n${job.period}",
                backgroundGradient = listOf(Color(0xFF11998e), Color(0xFF38ef7d)),
            )
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
