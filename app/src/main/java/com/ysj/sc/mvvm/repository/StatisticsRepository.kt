package com.ysj.sc.mvvm.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.ysj.sc.mvvm.db.SCDatabase
import com.ysj.sc.mvvm.db.dao.StatisticsDao
import com.ysj.sc.mvvm.db.entity.Consumption
import com.ysj.sc.mvvm.db.entity.CumulativeStatistics
import com.ysj.sc.mvvm.db.entity.Statistics
import com.ysj.sc.mvvm.retrofit.BackendService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class StatisticsRepository(application: Application) {
    private val executor: ExecutorService by lazy {
        Executors.newSingleThreadExecutor()
    }
    private val backendService: BackendService by lazy {
        BackendService.instance()
    }

    private val statisticsDao: StatisticsDao by lazy {
        val db = SCDatabase.getInstance(application)
        db.statisticsDao()
    }

    companion object {
        private var instance: StatisticsRepository? = null
        fun getInstance(application: Application) : StatisticsRepository{
            return instance ?: synchronized(this) {
                instance ?: StatisticsRepository(application)
            }
        }
    }
    fun getAll() : LiveData<List<Statistics>>{
        return statisticsDao.getAllCategoryAmount()
    }

    fun getAllChartData() : LiveData<List<CumulativeStatistics>> {
        return statisticsDao.getAllCumulativeAmount()
    }

    fun getAllStatisticsDataFromServer(userId: String) {
        backendService.getAllStatistics(userId).enqueue(object : Callback<List<Statistics>> {
            override fun onFailure(call: Call<List<Statistics>>, t: Throwable) {
                Log.d("####", "Failed : " + t.localizedMessage)
            }
            override fun onResponse(call: Call<List<Statistics>>, response: Response<List<Statistics>>) {
                statisticsDao.insert(response.body()!!)
                Log.d("####", "insert...")
            }
        })
    }
}
