package `overridden`

interface IAppInformation {
    val packageName: String
    val versionNumber: Int
    val version: String
}

data class AppInformation(
    override val packageName: String,
    override val versionNumber: Int,
    override val version: String,
) : IAppInformation