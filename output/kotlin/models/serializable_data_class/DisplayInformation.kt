package `serializable_data_class`

@Serializable
data class DisplayInformation(
    @SerialName("orientation") val orientation: String,
    @SerialName("top") val top: Int,
    @SerialName("left") val left: Int,
    @SerialName("bottom") val bottom: Int,
    @SerialName("right") val right: Int,
    @SerialName("display_name") val displayName: String,
)