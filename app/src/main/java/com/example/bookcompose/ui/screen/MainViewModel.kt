import androidx.lifecycle.LiveData
import com.example.bookcompose.core.network.result.Resource
import com.example.bookcompose.core.ui.BaseViewModel
import com.example.bookcompose.remote.model.BookModelDto

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    fun getBook(search:String): LiveData<Resource<BookModelDto.Item>> {
        return repository.getBook(search = search)
    }
    init {
        getBooks()
    }
    fun getBooks(query: String = "book", maxResult: Int = 10): LiveData<Resource<BookModelDto>> {
        return repository.getBooks(query, maxResult)
    }
}
