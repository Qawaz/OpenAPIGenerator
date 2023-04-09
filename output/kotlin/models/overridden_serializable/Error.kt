package overridden_serializable

interface IError {
    val error: String
    val message: String
}

@Serializable
data class Error(
    @SerialName("error") override val error: String,
    @SerialName("message") override val message: String,
) : IError