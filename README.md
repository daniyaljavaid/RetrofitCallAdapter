# Retrofit Call Adapters
## ResultState & Flow

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

# Type Restriction Adapter

Added ```RestrictionAdapter``` to restrict any return type other than:
```
ResultState<T>
```

So following return types will throw exception

```
@GET("?name=xyz")
suspend fun getData(): ResponseDto

@GET("?name=xyz")
suspend fun getDataAsResponse(): Response<ResponseDto>
```

and only ```ResultState<T>``` will be supported


```
@GET("?name=dj")
suspend fun getDataWithResultState(): ResultState<ResponseDto>
```
