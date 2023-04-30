package `overridden`

interface ISuccess {
    val success: Boolean
}

data class Success(
    override val success: Boolean,
) : ISuccess

interface IError {
    val type: String
    val message: String
}

data class Error(
    override val type: String,
    override val message: String,
) : IError

interface IAppInformation {
    val packageName: String
    val versionNumber: Int
    val version: String
}

data class AppInformation(
    override val packageName: String,
    override val versionNumber: Int,
    override val version: String,
) : IAppInformation

interface IUserInformation {
    val userEmail: String
    val userId: String
    val userName: String
}

data class UserInformation(
    override val userEmail: String,
    override val userId: String,
    override val userName: String,
) : IUserInformation

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

interface IDisplayInformation {
    val orientation: String
    val top: Int
    val left: Int
    val bottom: Int
    val right: Int
    val displayName: String
}

data class DisplayInformation(
    override val orientation: String,
    override val top: Int,
    override val left: Int,
    override val bottom: Int,
    override val right: Int,
    override val displayName: String,
) : IDisplayInformation

interface IDeviceStorageInformation {
    val storageName: String
    val totalSpace: Int
    val freeSpace: Int
}

data class DeviceStorageInformation(
    override val storageName: String,
    override val totalSpace: Int,
    override val freeSpace: Int,
) : IDeviceStorageInformation

interface IStackTraceElem {
    val isNativeMethod: Boolean
    val methodName: String
    val fileName: String?
    val lineNumber: Int
    val className: String
}

data class StackTraceElem(
    override val isNativeMethod: Boolean,
    override val methodName: String,
    override val fileName: String?,
    override val lineNumber: Int,
    override val className: String,
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

interface IDeviceState {
    val otherThreads: List<ThreadInformation>
    val currentThread: ThreadInformation
    val infoMap: Map<String, String>
    val isConnectedToInternet: Boolean?
    val timeZone: String?
    val storageInformation: List<DeviceStorageInformation>
}

data class DeviceState(
    override val otherThreads: List<ThreadInformation>,
    override val currentThread: ThreadInformation,
    override val infoMap: Map<String, String>,
    override val isConnectedToInternet: Boolean?,
    override val timeZone: String?,
    override val storageInformation: List<DeviceStorageInformation>,
) : IDeviceState

interface ILogEntry {
    val deviceState: DeviceState
    val timeFired: Long
    val values: Map<String, String>
    val tag: String
    val stackTrace: String
    val type: String
    val message: String
}

data class LogEntry(
    override val deviceState: DeviceState,
    override val timeFired: Long,
    override val values: Map<String, String>,
    override val tag: String,
    override val stackTrace: String,
    override val type: String,
    override val message: String,
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

data class DatabaseLogEntry(
    override val notes: List<String>,
    override val log: List<LogEntry>,
    override val createdAt: Int?,
    override val id: String?,
    override val state: String,
    override val device: DeviceInformation,
    override val appInformation: AppInformation,
    override val userInformation: UserInformation,
) : IDatabaseLogEntry