package com.hiepnt.basemvp.ui.activity.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hiepnt.basemvp.R
import com.hiepnt.basemvp.model.Children
import kotlinx.android.synthetic.main.item_title.view.*

class MainTitleAdapter (val context: MainActivity, val clickItemListener: (Children, Int) -> Unit): RecyclerView.Adapter<MainTitleAdapter.ViewHolder>(){
    private var items: List<Children> = listOf()
    private var currentItem = 9999

    override fun getItemCount(): Int {
        return items.size

    }

    fun updateBackItem(pos : Int){
        this.currentItem = pos
        notifyDataSetChanged()
    }

    fun updatePosts(items: List<Children>, isLoadMore:Boolean) {
        if(isLoadMore){
            this.items+=items
        }else{
            this.items = items
        }
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_title, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Children = items[position]

        holder?.txt_name!!.text = item.title
        if(item.children.size>0){
            holder?.img_next.visibility = View.VISIBLE
        }else{
            holder?.img_next.visibility = View.GONE
        }
        if(position == currentItem){
            holder?.layout_back.setBackgroundResource(R.drawable.custom_border_item_blue)
            holder?.txt_name.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder?.layout_back.setBackgroundResource(R.drawable.custom_border_item_white)
            holder?.txt_name.setTextColor(context.resources.getColor(R.color.blue))
        }
        holder?.layout_back.setOnClickListener(View.OnClickListener {
            updateBackItem(position)
            clickItemListener(item,position)
        })
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val txt_name = view.txt_name!!
        val img_next = view.img_next!!
        val layout_back = view.layout_back
    }
}