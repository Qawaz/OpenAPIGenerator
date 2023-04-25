package data_class

@Serializable
data class AppInformation(
    @SerialName("package_name") val packageName: String,
    @SerialName("version_number") val versionNumber: Int,
    @SerialName("version") val version: String,
)