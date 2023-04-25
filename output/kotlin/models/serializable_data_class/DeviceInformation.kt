package data_class

@Serializable
data class DeviceInformation(
    @SerialName("platform_type") val platformType: String,
    @SerialName("os_version") val osVersion: String?,
    @SerialName("os_name") val osName: String,
    @SerialName("info_map") val infoMap: Map<String, String>,
)