package `serializable_data_class`

@Serializable
data class UserInformation(
    @SerialName("user_email") val userEmail: String,
    @SerialName("user_id") val userId: String,
    @SerialName("user_name") val userName: String,
)