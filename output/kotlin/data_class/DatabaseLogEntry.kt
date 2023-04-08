package data_class

data class DatabaseLogEntry (
	val notes : List<String>,
	val log : List<LogEntry>,
	val created_at : Int,
	val id : String,
	val state : String,
	val device : DeviceInformation,
	val app_information : AppInformation,
	val user_information : UserInformation,
)