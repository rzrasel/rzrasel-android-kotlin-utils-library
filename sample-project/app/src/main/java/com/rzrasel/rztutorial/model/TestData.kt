package com.rzrasel.rztutorial.model

import android.os.Parcel
import android.os.Parcelable

data class TestData(var name: String?, var age: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestData> {
        override fun createFromParcel(parcel: Parcel): TestData {
            return TestData(parcel)
        }

        override fun newArray(size: Int): Array<TestData?> {
            return arrayOfNulls(size)
        }
    }
}
