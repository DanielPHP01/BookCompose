package com.example.bookcompose

import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookcompose.ui.theme.BookComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookComposeTheme {
                val viewModel: BookViewModel = viewModel()
                Surface(modifier = Modifier.fillMaxSize()) {
                    BookList(viewModel = viewModel)
                }
            }
        }
    }
}

interface BookApiService {
    @GET("volumes")
    suspend fun getBooks(): List<Book>
}

data class Book(val title: String, val imageLink: String)

class BookViewModel : ViewModel() {
    private val apiService = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/") // Замените на ваш базовый URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BookApiService::class.java)

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val bookList = apiService.getBooks()
                _books.value = bookList
            } catch (e: Exception) {
                // Обработка ошибки
            }
        }
    }
}

@Composable
fun BookList(viewModel: BookViewModel) {
    val books = viewModel.books.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        books.value.forEach { book ->
            BookItem(book = book)
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BookItem(book: Book) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 150.dp)
            .clickable { /* Обработка нажатия на элемент списка */ }
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        ) {
            // Отобразите изображение книги с помощью AsyncImage или другого компонента
        }
        Text(
            text = book.title,
            color = Color.Black,
            modifier = Modifier.padding(start = 2.dp)
        )
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewBookItem() {
    BookComposeTheme {
        BookItem(
            Book(
                title = "Sample Book",
                imageLink = "http://books.google.com/books/content?id=4CFjDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
            )
        )
    }
}
