package com.example.wyszukiwarka_witamin

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Vitamins(
    val id: String?,
    val objawy_nadmiaru: ArrayList<String>?,
    val objawy_niedoboru: ArrayList<String>?,
    val wystepowanie: ArrayList<String>?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeStringList(objawy_nadmiaru)
        parcel.writeStringList(objawy_niedoboru)
        parcel.writeStringList(wystepowanie)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vitamins> {
        override fun createFromParcel(parcel: Parcel): Vitamins {
            return Vitamins(parcel)
        }

        override fun newArray(size: Int): Array<Vitamins?> {
            return arrayOfNulls(size)
        }
    }

}