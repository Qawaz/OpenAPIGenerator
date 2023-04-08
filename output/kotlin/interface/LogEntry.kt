package `interface`

interface LogEntry {
	val device_state : DeviceState
	val time_fired : Int
	val values : Map<String,String>
	val tag : String
	val stack_trace : String
	val type : String
	val message : String
}