package main

interface info_map {

}

interface DeviceState {
	val other_threads : List<ThreadInformation>
	val info_map : Info_map
	val is_connected_to_internet : Boolean
	val time_zone : String
	val storage_information : List<DeviceStorageInformation>
}