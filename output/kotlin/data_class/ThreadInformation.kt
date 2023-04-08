package data_class

data class ThreadInformation (
	val thread_id : Int,
	val is_interrupted : Boolean,
	val thread_name : String,
	val is_alive : Boolean,
	val thread_traces : List<StackTraceElem>,
	val thread_state : String,
	val is_daemon : Boolean,
	val priority : Int,
)