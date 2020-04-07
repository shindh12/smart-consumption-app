package com.ysj.sc.mvvm.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ysj.sc.adapter.entity.ConsumptionVO
import com.ysj.sc.mvvm.db.SCDatabase
import com.ysj.sc.mvvm.db.entity.Consumption
import com.ysj.sc.mvvm.db.entity.Statistics
import com.ysj.sc.mvvm.repository.ConsumptionRepository
import com.ysj.sc.mvvm.repository.StatisticsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ConsumptionViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: ConsumptionRepository by lazy {
        ConsumptionRepository.getInstance(application)
    }
    private val sRepository: StatisticsRepository by lazy {
        StatisticsRepository.getInstance(application)
    }
    private val consumptions: LiveData<List<Consumption>> by lazy {
        cRepository.getAll()
    }
    private val statistics: LiveData<List<Statistics>> by lazy {
        sRepository.getAll()
    }

    fun getAllStatistics() = statistics

}

