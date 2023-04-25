package data_class

@Serializable
data class DeviceStorageInformation(
    @SerialName("storage_name") val storageName: String?,
    @SerialName("total_space") val totalSpace: Int?,
    @SerialName("free_space") val freeSpace: Int?,
)