package com.ysj.sc.mvvm.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["usedYM", "category"])
data class Statistics(
        val usedYM : String,
        val category: String,
        val maxAmount: Long,
        val categoryAmount: Long)
