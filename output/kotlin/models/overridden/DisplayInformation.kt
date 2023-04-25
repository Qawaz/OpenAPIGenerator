package overridden

interface IDisplayInformation {
    val orientation: String?
    val top: Int?
    val left: Int?
    val bottom: Int?
    val right: Int?
    val displayName: String?
}

data class DisplayInformation(
    override val orientation: String?,
    override val top: Int?,
    override val left: Int?,
    override val bottom: Int?,
    override val right: Int?,
    override val displayName: String?,
) : IDisplayInformation