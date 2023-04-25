package `overridden_serializable`

interface ISuccess {
    val success: Boolean
}

@Serializable
data class Success(
    @SerialName("success") override val success: Boolean,
) : ISuccess

interface IError {
    val type: String
    val message: String
}

@Serializable
data class Error(
    @SerialName("type") override val type: String,
    @SerialName("message") override val message: String,
) : IError

interface IAppInformation {
    val packageName: String
    val versionNumber: Int
    val version: String
}

@Serializable
data class AppInformation(
    @SerialName("package_name") override val packageName: String,
    @SerialName("version_number") override val versionNumber: Int,
    @SerialName("version") override val version: String,
) : IAppInformation

interface IUserInformation {
    val userEmail: String
    val userId: String
    val userName: String
}

@Serializable
data class UserInformation(
    @SerialName("user_email") override val userEmail: String,
    @SerialName("user_id") override val userId: String,
    @SerialName("user_name") override val userName: String,
) : IUserInformation

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

interface IDisplayInformation {
    val orientation: String
    val top: Int
    val left: Int
    val bottom: Int
    val right: Int
    val displayName: String
}

@Serializable
data class DisplayInformation(
    @SerialName("orientation") override val orientation: String,
    @SerialName("top") override val top: Int,
    @SerialName("left") override val left: Int,
    @SerialName("bottom") override val bottom: Int,
    @SerialName("right") override val right: Int,
    @SerialName("display_name") override val displayName: String,
) : IDisplayInformation

interface IDeviceStorageInformation {
    val storageName: String
    val totalSpace: Int
    val freeSpace: Int
}

@Serializable
data class DeviceStorageInformation(
    @SerialName("storage_name") override val storageName: String,
    @SerialName("total_space") override val totalSpace: Int,
    @SerialName("free_space") override val freeSpace: Int,
) : IDeviceStorageInformation

interface IStackTraceElem {
    val isNativeMethod: Boolean
    val methodName: String
    val fileName: String?
    val lineNumber: Int
    val className: String
}

@Serializable
data class StackTraceElem(
    @SerialName("is_native_method") override val isNativeMethod: Boolean,
    @SerialName("method_name") override val methodName: String,
    @SerialName("file_name") override val fileName: String?,
    @SerialName("line_number") override val lineNumber: Int,
    @SerialName("class_name") override val className: String,
) : IStackTraceElem

interface IThreadInformation {
    val threadId: Int
    val isInterrupted: Boolean
    val threadName: String
    val isAlive: Boolean
    val threadTraces: List<StackTraceElem>
    val threadState: String
    val isDaemon: Boolean
    val priority: Int
}

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

interface IDeviceState {
    val otherThreads: List<ThreadInformation>
    val currentThread: ThreadInformation
    val infoMap: Map<String, String>
    val isConnectedToInternet: Boolean?
    val timeZone: String?
    val storageInformation: List<DeviceStorageInformation>
}

@Serializable
data class DeviceState(
    @SerialName("other_threads") override val otherThreads: List<ThreadInformation>,
    @SerialName("current_thread") override val currentThread: ThreadInformation,
    @SerialName("info_map") override val infoMap: Map<String, String>,
    @SerialName("is_connected_to_internet") override val isConnectedToInternet: Boolean?,
    @SerialName("time_zone") override val timeZone: String?,
    @SerialName("storage_information") override val storageInformation: List<DeviceStorageInformation>,
) : IDeviceState

interface ILogEntry {
    val deviceState: DeviceState
    val timeFired: Int
    val values: Map<String, String>
    val tag: String
    val stackTrace: String
    val type: String
    val message: String
}

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

interface IDatabaseLogEntry {
    val notes: List<String>
    val log: List<LogEntry>
    val createdAt: Int?
    val id: String?
    val state: String
    val device: DeviceInformation
    val appInformation: AppInformation
    val userInformation: UserInformation
}

@Serializable
data class DatabaseLogEntry(
    @SerialName("notes") override val notes: List<String>,
    @SerialName("log") override val log: List<LogEntry>,
    @SerialName("created_at") override val createdAt: Int?,
    @SerialName("id") override val id: String?,
    @SerialName("state") override val state: String,
    @SerialName("device") override val device: DeviceInformation,
    @SerialName("app_information") override val appInformation: AppInformation,
    @SerialName("user_information") override val userInformation: UserInformation,
) : IDatabaseLogEntry