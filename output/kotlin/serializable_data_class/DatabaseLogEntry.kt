package data_class

@Serializable
data class DatabaseLogEntry(
    @SerialName("notes") val notes: List<String>,
    @SerialName("log") val log: List<LogEntry>,
    @SerialName("created_at") val createdAt: Int,
    @SerialName("id") val id: String,
    @SerialName("state") val state: String,
    @SerialName("device") val device: DeviceInformation,
    @SerialName("app_information") val appInformation: AppInformation,
    @SerialName("user_information") val userInformation: UserInformation,
)