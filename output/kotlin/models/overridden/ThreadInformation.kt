package overridden

interface IThreadInformation {
    val threadId: Int?
    val isInterrupted: Boolean?
    val threadName: String?
    val isAlive: Boolean?
    val threadTraces: List<StackTraceElem>
    val threadState: String?
    val isDaemon: Boolean?
    val priority: Int?
}

data class ThreadInformation(
    override val threadId: Int?,
    override val isInterrupted: Boolean?,
    override val threadName: String?,
    override val isAlive: Boolean?,
    override val threadTraces: List<StackTraceElem>,
    override val threadState: String?,
    override val isDaemon: Boolean?,
    override val priority: Int?,
) : IThreadInformation