interface ISuccess {
    val success: Boolean
}

@Serializable
data class Success(
    @SerialName("success") override val success: Boolean,
) : ISuccess

@Serializable
data class Error(
    @SerialName("type") override val type: String,
    @SerialName("message") override val message: String,
) : IError

@Serializable
data class AppInformation(
    @SerialName("package_name") override val packageName: String,
    @SerialName("version_number") override val versionNumber: Int,
    @SerialName("version") override val version: String,
) : IAppInformation

@Serializable
data class UserInformation(
    @SerialName("user_email") override val userEmail: String,
    @SerialName("user_id") override val userId: String,
    @SerialName("user_name") override val userName: String,
) : IUserInformation

@Serializable
data class DeviceInformation(
    @SerialName("platform_type") override val platformType: String,
    @SerialName("os_version") override val osVersion: String,
    @SerialName("os_name") override val osName: String,
    @SerialName("info_map") override val infoMap: Map<String, String>,
) : IDeviceInformation

@Serializable
data class DisplayInformation(
    @SerialName("orientation") override val orientation: String,
    @SerialName("top") override val top: Int,
    @SerialName("left") override val left: Int,
    @SerialName("bottom") override val bottom: Int,
    @SerialName("right") override val right: Int,
    @SerialName("display_name") override val displayName: String,
) : IDisplayInformation

@Serializable
data class DeviceStorageInformation(
    @SerialName("storage_name") override val storageName: String,
    @SerialName("total_space") override val totalSpace: Int,
    @SerialName("free_space") override val freeSpace: Int,
) : IDeviceStorageInformation

@Serializable
data class StackTraceElem(
    @SerialName("is_native_method") override val isNativeMethod: Boolean,
    @SerialName("method_name") override val methodName: String,
    @SerialName("file_name") override val fileName: String,
    @SerialName("line_number") override val lineNumber: Int,
    @SerialName("class_name") override val className: String,
) : IStackTraceElem

@Serializable
data class ThreadInformation(
    @SerialName("thread_id") override val threadId: Int,
    @SerialName("is_interrupted") override val isInterrupted: Boolean,
    @SerialName("thread_name") override val threadName: String,
    @SerialName("is_alive") override val isAlive: Boolean,
    @SerialName("thread_traces") override val threadTraces: List<StackTraceElem>,
    @SerialName("thread_state") override val threadState: String,
    @SerialName("is_daemon") override val isDaemon: Boolean,
    @SerialName("priority") override val priority: Int,
) : IThreadInformation

@Serializable
data class DeviceState(
    @SerialName("other_threads") override val otherThreads: List<ThreadInformation>,
    @SerialName("current_thread") override val currentThread: ThreadInformation,
    @SerialName("info_map") override val infoMap: Map<String, String>,
    @SerialName("is_connected_to_internet") override val isConnectedToInternet: Boolean,
    @SerialName("time_zone") override val timeZone: String,
    @SerialName("storage_information") override val storageInformation: List<DeviceStorageInformation>,
) : IDeviceState

@Serializable
data class LogEntry(
    @SerialName("device_state") override val deviceState: DeviceState,
    @SerialName("time_fired") override val timeFired: Int,
    @SerialName("values") override val values: Map<String, String>,
    @SerialName("tag") override val tag: String,
    @SerialName("stack_trace") override val stackTrace: String,
    @SerialName("type") override val type: String,
    @SerialName("message") override val message: String,
) : ILogEntry

@Serializable
data class DatabaseLogEntry(
    @SerialName("notes") override val notes: List<String>,
    @SerialName("log") override val log: List<LogEntry>,
    @SerialName("created_at") override val createdAt: Int,
    @SerialName("id") override val id: String,
    @SerialName("state") override val state: String,
    @SerialName("device") override val device: DeviceInformation,
    @SerialName("app_information") override val appInformation: AppInformation,
    @SerialName("user_information") override val userInformation: UserInformation,
) : IDatabaseLogEntry