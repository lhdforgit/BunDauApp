package com.example.bundauapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bundauapp.data.entity.PostEntity
import com.example.bundauapp.data.repository.post.PostRepository
import com.example.bundauapp.data.retrofit.resource.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    fun getListPost(): LiveData<Resource<List<PostEntity>>> {
        return postRepository.getListPost()
    }

}