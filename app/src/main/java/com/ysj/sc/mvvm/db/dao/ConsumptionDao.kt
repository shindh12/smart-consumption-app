package com.ysj.sc.mvvm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ysj.sc.adapter.entity.ConsumptionVO
import com.ysj.sc.mvvm.db.entity.Consumption
import com.ysj.sc.mvvm.db.entity.Statistics

@Dao
interface ConsumptionDao : BaseDao<Consumption> {
    @Query("SELECT * FROM Consumption")
    fun getAll(): LiveData<List<Consumption>>

    @Query("SELECT * FROM Consumption WHERE category = :category ORDER BY usedDate DESC")
    fun getDetailsByCategory(category: String): List<Consumption>

    @Query("SELECT usedDate FROM Consumption ORDER BY usedDate DESC LIMIT 1")
    fun getLastDate(): String

//    @Query("SELECT SUM(usedAmount) as categoryAmount, category as categoryCd, 3000000 as maxAmount FROM Consumption GROUP BY category")
//    fun getAggregationByCategory(): List<Statistics>

}