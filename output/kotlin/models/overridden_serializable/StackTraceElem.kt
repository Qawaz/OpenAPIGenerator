package overridden_serializable

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