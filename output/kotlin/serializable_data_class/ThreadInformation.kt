package data_class

@Serializable
data class ThreadInformation(
	@SerialName("thread_id") val threadId: Int,
	@SerialName("is_interrupted") val isInterrupted: Boolean,
	@SerialName("thread_name") val threadName: String,
	@SerialName("is_alive") val isAlive: Boolean,
	@SerialName("thread_traces") val threadTraces: List<StackTraceElem>,
	@SerialName("thread_state") val threadState: String,
	@SerialName("is_daemon") val isDaemon: Boolean,
	@SerialName("priority") val priority: Int,
)