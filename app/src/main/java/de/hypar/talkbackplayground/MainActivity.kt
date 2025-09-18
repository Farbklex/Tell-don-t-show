package de.hypar.talkbackplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.hypar.talkbackplayground.ui.theme.TalkBackPlaygroundTheme
import kotlinx.serialization.Serializable
import androidx.navigation.compose.rememberNavController
import de.hypar.talkbackplayground.ui.screens.MergeSemanticsScreen

@Serializable
object TopicList

@Serializable
object Roles

@Serializable
object MergeSemantics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalkBackPlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController, startDestination = TopicList,
                    ) {
                        composable<TopicList> { TopicListScreen(navController) }
                        composable<Roles> { RolesScreen() }
                        composable<MergeSemantics> { MergeSemanticsScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun TopicListScreen(navController: NavController) {
    val topics: List<Pair<String, Any>> = listOf(
        Pair("Merge semantics", MergeSemantics),
        Pair("Semantic Roles", Roles),
    )

    LazyColumn {
        for (topic in topics) {
            item {
                Text(modifier = Modifier.clickable {
                    navController.navigate(topic.second)
                }, text = topic.first)
            }
        }
    }
}

@Composable
fun RolesScreen() {

}


@Preview(showBackground = true)
@Composable
fun TalkBackListPreview() {
    TalkBackPlaygroundTheme {
    }
}