package de.hypar.talkbackplayground.ui.screens

import android.text.Layout.Alignment
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hypar.talkbackplayground.ui.theme.TalkBackPlaygroundTheme

@Composable
fun ProfileScreen(uiState: ProfileUiState = ProfileUiState()) {
    Column {
        Text(text = "Personal Data ", style = MaterialTheme.typography.headlineMedium)
        Card() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, Color.DarkGray), RoundedCornerShape(12.dp))
                    .padding(8.dp)
            ) {
                ProfileDataRow("Name", "${uiState.firstName} ${uiState.lastName}")
                ProfileDataRow("Birthday", uiState.birthdate)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Contact Info", style = MaterialTheme.typography.headlineMedium)
        Card() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, Color.DarkGray), RoundedCornerShape(12.dp))
                    .padding(8.dp)
            ) {
                ProfileDataRow("Address", "${uiState.address}\n${uiState.zipCode} ${uiState.city}")
                ProfileDataRow("E-Mail", uiState.email)
                ProfileDataRow("Phone No.", uiState.redactedPhoneNo)
            }
        }
    }
}

@Composable
fun ProfileDataRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(label)
        Text(value, textAlign = TextAlign.End)
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    TalkBackPlaygroundTheme {
        ProfileScreen()
    }
}