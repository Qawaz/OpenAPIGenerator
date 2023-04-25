package overridden

interface IDatabaseLogEntry {
    val notes: List<String>
    val log: List<LogEntry>
    val createdAt: Int?
    val id: String?
    val state: String
    val device: DeviceInformation
    val appInformation: AppInformation
    val userInformation: UserInformation
}

data class DatabaseLogEntry(
    override val notes: List<String>,
    override val log: List<LogEntry>,
    override val createdAt: Int?,
    override val id: String?,
    override val state: String,
    override val device: DeviceInformation,
    override val appInformation: AppInformation,
    override val userInformation: UserInformation,
) : IDatabaseLogEntry