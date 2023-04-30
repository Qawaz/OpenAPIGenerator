package `data_class`

data class LogEntry(
    val deviceState: DeviceState,
    val timeFired: Long,
    val values: Map<String, String>,
    val tag: String,
    val stackTrace: String,
    val type: String,
    val message: String,
)