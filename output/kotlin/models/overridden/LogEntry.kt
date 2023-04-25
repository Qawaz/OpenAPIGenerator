package `overridden`

interface ILogEntry {
    val deviceState: DeviceState
    val timeFired: Int
    val values: Map<String, String>
    val tag: String
    val stackTrace: String
    val type: String
    val message: String
}

data class LogEntry(
    override val deviceState: DeviceState,
    override val timeFired: Int,
    override val values: Map<String, String>,
    override val tag: String,
    override val stackTrace: String,
    override val type: String,
    override val message: String,
) : ILogEntry