// MainActivity.kt

package app.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.movies.ui.theme.MoviesappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    NavigationController()
}

@Composable
fun MovieList(navController: NavController) {
    val movies = remember {
        getBestMovies()
    }

    LazyColumn {
        item {
            Text(
                text = "All time favorites",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        items(movies) { movie ->
            ClickableMovieItem(navController, movie = movie)
        }
    }
}

@Composable
fun ClickableMovieItem(navController: NavController, movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { navController.navigate("MovieView/${movie.title}") }
    ) {
        // You can use Coil or Glide to load images, but for simplicity, I'm using painterResource
        Image(
            painter = painterResource(id = movie.cover),
            contentDescription = null, // TODO: Provide proper content description
            modifier = Modifier
                .height(260.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column {
            Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
        }
    }
}
