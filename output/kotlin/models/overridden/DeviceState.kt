package `overridden`

interface IDeviceState {
    val otherThreads: List<ThreadInformation>
    val currentThread: ThreadInformation
    val infoMap: Map<String, String>
    val isConnectedToInternet: Boolean?
    val timeZone: String?
    val storageInformation: List<DeviceStorageInformation>
}

data class DeviceState(
    override val otherThreads: List<ThreadInformation>,
    override val currentThread: ThreadInformation,
    override val infoMap: Map<String, String>,
    override val isConnectedToInternet: Boolean?,
    override val timeZone: String?,
    override val storageInformation: List<DeviceStorageInformation>,
) : IDeviceState