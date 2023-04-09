package data_class

data class DatabaseLogEntry(
    val notes: List<String>,
    val log: List<LogEntry>,
    val createdAt: Int,
    val id: String,
    val state: String,
    val device: DeviceInformation,
    val appInformation: AppInformation,
    val userInformation: UserInformation,
)