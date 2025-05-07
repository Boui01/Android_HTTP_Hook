package com.example.product

import kotlinx.serialization.Serializable

@Serializable
data class Product (val id:Int, val name:String, val price:Int, val stock:Int){
    fun info():String{
        return "$id: $name $price $stock"
    }
}
