package overridden

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