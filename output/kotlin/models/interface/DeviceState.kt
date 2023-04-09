package `interface`

interface DeviceState {
    val otherThreads: List<ThreadInformation>
    val currentThread: ThreadInformation
    val infoMap: Map<String, String>
    val isConnectedToInternet: Boolean
    val timeZone: String
    val storageInformation: List<DeviceStorageInformation>
}