package data_class

@Serializable
data class DeviceState (
	@SerialName("other_threads") val otherThreads : List<ThreadInformation>,
	@SerialName("current_thread") val currentThread : ThreadInformation,
	@SerialName("info_map") val infoMap : Map<String,String>,
	@SerialName("is_connected_to_internet") val isConnectedToInternet : Boolean,
	@SerialName("time_zone") val timeZone : String,
	@SerialName("storage_information") val storageInformation : List<DeviceStorageInformation>,
)