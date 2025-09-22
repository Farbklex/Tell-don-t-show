package de.hypar.talkbackplayground.ui.screens

data class ProfileUiState(
    val firstName: String = "Alexander",
    val lastName: String = "Hoffmann",
    val address: String = "Messedamm 26",
    val zipCode: String = "14055",
    val city: String = "Berlin",
    val birthdate: String = "01.01.1990",
    val email: String = "contact@hypar.de",
    val redactedPhoneNo: String = "+4915*******99",
)
