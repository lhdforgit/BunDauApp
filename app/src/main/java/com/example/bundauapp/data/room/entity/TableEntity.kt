package com.example.bundauapp.data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table_tb")
@Parcelize
data class TableEntity(
    @PrimaryKey
    @SerializedName("id")
    var id: String = "",

    @SerializedName("number")
    var number: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    ) : Parcelable {

}