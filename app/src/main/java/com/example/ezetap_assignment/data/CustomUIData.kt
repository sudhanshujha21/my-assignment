package com.example.ezetap_assignment.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CustomUIData(
    @SerializedName("heading-text")
    @Expose
    var headingText: String,
    @SerializedName("uidata")
    @Expose
    var uidata: List<UIData>
)


