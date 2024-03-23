package com.example.wavesoffood.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable



//        Made by Muhammad Noman
//
//        If you need my service or you have any question then you can contact with me.
//
//        WhatsApp number :  +923104881573
//
//        LinkedIn account :  https://www.linkedin.com/in/muhammad-noman59
//
//        Facebook account :  https://www.facebook.com/profile.php?id=100092720556743&mibextid=ZbWKwL





class OrderDetails(): Serializable  {

     var userId : String? = null
     var userName : String? = null
     var foodItemName : ArrayList<String>? = null
     var foodItemImage : ArrayList<String>? = null
     var foodItemPrice : ArrayList<String>? = null
     var foodItemQuantities : ArrayList<Int>? = null
     var address : String? = null
     var totalPrice : String? = null
     var phoneNumber : String? = null
     var orderAccepted : Boolean = false
     var paymentReceived : Boolean = false
     var itemPushKey : String? = null
     var currentTime : Long = 0

    constructor(parcel: Parcel) : this() {
        userId = parcel.readString()
        userName = parcel.readString()
        address = parcel.readString()
        totalPrice = parcel.readString()
        phoneNumber = parcel.readString()
        orderAccepted = parcel.readByte() != 0.toByte()
        paymentReceived = parcel.readByte() != 0.toByte()
        itemPushKey = parcel.readString()
        currentTime = parcel.readLong()
    }

    constructor(
        userId: String,
        names: String,
        foodItemName: ArrayList<String>,
        foodItemImage: ArrayList<String>,
        foodItemPrice: ArrayList<String>,
        foodItemQuantities: ArrayList<Int>,
        address: String,
        numbers: String,
        time: Long,
        itemPushKey: String?,
        b: Boolean,
        b1: Boolean,
        totalPrice: String?
    ) : this(){

        this.userId = userId
        this.userName = names
        this.foodItemName = foodItemName
        this.foodItemImage = foodItemImage
        this.foodItemPrice = foodItemPrice
        this.foodItemQuantities = foodItemQuantities
        this.address = address
        this.phoneNumber = numbers
        this.currentTime = time
        this.itemPushKey = itemPushKey
        this.orderAccepted = orderAccepted
        this.paymentReceived = paymentReceived
        this.totalPrice = totalPrice
    }

   fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(userName)
        parcel.writeString(address)
        parcel.writeString(totalPrice)
        parcel.writeString(phoneNumber)
        parcel.writeByte(if (orderAccepted) 1 else 0)
        parcel.writeByte(if (paymentReceived) 1 else 0)
        parcel.writeString(itemPushKey)
        parcel.writeLong(currentTime)
    }

    fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderDetails> {
        override fun createFromParcel(parcel: Parcel): OrderDetails {
            return OrderDetails(parcel)
        }

        override fun newArray(size: Int): Array<OrderDetails?> {
            return arrayOfNulls(size)
        }
    }
}