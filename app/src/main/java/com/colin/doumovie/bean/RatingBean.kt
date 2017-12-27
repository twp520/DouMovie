package com.colin.doumovie.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by tianweiping on 2017/12/19.
 */
class RatingBean() : Parcelable {

    var max: Int = 0
    var average: Double = 0.toDouble()
    var stars: String? = null
    var min: Int = 0
    var details: DetailsBean? = null

    var count: Int = 0

    constructor(parcel: Parcel) : this() {
        max = parcel.readInt()
        average = parcel.readDouble()
        stars = parcel.readString()
        min = parcel.readInt()
    }

    /**
     * 1 : 291
     * 3 : 8528
     * 2 : 1243
     * 5 : 9110
     * 4 : 17020
     */
    class DetailsBean {
        @SerializedName("1")
        var `_$1`: Int = 0
        @SerializedName("3")
        var `_$3`:  Int = 0
        @SerializedName("2")
        var `_$2`:  Int = 0
        @SerializedName("5")
        var `_$5`:  Int = 0
        @SerializedName("4")
        var `_$4`:  Int = 0


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(max)
        parcel.writeDouble(average)
        parcel.writeString(stars)
        parcel.writeInt(min)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RatingBean> {
        override fun createFromParcel(parcel: Parcel): RatingBean {
            return RatingBean(parcel)
        }

        override fun newArray(size: Int): Array<RatingBean?> {
            return arrayOfNulls(size)
        }
    }


}