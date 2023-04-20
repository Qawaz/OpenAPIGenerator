interface Success {
    val success: Boolean
}

interface Error {
    val type: String
    val message: String
}

interface AppInformation {
    val packageName: String
    val versionNumber: Int
    val version: String
}

interface UserInformation {
    val userEmail: String
    val userId: String
    val userName: String
}

interface DeviceInformation {
    val platformType: String
    val osVersion: String
    val osName: String
    val infoMap: Map<String, String>
}

interface DisplayInformation {
    val orientation: String
    val top: Int
    val left: Int
    val bottom: Int
    val right: Int
    val displayName: String
}

interface DeviceStorageInformation {
    val storageName: String
    val totalSpace: Int
    val freeSpace: Int
}

interface StackTraceElem {
    val isNativeMethod: Boolean
    val methodName: String
    val fileName: String
    val lineNumber: Int
    val className: String
}

interface ThreadInformation {
    val threadId: Int
    val isInterrupted: Boolean
    val threadName: String
    val isAlive: Boolean
    val threadTraces: List<StackTraceElem>
    val threadState: String
    val isDaemon: Boolean
    val priority: Int
}

interface DeviceState {
    val otherThreads: List<ThreadInformation>
    val currentThread: ThreadInformation
    val infoMap: Map<String, String>
    val isConnectedToInternet: Boolean
    val timeZone: String
    val storageInformation: List<DeviceStorageInformation>
}

interface LogEntry {
    val deviceState: DeviceState
    val timeFired: Int
    val values: Map<String, String>
    val tag: String
    val stackTrace: String
    val type: String
    val message: String
}

interface DatabaseLogEntry {
    val notes: List<String>
    val log: List<LogEntry>
    val createdAt: Int
    val id: String
    val state: String
    val device: DeviceInformation
    val appInformation: AppInformation
    val userInformation: UserInformation
}