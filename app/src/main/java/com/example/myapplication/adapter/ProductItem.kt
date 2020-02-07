package com.example.myapplication.adapter

import com.example.myapplication.GlideApp
import com.example.myapplication.R
import com.example.myapplication.model.Product
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_row_section1.view.*

class ProductItem(private val product: Product) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val imageView = viewHolder.itemView.iv_image
        val context = viewHolder.itemView.context

        GlideApp.with(context).load(product.imageUrl).into(imageView)
    }

    override fun getLayout() = R.layout.item_row_section1
}
