package `overridden_serializable`

interface IError {
    val type: String
    val message: String
}

@Serializable
data class Error(
    @SerialName("type") override val type: String,
    @SerialName("message") override val message: String,
) : IError