package com.example.multitableinroom.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.multitableinroom.R
import com.example.multitableinroom.database.entity.Region
import com.example.multitableinroom.databinding.ItemRegionBinding

class RegionAdapter(context: Context, var list:List<Region> ):
    ArrayAdapter<Region>(context, R.layout.item_region) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemRegionBinding: ItemRegionBinding
        if (convertView == null) {
            itemRegionBinding =
                ItemRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        } else {
            itemRegionBinding = ItemRegionBinding.bind(convertView)
        }
        itemRegionBinding.regionNameTv.text = list[position].name
        return itemRegionBinding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemRegionBinding: ItemRegionBinding
        if (convertView == null) {
            itemRegionBinding =
                ItemRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        } else {
            itemRegionBinding = ItemRegionBinding.bind(convertView)
        }
        itemRegionBinding.regionNameTv.text = list[position].name
        return itemRegionBinding.root

    }

    override fun getCount(): Int {
        return super.getCount()

    }


}