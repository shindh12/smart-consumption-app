package com.ysj.sc.mvvm.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// data class includes methods like toString, hashCode, equlas, copy
// String? permits the null value. default : null impossible
@Entity
class Consumption() {
    constructor(date: String, store : String, usedAmount : Long, category : String) : this() {
        this.date = date
        this.store = store
        this.usedAmount = usedAmount
        this.category = category
    }
    @PrimaryKey (autoGenerate = true) var uid: Int = 0
    @ColumnInfo (name = "usedDate") var date: String = ""
    @ColumnInfo (name = "storeName") var store: String = ""
    var usedAmount: Long = 0
    var category: String = ""
}