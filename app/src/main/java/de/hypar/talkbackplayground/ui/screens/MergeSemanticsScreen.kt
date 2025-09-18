package de.hypar.talkbackplayground.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hypar.talkbackplayground.MergeSemantics
import de.hypar.talkbackplayground.ui.theme.TalkBackPlaygroundTheme

@Composable
fun MergeSemanticsScreen() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("First name")
            Text("Alexander")
        }
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .semantics(mergeDescendants = true) { },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("First name:")
            Text("Alexander")
        }
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("First name:")
            Text("Alexander")
            Icon(
                Icons.Default.Edit,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun MergeSemanticsScreenPreview() {
    TalkBackPlaygroundTheme {
        MergeSemanticsScreen()
    }
}