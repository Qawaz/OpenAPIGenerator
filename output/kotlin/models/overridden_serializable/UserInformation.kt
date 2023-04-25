package overridden_serializable

interface IUserInformation {
    val userEmail: String?
    val userId: String?
    val userName: String?
}

@Serializable
data class UserInformation(
    @SerialName("user_email") override val userEmail: String?,
    @SerialName("user_id") override val userId: String?,
    @SerialName("user_name") override val userName: String?,
) : IUserInformation