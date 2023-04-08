package data_class

@Serializable
data class Error(
    @SerialName("error") val error: String,
    @SerialName("message") val message: String,
)