package overridden

interface IError {
    val type: String
    val message: String
}

data class Error(
    override val type: String,
    override val message: String,
) : IError