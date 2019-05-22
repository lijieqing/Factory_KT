package com.fengmi.factory_kt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.fengmi.factory_kt.views.InfoTagView

class FactoryLauncher : Activity() {
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lv_info)

        var adapter = InfoAdapter(this)

        listView.adapter = adapter
    }

}

class InfoAdapter : BaseAdapter {
    var infoMap: HashMap<String, String> = HashMap()
    var context: Context

    constructor(context: Context) {
        this.context = context
        for (index in 0..9) {
            infoMap.put("Version $index", "Value Data&$index")
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = View.inflate(context, R.layout.info_item, null)
        view.findViewById<InfoTagView>(R.id.info_item).setTagName("Version $position")
        view.findViewById<InfoTagView>(R.id.info_item).setTagValue(infoMap.get("Version $position"))
//        view.findViewById<TextView>(R.id.tv_key).text = "Version $position"
//        view.findViewById<TextView>(R.id.tv_value).text = infoMap.get("Version $position")
        return view
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return infoMap.size
    }

}
