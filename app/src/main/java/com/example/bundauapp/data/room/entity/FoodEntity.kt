package com.example.bundauapp.data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "food_tb")
@Parcelize
data class FoodEntity(
    @PrimaryKey
    @SerializedName("id")
    var id: String = "",
    @SerializedName("number")
    var time: Long? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: Int? = null, // 1 food : 2 drink
    @SerializedName("price")
    var price: Double? = null,
    @SerializedName("promoPercent")
    var promoPercent: Int? = null,
    @SerializedName("description")
    var description: String? = null
) : Parcelable {
}