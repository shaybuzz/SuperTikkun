package com.supertikkun.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.supertikkun.R
import com.supertikkun.local.TikunDataBase
import com.supertikkun.network.NetworkService
import com.supertikkun.repository.TikunRepoImpl
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tikunService = NetworkService.tikunService
        val tikunRepo = TikunRepoImpl(TikunDataBase.getDatabase(this), tikunService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(tikunService, tikunRepo)).get(MainViewModel::class.java)

//        mainViewModel.textTikunData.observe(this, Observer {
//            if(it == null ){
//                Timber.d("#### test datta data is null!!!!")
//            }else {
//                textSimple.text = it.textSimple
//                textTaam.text = it.textWithTaam
//            }
//        })

//        mainViewModel.textSimple.observe(this, Observer {
//
//                Timber.d("###got simple $it")
//                textSimple.text = it
//
////
////            //textSimple.visibility = View.GONE
//        })
//
//        mainViewModel.textWithTaam.observe(this, Observer {
//
//                Timber.d("###main got taam  $it")
//                textTaam.text = it
//
//
////
////            textTaam2.text = it
////            textTaam.visibility = View.GONE
//        })
//

    }
}
