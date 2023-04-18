interface ISuccess {
    val success: Boolean
}

data class Success(
    override val success: Boolean,
) : ISuccess

data class Error(
    override val error: String,
    override val message: String,
) : IError

data class AppInformation(
    override val packageName: String,
    override val versionNumber: Int,
    override val version: String,
) : IAppInformation

data class UserInformation(
    override val userEmail: String,
    override val userId: String,
    override val userName: String,
) : IUserInformation

data class DeviceInformation(
    override val platformType: String,
    override val osVersion: String,
    override val osName: String,
    override val infoMap: Map<String, String>,
) : IDeviceInformation

data class DisplayInformation(
    override val orientation: String,
    override val top: Int,
    override val left: Int,
    override val bottom: Int,
    override val right: Int,
    override val displayName: String,
) : IDisplayInformation

data class DeviceStorageInformation(
    override val storageName: String,
    override val totalSpace: Int,
    override val freeSpace: Int,
) : IDeviceStorageInformation

data class StackTraceElem(
    override val isNativeMethod: Boolean,
    override val methodName: String,
    override val fileName: String,
    override val lineNumber: Int,
    override val className: String,
) : IStackTraceElem

data class ThreadInformation(
    override val threadId: Int,
    override val isInterrupted: Boolean,
    override val threadName: String,
    override val isAlive: Boolean,
    override val threadTraces: List<StackTraceElem>,
    override val threadState: String,
    override val isDaemon: Boolean,
    override val priority: Int,
) : IThreadInformation

data class DeviceState(
    override val otherThreads: List<ThreadInformation>,
    override val currentThread: ThreadInformation,
    override val infoMap: Map<String, String>,
    override val isConnectedToInternet: Boolean,
    override val timeZone: String,
    override val storageInformation: List<DeviceStorageInformation>,
) : IDeviceState

data class LogEntry(
    override val deviceState: DeviceState,
    override val timeFired: Int,
    override val values: Map<String, String>,
    override val tag: String,
    override val stackTrace: String,
    override val type: String,
    override val message: String,
) : ILogEntry

data class DatabaseLogEntry(
    override val notes: List<String>,
    override val log: List<LogEntry>,
    override val createdAt: Int,
    override val id: String,
    override val state: String,
    override val device: DeviceInformation,
    override val appInformation: AppInformation,
    override val userInformation: UserInformation,
) : IDatabaseLogEntry