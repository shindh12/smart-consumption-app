package com.ysj.sc.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ysj.sc.mvvm.db.dao.ConsumptionDao
import com.ysj.sc.mvvm.db.dao.StatisticsDao
import com.ysj.sc.mvvm.db.entity.Consumption
import com.ysj.sc.mvvm.db.entity.Statistics

@Database(entities = [Consumption::class, Statistics::class], version = 1, exportSchema = false)
abstract class SCDatabase : RoomDatabase() {
    abstract fun consumptionDao() : ConsumptionDao
    abstract fun statisticsDao() : StatisticsDao

    companion object {
        val DB_NAME = "sc.db"
        private var instance: SCDatabase? = null
        fun getInstance(context: Context) : SCDatabase{
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, SCDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build().also { instance = it }
            }
        }
    }
}