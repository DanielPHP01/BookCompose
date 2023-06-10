@file:Suppress("CAST_NEVER_SUCCEEDS")

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bookcompose.remote.model.BookModelDto
import com.example.bookcompose.ui.theme.TextFieldColorPlaceHolder
import com.example.bookcompose.R
import com.example.bookcompose.core.network.result.Resource
import com.example.bookcompose.remote.model.Task
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var editText by remember { mutableStateOf("") }
    val vm: MainViewModel = koinViewModel()
    var data by remember { mutableStateOf<List<BookModelDto>>(emptyList()) }


    fun initViewModel() {

        vm.getBooks(editText).observeForever {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { bookList ->
                        data = listOf(bookList)
                    }
                    Log.e("ololo", "initViewModelData: ${it.data}")
                    Log.e("ololo", "initViewModelList: ${data[0]}")
                }

                Resource.Status.LOADING -> {
                    Log.d("ololo", "initViewModel: ")
                }

                Resource.Status.ERROR -> {
                    Log.e("ololo", "initViewModel: ${it.message}, ")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TextField(
            value = editText,
            onValueChange = { editText = it },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            placeholder = {
                Text(
                    text = "Search", color = TextFieldColorPlaceHolder
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        )

        Button(onClick = {
            initViewModel()
        }) {
            Text(text = "Search")
        }

        if (data.isNotEmpty()) {
            LazyColumn() {
                itemsIndexed(data[0].items) { _, item ->
                    BookItem(item = item)
                    Log.e("ololo", "HomeScreen: ${data[0].items}", )
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookItem(item: BookModelDto.Item) {
    val image = item.volumeInfo.imageLinks.smallThumbnail

    Column() {
        GlideImage(
            model = image,
            contentDescription = item.volumeInfo.title
        )
        Text(text = item.volumeInfo.title)
    }
}




