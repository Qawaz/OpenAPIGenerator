package overridden

interface IDeviceInformation {
    val platformType: String
    val osVersion: String?
    val osName: String
    val infoMap: Map<String, String>
}

data class DeviceInformation(
    override val platformType: String,
    override val osVersion: String?,
    override val osName: String,
    override val infoMap: Map<String, String>,
) : IDeviceInformation