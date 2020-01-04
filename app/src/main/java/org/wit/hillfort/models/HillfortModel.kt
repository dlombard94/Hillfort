package org.wit.hillfort.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable

@Parcelize
@Entity
data class HillfortModel(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                         var fbId : String = "",
                         var title: String = "",
                         var image: String = "",
                         var description: String = "",
                         @Embedded var location : Location = Location()): Parcelable

