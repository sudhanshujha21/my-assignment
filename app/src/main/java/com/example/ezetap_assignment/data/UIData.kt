package com.example.ezetap_assignment.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UIData(
    @SerializedName("uitype")
    @Expose
    var uitype: String,
    @SerializedName("value")
    @Expose
    var value: String,
    @SerializedName("key")
    @Expose
    var key: String,
    @SerializedName("hint")
    @Expose
    var hint: String
)




