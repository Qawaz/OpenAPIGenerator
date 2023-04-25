package data_class

@Serializable
data class Error(
    @SerialName("type") val type: String?,
    @SerialName("message") val message: String?,
)