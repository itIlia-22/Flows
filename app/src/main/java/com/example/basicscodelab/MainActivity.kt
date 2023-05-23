package com.example.basicscodelab

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printRange()
    }

    private fun printRange() {
        CoroutineScope(Dispatchers.Main).launch {
            (1..5).asFlow()
                .map {
//выбрасывается ошибка, если значение == 3
                    check(it != 3) { "Значение == $it" }//текст ошибки
                    it * it
                }
                .onCompletion {
                    Log.d(TAG, "onCompletion")
                }
                .catch { e ->
                    Log.d(TAG, "Ошибка: $e")
                }
                .collect {
                    Log.d(TAG, it.toString())
                }
        }
    }


}