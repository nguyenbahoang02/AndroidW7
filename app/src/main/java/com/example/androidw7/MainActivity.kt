package com.example.androidw7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Prepare arrays of data
        val itemList = arrayListOf<ItemModel>()
        for (i in 1..24) {
            itemList.add(
                ItemModel(
                    i,
                    ('A' + i - 1).toString() + " Hoang",
                    "0912164656",
                    ('a' + i - 1).toString() + "@gmail.com"
                )
            )
        }
        val adapter = MyCustomAdapter(itemList, this)
        val recycleView: RecyclerView = findViewById(R.id.RVLayout)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        registerForContextMenu(recycleView)
    }
}