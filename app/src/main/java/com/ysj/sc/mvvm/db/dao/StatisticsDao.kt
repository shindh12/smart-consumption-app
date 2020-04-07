package com.ysj.sc.mvvm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ysj.sc.mvvm.db.entity.CumulativeStatistics
import com.ysj.sc.mvvm.db.entity.Statistics

@Dao
interface StatisticsDao : BaseDao<Statistics> {
    @Query ("SELECT sum(categoryAmount) as amount, usedYM FROM Statistics WHERE usedYM >= STRFTIME('%Y%m', DATETIME('NOW', 'start of year')) GROUP BY usedYM ORDER BY categoryAmount DESC")
    fun getAllCumulativeAmount() : LiveData<List<CumulativeStatistics>>

    @Query ("SELECT usedYM, category, sum(categoryAmount) as categoryAmount, maxAmount FROM Statistics WHERE usedYM >= STRFTIME('%Y%m', DATETIME('NOW', 'start of year')) GROUP BY usedYM, category")
    fun getAllCategoryAmount() : LiveData<List<Statistics>>
}