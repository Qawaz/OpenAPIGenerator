package overridden_serializable

interface IDeviceState {
    val otherThreads: List<ThreadInformation>
    val currentThread: ThreadInformation
    val infoMap: Map<String, String>
    val isConnectedToInternet: Boolean?
    val timeZone: String?
    val storageInformation: List<DeviceStorageInformation>
}

@Serializable
data class DeviceState(
    @SerialName("other_threads") override val otherThreads: List<ThreadInformation>,
    @SerialName("current_thread") override val currentThread: ThreadInformation,
    @SerialName("info_map") override val infoMap: Map<String, String>,
    @SerialName("is_connected_to_internet") override val isConnectedToInternet: Boolean?,
    @SerialName("time_zone") override val timeZone: String?,
    @SerialName("storage_information") override val storageInformation: List<DeviceStorageInformation>,
) : IDeviceState