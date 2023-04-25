package `overridden`

interface ISuccess {
    val success: Boolean
}

data class Success(
    override val success: Boolean,
) : ISuccess