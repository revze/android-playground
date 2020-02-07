package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        rv_section1.isNestedScrollingEnabled = false
//        rv_section1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        rv_section1.adapter = adapter
//        for (i in 0 until 10) {
//            adapter.add(ProductItem(Product("Revando", "https://i.picsum.photos/id/297/400/300.jpg")))
//        }
//
//        rv_section2.isNestedScrollingEnabled = false
//        rv_section2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        rv_section2.adapter = adapter2
//        for (i in 0 until 10) {
//            adapter2.add(ProductItem(Product("Revando", "https://i.picsum.photos/id/297/400/300.jpg")))
//        }
    }

    private val adapter: GroupAdapter<GroupieViewHolder> = GroupAdapter()
    private val adapter2: GroupAdapter<GroupieViewHolder> = GroupAdapter()
}
