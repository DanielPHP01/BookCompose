import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookcompose.objects.ScreenConstants

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenConstants.HomeScreen.route
    ) {
        composable(ScreenConstants.HomeScreen.route) {
            HomeScreen()
        }
    }
}