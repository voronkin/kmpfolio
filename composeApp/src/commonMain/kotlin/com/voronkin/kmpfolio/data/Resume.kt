package com.voronkin.kmpfolio.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Resume(
    @SerialName("personal_information") val personalInformation: PersonalInformation,
    @SerialName("job_preferences") val jobPreferences: JobPreferences,
    @SerialName("work_experience") val workExperience: List<WorkExperience>,
    val education: Education,
    val skills: Skills,
    @SerialName("additional_info") val additionalInfo: AdditionalInfo,
)

@Serializable
data class PersonalInformation(
    val name: String,
    val age: Int,
    @SerialName("birth_date") val birthDate: String,
    val location: String,
    val citizenship: String,
    val contact: Contact,
)

@Serializable
data class Contact(
    val phone: String,
    val email: String,
)

@Serializable
data class JobPreferences(
    @SerialName("desired_position") val desiredPosition: String,
    val specializations: List<String>,
    @SerialName("employment_type") val employmentType: List<String>,
    val schedule: List<String>,
    val relocation: String,
    @SerialName("business_trips") val businessTrips: String,
)

@Serializable
data class WorkExperience(
    val period: String,
    val duration: String,
    val company: String,
    val position: String,
    val responsibilities: List<String>,
    val technologies: List<String> = emptyList(),
)

@Serializable
data class Education(
    val level: String,
    val year: Int,
    val institution: String,
    val faculty: String,
    val specialization: String,
)

@Serializable
data class Skills(
    val languages: Map<String, String>,
    @SerialName("technical_skills") val technicalSkills: List<String>,
)

@Serializable
data class AdditionalInfo(
    @SerialName("about_me") val aboutMe: String,
)
