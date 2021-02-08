package com.example.bundauapp.data.repository.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bundauapp.data.api.post.PostApi
import com.example.bundauapp.data.entity.PostEntity
import com.example.bundauapp.data.retrofit.resource.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(val api: PostApi) : PostRepository {
    override fun getListPost(): LiveData<Resource<List<PostEntity>>> {
        val result = MutableLiveData<Resource<List<PostEntity>>>()
        result.value = Resource.loading(null)
        api.getListPost().enqueue(object : Callback<List<PostEntity>> {

            override fun onResponse(
                call: Call<List<PostEntity>>,
                response: Response<List<PostEntity>>
            ) {
                result.value = Resource.success(response.body())
            }

            override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {

            }
        })
        return result
    }
}