package de.hypar.talkbackplayground.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hypar.talkbackplayground.R
import de.hypar.talkbackplayground.ui.theme.TalkBackPlaygroundTheme

@Composable
fun SemanticsBasicsScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Composables have semantics",
        )
        Text("They add information which accessibility services can use")
        Text("They can be used to outright describe a UI component, like an image")

        Image(
            painter = painterResource(R.drawable.gbc),
            contentDescription = "A Game Boy Color in the color teal"
        )

        Text("Or describe the role of UI component:")

        Text(
            modifier = Modifier.semantics { heading() },
            text = "Tell, don't show",
            style = MaterialTheme.typography.headlineMedium
        )
        Text("In this lightning talk I will show some best...")
        Text(
            modifier = Modifier.clickable(role = Role.Button) {
                // Do click stuff
            }.semantics { role = Role.Button },
            text = "Read more",
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
@Preview
fun SemanticsBasicsScreenPreview() {
    TalkBackPlaygroundTheme {
        SemanticsBasicsScreen()
    }
}