package data_class

@Serializable
data class StackTraceElem(
    @SerialName("is_native_method") val isNativeMethod: Boolean,
    @SerialName("method_name") val methodName: String,
    @SerialName("file_name") val fileName: String,
    @SerialName("line_number") val lineNumber: Int,
    @SerialName("class_name") val className: String,
)