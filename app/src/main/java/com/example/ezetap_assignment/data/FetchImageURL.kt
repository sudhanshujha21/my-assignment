package com.example.ezetap_assignment.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FetchImageURL(
    @SerializedName("logo-url")
    @Expose
    var logoUrl: String
)
