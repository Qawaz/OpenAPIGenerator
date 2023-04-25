package overridden_serializable

interface IDatabaseLogEntry {
    val notes: List<String>
    val log: List<LogEntry>
    val createdAt: Int?
    val id: String?
    val state: String?
    val device: DeviceInformation
    val appInformation: AppInformation
    val userInformation: UserInformation
}

@Serializable
data class DatabaseLogEntry(
    @SerialName("notes") override val notes: List<String>,
    @SerialName("log") override val log: List<LogEntry>,
    @SerialName("created_at") override val createdAt: Int?,
    @SerialName("id") override val id: String?,
    @SerialName("state") override val state: String?,
    @SerialName("device") override val device: DeviceInformation,
    @SerialName("app_information") override val appInformation: AppInformation,
    @SerialName("user_information") override val userInformation: UserInformation,
) : IDatabaseLogEntry