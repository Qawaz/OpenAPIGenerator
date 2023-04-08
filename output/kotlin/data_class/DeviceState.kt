package main

data class info_map (

)

data class DeviceState (
	val other_threads : List<ThreadInformation>,
	val info_map : Info_map,
	val is_connected_to_internet : Boolean,
	val time_zone : String,
	val storage_information : List<DeviceStorageInformation>,
)