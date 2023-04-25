package `overridden_serializable`

interface IDisplayInformation {
    val orientation: String
    val top: Int
    val left: Int
    val bottom: Int
    val right: Int
    val displayName: String
}

@Serializable
data class DisplayInformation(
    @SerialName("orientation") override val orientation: String,
    @SerialName("top") override val top: Int,
    @SerialName("left") override val left: Int,
    @SerialName("bottom") override val bottom: Int,
    @SerialName("right") override val right: Int,
    @SerialName("display_name") override val displayName: String,
) : IDisplayInformation