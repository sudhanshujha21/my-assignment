package com.example.ezetap_assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezetap_assignment.data.HomeRepository

class MyViewModelFactory constructor(private val homeRepository: HomeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            MyViewModel(this.homeRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}