package `overridden`

interface IUserInformation {
    val userEmail: String
    val userId: String
    val userName: String
}

data class UserInformation(
    override val userEmail: String,
    override val userId: String,
    override val userName: String,
) : IUserInformation