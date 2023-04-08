package main

interface info_map {

}

interface DeviceInformation {
	val platform_type : String
	val os_version : String
	val os_name : String
	val info_map : Info_map
}

interface AppInformation {
	val package_name : String
	val version_number : Int
	val version : String
}

interface UserInformation {
	val user_email : String
	val user_id : String
	val user_name : String
}

interface DatabaseLogEntry {
	val notes : List<String>
	val log : List<LogEntry>
	val created_at : Int
	val id : String
	val state : String
	val device : DeviceInformation
	val app_information : AppInformation
	val user_information : UserInformation
}