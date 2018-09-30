package com.exa.d2.presentation.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.exa.d2.R
import com.exa.d2.data.db.AppDatabase
import com.exa.d2.data.db.entity.TrainEntity
import com.exa.d2.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

const val TAG = "MainActivity"

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel.getTrain()
    }

    override fun onResume() {
        super.onResume()
        transitScreen()
    }

    private fun transitScreen() {
        supportFragmentManager.beginTransaction()
                .replace(binding.activityMainFragmentContainer.id, MainFragment.newInstance())
                .commit()
    }
}
