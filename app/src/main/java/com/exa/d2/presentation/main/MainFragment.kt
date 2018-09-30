package com.exa.d2.presentation.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.exa.d2.data.db.entity.TrainEntity
import com.exa.d2.data.model.Train
import com.exa.d2.databinding.FragmentMainBinding
import com.exa.d2.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
    }
    lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.trains.observe(this, Observer {
            mainViewModel.saveTrain()
            setAdapter()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setAdapter() {
        val adapter = TrainAdapter(mainViewModel.trains.value!!)
        adapter.setOnItemClickListener(object : TrainAdapter.OnItemClickListener {
            override fun onClick(view: View, data: TrainEntity) {
                Toast.makeText(activity?.applicationContext, data.name, Toast.LENGTH_LONG).show()
            }
        })
        binding.mainFragmentRecyclerview.adapter = adapter
        binding.mainFragmentRecyclerview.setLayoutManager(LinearLayoutManager(activity!!))
    }
}
