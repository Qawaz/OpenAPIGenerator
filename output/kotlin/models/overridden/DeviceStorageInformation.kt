package overridden

interface IDeviceStorageInformation {
    val storageName: String
    val totalSpace: Int
    val freeSpace: Int
}

data class DeviceStorageInformation(
    override val storageName: String,
    override val totalSpace: Int,
    override val freeSpace: Int,
) : IDeviceStorageInformation