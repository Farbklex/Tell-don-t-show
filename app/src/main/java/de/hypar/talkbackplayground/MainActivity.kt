package de.hypar.talkbackplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.hypar.talkbackplayground.ui.screens.MergeSemanticsScreen
import de.hypar.talkbackplayground.ui.screens.ProfileScreen
import de.hypar.talkbackplayground.ui.screens.SemanticsBasicsScreen
import de.hypar.talkbackplayground.ui.theme.TalkBackPlaygroundTheme
import kotlinx.serialization.Serializable

@Serializable
object Profile

@Serializable
object SemanticsBasics

@Serializable
object TopicList

@Serializable
object Roles

@Serializable
object MergeSemantics

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalkBackPlaygroundTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination?.route
                                val title = when(currentDestination) {
                                    Profile::class.qualifiedName -> "Profile"
                                    TopicList::class.qualifiedName -> "TalkBack Samples"
                                    SemanticsBasics::class.qualifiedName -> "Semantics"
                                    Roles::class.qualifiedName -> "Semantic Roles"
                                    MergeSemantics::class.qualifiedName -> "Merge Semantics"
                                    else -> null
                                }
                                title?.let { Text(title, style = MaterialTheme.typography.titleLarge) }
                             }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding).padding(16.dp),
                        navController = navController, startDestination = TopicList,
                    ) {
                        composable<TopicList> { TopicListScreen(navController) }
                        composable<Profile> { ProfileScreen() }
                        composable<SemanticsBasics> { SemanticsBasicsScreen() }
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
        Pair("Profile", Profile),
        Pair("Semantics", SemanticsBasics),
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