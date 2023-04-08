package data_class

data class LogEntry (
	val deviceState : DeviceState,
	val timeFired : Int,
	val values : Map<String,String>,
	val tag : String,
	val stackTrace : String,
	val type : String,
	val message : String,
)