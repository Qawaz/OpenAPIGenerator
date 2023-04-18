data class Success(
    val success: Boolean,
)

data class Error(
    val error: String,
    val message: String,
)

data class AppInformation(
    val packageName: String,
    val versionNumber: Int,
    val version: String,
)

data class UserInformation(
    val userEmail: String,
    val userId: String,
    val userName: String,
)

data class DeviceInformation(
    val platformType: String,
    val osVersion: String,
    val osName: String,
    val infoMap: Map<String, String>,
)

data class DisplayInformation(
    val orientation: String,
    val top: Int,
    val left: Int,
    val bottom: Int,
    val right: Int,
    val displayName: String,
)

data class DeviceStorageInformation(
    val storageName: String,
    val totalSpace: Int,
    val freeSpace: Int,
)

data class StackTraceElem(
    val isNativeMethod: Boolean,
    val methodName: String,
    val fileName: String,
    val lineNumber: Int,
    val className: String,
)

data class ThreadInformation(
    val threadId: Int,
    val isInterrupted: Boolean,
    val threadName: String,
    val isAlive: Boolean,
    val threadTraces: List<StackTraceElem>,
    val threadState: String,
    val isDaemon: Boolean,
    val priority: Int,
)

data class DeviceState(
    val otherThreads: List<ThreadInformation>,
    val currentThread: ThreadInformation,
    val infoMap: Map<String, String>,
    val isConnectedToInternet: Boolean,
    val timeZone: String,
    val storageInformation: List<DeviceStorageInformation>,
)

data class LogEntry(
    val deviceState: DeviceState,
    val timeFired: Int,
    val values: Map<String, String>,
    val tag: String,
    val stackTrace: String,
    val type: String,
    val message: String,
)

data class DatabaseLogEntry(
    val notes: List<String>,
    val log: List<LogEntry>,
    val createdAt: Int,
    val id: String,
    val state: String,
    val device: DeviceInformation,
    val appInformation: AppInformation,
    val userInformation: UserInformation,
)