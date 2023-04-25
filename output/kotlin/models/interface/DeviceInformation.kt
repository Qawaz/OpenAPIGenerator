package `interface`

interface DeviceInformation {
    val platformType: String?
    val osVersion: String?
    val osName: String?
    val infoMap: Map<String, String>
}