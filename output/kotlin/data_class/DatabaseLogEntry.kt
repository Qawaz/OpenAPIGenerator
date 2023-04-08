package main

data class info_map (

)

data class DeviceInformation (
	val platform_type : String,
	val os_version : String,
	val os_name : String,
	val info_map : Info_map,
)

data class AppInformation (
	val package_name : String,
	val version_number : Int,
	val version : String,
)

data class UserInformation (
	val user_email : String,
	val user_id : String,
	val user_name : String,
)

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