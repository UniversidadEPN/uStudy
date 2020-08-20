package com.epn.proyectomoviles

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class  ImageAdapter2 (private var mContext : Context) : BaseAdapter(){

    internal var mThumbsID = arrayOf(
        R.drawable.fisica, R.drawable.math,
        R.drawable.geo, R.drawable.quimica
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var  imageView: ImageView
        if(convertView == null){
            imageView = ImageView(mContext)
            imageView.layoutParams = AbsListView.LayoutParams(1100,700)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(0,8,0,8)

        }else{

            imageView  =  convertView as ImageView
        }
        imageView.setImageResource(mThumbsID[position])
        return  imageView
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return mThumbsID.size
    }


}