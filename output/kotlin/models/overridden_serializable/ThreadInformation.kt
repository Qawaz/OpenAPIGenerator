package `overridden_serializable`

interface IThreadInformation {
    val threadId: Int
    val isInterrupted: Boolean
    val threadName: String
    val isAlive: Boolean
    val threadTraces: List<StackTraceElem>
    val threadState: String
    val isDaemon: Boolean
    val priority: Int
}

@Serializable
data class ThreadInformation(
    @SerialName("thread_id") override val threadId: Int,
    @SerialName("is_interrupted") override val isInterrupted: Boolean,
    @SerialName("thread_name") override val threadName: String,
    @SerialName("is_alive") override val isAlive: Boolean,
    @SerialName("thread_traces") override val threadTraces: List<StackTraceElem>,
    @SerialName("thread_state") override val threadState: String,
    @SerialName("is_daemon") override val isDaemon: Boolean,
    @SerialName("priority") override val priority: Int,
) : IThreadInformation