package `interface`

interface ThreadInformation {
	val threadId: Int
	val isInterrupted: Boolean
	val threadName: String
	val isAlive: Boolean
	val threadTraces: List<StackTraceElem>
	val threadState: String
	val isDaemon: Boolean
	val priority: Int
}