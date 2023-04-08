package data_class

@Serializable
data class LogEntry(
	@SerialName("device_state") val deviceState: DeviceState,
	@SerialName("time_fired") val timeFired: Int,
	@SerialName("values") val values: Map<String, String>,
	@SerialName("tag") val tag: String,
	@SerialName("stack_trace") val stackTrace: String,
	@SerialName("type") val type: String,
	@SerialName("message") val message: String,
)