package overridden_serializable

interface ISuccess {
    val success: Boolean?
}

@Serializable
data class Success(
    @SerialName("success") override val success: Boolean?,
) : ISuccess