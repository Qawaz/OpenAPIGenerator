package data_class

data class StackTraceElem(
    val isNativeMethod: Boolean?,
    val methodName: String?,
    val fileName: String?,
    val lineNumber: Int?,
    val className: String?,
)