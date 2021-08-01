package com.example.ezetap_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ezetap_assignment.data.CustomUIData
import com.example.ezetap_assignment.data.FetchImageURL
import com.example.ezetap_assignment.data.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    //this is the data that we will fetch asynchronously
    var customUIData: MutableLiveData<CustomUIData> = MutableLiveData<CustomUIData>()
    var fetchLogoUrl: MutableLiveData<FetchImageURL> = MutableLiveData<FetchImageURL>()
    val errorMessage = MutableLiveData<String>()
    val logoUrl = MutableLiveData<String>()


    fun customUIData(): MutableLiveData<CustomUIData> {
        val response = homeRepository.getCustomUIData()
        response?.enqueue(object : Callback<CustomUIData?> {
            override fun onResponse(call: Call<CustomUIData?>, response: Response<CustomUIData?>) {
                if (response.isSuccessful) {
                    customUIData.value = response.body()
                    Log.e("onResponse", " - > onResponse    ${customUIData.value}")
                }

            }

            override fun onFailure(call: Call<CustomUIData?>, t: Throwable) {
                Log.e("ListSize", " - > Error    " + t.message)
            }
        })
        return customUIData
    }


    fun fetchLogoUrlData(): MutableLiveData<FetchImageURL> {
        val response = homeRepository.getFetchImageUrl()
        response?.enqueue(object : Callback<FetchImageURL?> {
            override fun onResponse(
                call: Call<FetchImageURL?>,
                response: Response<FetchImageURL?>
            ) {
                if (response.isSuccessful) {
                    fetchLogoUrl.value = response.body()
                    Log.e("onResponse", " - > onResponse    ${fetchLogoUrl.value}")
                }

            }

            override fun onFailure(call: Call<FetchImageURL?>, t: Throwable) {
                Log.e("ListSize", " - > Error    " + t.localizedMessage)
            }
        })
        return fetchLogoUrl
    }


}











