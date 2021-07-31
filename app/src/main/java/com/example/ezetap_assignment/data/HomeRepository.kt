package com.example.ezetap_assignment.data

import com.example.ezetap_assignment.network.RetrofitService


class HomeRepository constructor(private val retrofitService: RetrofitService) {
    fun getCustomUIData() = retrofitService.fetchCustomUI()
    fun getFetchImageUrl() = retrofitService.fetchImage()


}