package de.hypar.talkbackplayground.ui.screens.start

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.hypar.talkbackplayground.ui.screens.ProfileUiState
import de.hypar.talkbackplayground.ui.theme.TalkBackPlaygroundTheme
import kotlinx.coroutines.delay

@Composable
fun ProfileScreenStart(uiState: ProfileUiState = ProfileUiState()) {
    var showSmsNotification by remember {mutableStateOf(false)}
    Column {
        PersonalData(uiState)
        Spacer(modifier = Modifier.height(32.dp))
        ContactData(uiState) { showSmsNotification = true }
        Spacer(modifier = Modifier.height(32.dp))
        PaymentInfo(uiState)
    }
    if(showSmsNotification){
        Box(modifier = Modifier.fillMaxWidth().background(Color.Green).padding(16.dp)){
            Text("Verification SMS sent")
            LaunchedEffect(showSmsNotification) {
                delay(3000)
                showSmsNotification = false
            }
        }
    }
}

@Composable
private fun PersonalData(uiState: ProfileUiState) {
    Text(
        modifier = Modifier,
        text = "Personal Data",
        style = MaterialTheme.typography.headlineMedium,
    )
    Card(
        Modifier.border(
            BorderStroke(2.dp, Color.DarkGray), RoundedCornerShape(12.dp)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

                .padding(8.dp)
        ) {
            ProfileDataRow(
                "Name",
                "${uiState.firstName} ${uiState.lastName}"
            )
            ProfileDataRow("Birthday", uiState.birthdate)
        }
    }
}

@Composable
private fun ContactData(uiState: ProfileUiState, onShowSmsNotification: () -> Unit) {
    Text(
        modifier = Modifier,
        text = "Contact Info",
        style = MaterialTheme.typography.headlineMedium
    )
    Card(
        Modifier.border(
            BorderStroke(2.dp, Color.DarkGray), RoundedCornerShape(12.dp)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            ProfileDataRow(
                "Address",
                "${uiState.address}\n${uiState.zipCode} ${uiState.city}"
            )
            ProfileDataRow("E-Mail", uiState.email)
            ProfileDataRow(
                "Phone No.",
                uiState.redactedPhoneNo
            )
            ErrorText("Phone number has not, yet been verified.", onShowSmsNotification)
        }
    }
}

@Composable
private fun PaymentInfo(uiState: ProfileUiState) {
    Text(
        modifier = Modifier,
        text = "Payment Info",
        style = MaterialTheme.typography.headlineMedium
    )
    Card(
        modifier = Modifier
            .border(
                BorderStroke(2.dp, Color.DarkGray), RoundedCornerShape(12.dp)
            )
            // Yes!
            .clickable() {
                // TODO Edit payment info
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                ProfileDataRow("Type", "Bank account")
                ProfileDataRow("IBAN", uiState.redactedIBAN)
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ErrorText(text: String, onShowSmsNotification: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable() {
                onShowSmsNotification()
//                Toast.makeText(context, "Verification SMS sent", Toast.LENGTH_SHORT).show()
            },
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            color = Color.Red,
            textAlign = TextAlign.End,
            fontSize = 13.sp,
            textDecoration = TextDecoration.Underline
        )
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "", tint = Color.Red)
    }
}

@Composable
internal fun ProfileDataRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(text = label)
        Text(text = value, textAlign = TextAlign.End)
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenStartPreview() {
    TalkBackPlaygroundTheme {
        ProfileScreenStart()
    }
}