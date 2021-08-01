package com.example.ezetap_assignment.network

import com.example.ezetap_assignment.data.CustomUIData
import com.example.ezetap_assignment.data.FetchImageURL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {


    /*

    {
	"logo-url": "https://demo.ezetap.com/portal/images/logo.gif",
	"heading-text": "Ezetap Android Assignment",
	"uidata": [{
			"uitype": "label",
			"value": "Your Name",
			"key": "label_name"
		},
		{
			"uitype": "edittext",
			"key": "text_name",
			"hint": "Enter your name"
		},
		{
			"uitype": "label",
			"value": "Your Phone",
			"key": "label_phone"
		},
		{
			"uitype": "edittext",
			"key": "text_phone",
			"hint": "Enter your phone no"
		},
		{
			"uitype": "label",
			"value": "Your City",
			"key": "label_city"
		},
		{
			"uitype": "edittext",
			"key": "text_city",
			"hint": "Enter your city"
		},
		{
			"uitype": "button",
			"value": "Submit"
		}
	]
}


     */

    // This will connect to a URL and return the JSON object found in the URL.

    @GET("android_assignment")
    fun fetchCustomUI(): Call<CustomUIData?>?

    // This will connect to a URL and return the image data.

    @GET("android_assignment")
    fun fetchImage(): Call<FetchImageURL>


    companion object {
        //URL: https://demo.ezetap.com/mobileapps/android_assignment.json
        private const val BASE_URL = "https://demo/"
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}