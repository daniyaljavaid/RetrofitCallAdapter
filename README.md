# RetrofitCallAdapters

Retrofit call adapters to support following types:
```
1. ResultState<T>
2. Flow<ResultState<T>>
```

So your Retrofit Service results in

```
@GET("?name=xyz")
suspend fun getDataWithResultState(): ResultState<ResponseDto>

@GET("?name=xyz")
fun getDataWithResultStateFlow(): Flow<ResultState<ResponseDto>>
```


where ```ResultState``` is a sealed interface for handling network response

```
sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val exception: Throwable? = null) : ResultState<Nothing>
}
```

