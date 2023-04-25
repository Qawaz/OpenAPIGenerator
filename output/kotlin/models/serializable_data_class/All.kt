@Serializable
data class Success(
    @SerialName("success") val success: Boolean?,
)

@Serializable
data class Error(
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?,
)

@Serializable
data class AppInformation(
    @SerialName("package_name") val packageName: String?,
    @SerialName("version_number") val versionNumber: Int?,
    @SerialName("version") val version: String?,
)

@Serializable
data class UserInformation(
    @SerialName("user_email") val userEmail: String?,
    @SerialName("user_id") val userId: String?,
    @SerialName("user_name") val userName: String?,
)

@Serializable
data class DeviceInformation(
    @SerialName("platform_type") val platformType: String?,
    @SerialName("os_version") val osVersion: String?,
    @SerialName("os_name") val osName: String?,
    @SerialName("info_map") val infoMap: Map<String, String>,
)

@Serializable
data class DisplayInformation(
    @SerialName("orientation") val orientation: String?,
    @SerialName("top") val top: Int?,
    @SerialName("left") val left: Int?,
    @SerialName("bottom") val bottom: Int?,
    @SerialName("right") val right: Int?,
    @SerialName("display_name") val displayName: String?,
)

@Serializable
data class DeviceStorageInformation(
    @SerialName("storage_name") val storageName: String?,
    @SerialName("total_space") val totalSpace: Int?,
    @SerialName("free_space") val freeSpace: Int?,
)

@Serializable
data class StackTraceElem(
    @SerialName("is_native_method") val isNativeMethod: Boolean?,
    @SerialName("method_name") val methodName: String?,
    @SerialName("file_name") val fileName: String?,
    @SerialName("line_number") val lineNumber: Int?,
    @SerialName("class_name") val className: String?,
)

@Serializable
data class ThreadInformation(
    @SerialName("thread_id") val threadId: Int?,
    @SerialName("is_interrupted") val isInterrupted: Boolean?,
    @SerialName("thread_name") val threadName: String?,
    @SerialName("is_alive") val isAlive: Boolean?,
    @SerialName("thread_traces") val threadTraces: List<StackTraceElem>,
    @SerialName("thread_state") val threadState: String?,
    @SerialName("is_daemon") val isDaemon: Boolean?,
    @SerialName("priority") val priority: Int?,
)

@Serializable
data class DeviceState(
    @SerialName("other_threads") val otherThreads: List<ThreadInformation>,
    @SerialName("current_thread") val currentThread: ThreadInformation,
    @SerialName("info_map") val infoMap: Map<String, String>,
    @SerialName("is_connected_to_internet") val isConnectedToInternet: Boolean?,
    @SerialName("time_zone") val timeZone: String?,
    @SerialName("storage_information") val storageInformation: List<DeviceStorageInformation>,
)

@Serializable
data class LogEntry(
    @SerialName("device_state") val deviceState: DeviceState,
    @SerialName("time_fired") val timeFired: Int?,
    @SerialName("values") val values: Map<String, String>,
    @SerialName("tag") val tag: String?,
    @SerialName("stack_trace") val stackTrace: String?,
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?,
)

@Serializable
data class DatabaseLogEntry(
    @SerialName("notes") val notes: List<String>,
    @SerialName("log") val log: List<LogEntry>,
    @SerialName("created_at") val createdAt: Int?,
    @SerialName("id") val id: String?,
    @SerialName("state") val state: String?,
    @SerialName("device") val device: DeviceInformation,
    @SerialName("app_information") val appInformation: AppInformation,
    @SerialName("user_information") val userInformation: UserInformation,
)