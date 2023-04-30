package `overridden_serializable`

interface ILogEntry {
    val deviceState: DeviceState
    val timeFired: Long
    val values: Map<String, String>
    val tag: String
    val stackTrace: String
    val type: String
    val message: String
}

@Serializable
data class LogEntry(
    @SerialName("device_state") override val deviceState: DeviceState,
    @SerialName("time_fired") override val timeFired: Long,
    @SerialName("values") override val values: Map<String, String>,
    @SerialName("tag") override val tag: String,
    @SerialName("stack_trace") override val stackTrace: String,
    @SerialName("type") override val type: String,
    @SerialName("message") override val message: String,
) : ILogEntry