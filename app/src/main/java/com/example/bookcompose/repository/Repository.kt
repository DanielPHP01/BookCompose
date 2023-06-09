import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.bookcompose.core.network.result.Resource
import com.example.bookcompose.remote.RemoteDataSource
import com.example.bookcompose.remote.model.BookModelDto
import kotlinx.coroutines.Dispatchers

class Repository(private val dataSource: RemoteDataSource) {
    fun getBook(search:String): LiveData<Resource<BookModelDto.Item>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getBook(search = search)
            emit(response)
        }
    }

     fun getBooks(
        query: String,
        maxResult: Int
    ):  LiveData<Resource<BookModelDto>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val response = dataSource.getBooks(query, maxResult)
            emit(response)
        }
    }
}
