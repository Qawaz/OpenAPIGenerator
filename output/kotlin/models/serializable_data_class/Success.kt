package `serializable_data_class`

@Serializable
data class Success(
    @SerialName("success") val success: Boolean,
)