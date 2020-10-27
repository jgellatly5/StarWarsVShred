package com.jordangellatly.starwarsvshred.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StarWarsCharacter(
    val name: String,
    val height: String,
    val mass: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("eye_color") val eyeColor: String,
    @SerializedName("birth_year") val birthYear: String,
    val gender: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(height)
        parcel.writeString(mass)
        parcel.writeString(hairColor)
        parcel.writeString(skinColor)
        parcel.writeString(eyeColor)
        parcel.writeString(birthYear)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StarWarsCharacter> {
        override fun createFromParcel(parcel: Parcel): StarWarsCharacter {
            return StarWarsCharacter(parcel)
        }

        override fun newArray(size: Int): Array<StarWarsCharacter?> {
            return arrayOfNulls(size)
        }
    }

}
