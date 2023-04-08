package data_class

data class DeviceState (
	val other_threads : List<ThreadInformation>,
	val current_thread : ThreadInformation,
	val info_map : Map<String,String>,
	val is_connected_to_internet : Boolean,
	val time_zone : String,
	val storage_information : List<DeviceStorageInformation>,
)