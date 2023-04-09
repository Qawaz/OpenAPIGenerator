package overridden_serializable

interface IDeviceStorageInformation {
    val storageName: String
    val totalSpace: Int
    val freeSpace: Int
}

@Serializable
data class DeviceStorageInformation(
    @SerialName("storage_name") override val storageName: String,
    @SerialName("total_space") override val totalSpace: Int,
    @SerialName("free_space") override val freeSpace: Int,
) : IDeviceStorageInformation