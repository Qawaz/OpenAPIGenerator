package overridden_serializable

interface IAppInformation {
    val packageName: String?
    val versionNumber: Int?
    val version: String?
}

@Serializable
data class AppInformation(
    @SerialName("package_name") override val packageName: String?,
    @SerialName("version_number") override val versionNumber: Int?,
    @SerialName("version") override val version: String?,
) : IAppInformation