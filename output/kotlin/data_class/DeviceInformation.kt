package data_class

data class DeviceInformation (
	val platform_type : String,
	val os_version : String,
	val os_name : String,
	val info_map : Map<String,String>,
)