package overridden

interface IError {
    val error: String
    val message: String
}

data class Error(
    override val error: String,
    override val message: String,
) : IError