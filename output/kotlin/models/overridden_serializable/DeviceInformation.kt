package overridden_serializable

interface IDeviceInformation {
    val platformType: String
    val osVersion: String?
    val osName: String
    val infoMap: Map<String, String>
}

@Serializable
data class DeviceInformation(
    @SerialName("platform_type") override val platformType: String,
    @SerialName("os_version") override val osVersion: String?,
    @SerialName("os_name") override val osName: String,
    @SerialName("info_map") override val infoMap: Map<String, String>,
) : IDeviceInformation