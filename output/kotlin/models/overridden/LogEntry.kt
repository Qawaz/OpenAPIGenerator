package `overridden`

interface ILogEntry {
    val deviceState: DeviceState
    val timeFired: Long
    val values: Map<String, String>
    val tag: String
    val stackTrace: String
    val type: String
    val message: String
}

data class LogEntry(
    override val deviceState: DeviceState,
    override val timeFired: Long,
    override val values: Map<String, String>,
    override val tag: String,
    override val stackTrace: String,
    override val type: String,
    override val message: String,
) : ILogEntry