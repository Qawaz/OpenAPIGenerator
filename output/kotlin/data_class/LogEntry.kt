package main

data class values (

)

data class LogEntry (
	val time_fired : Int,
	val values : Values,
	val tag : String,
	val stack_trace : String,
	val type : String,
	val message : String,
)