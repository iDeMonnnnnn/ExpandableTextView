package com.demon.expandabletextview

import android.content.Context
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.demon.expandablelibrary.ExpandableTextView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        rv.adapter = Adapter(this)
    }


    class Adapter(val context: Context) : RecyclerView.Adapter<Adapter.Holder>() {
        private val mCollapsedStatus: SparseBooleanArray = SparseBooleanArray()

        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val expandText = itemView.findViewById<ExpandableTextView>(R.id.expandText)
            val tvNo = itemView.findViewById<TextView>(R.id.tvNo)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.run {
                tvNo.text = "$position"
                expandText.setText(context.getString(R.string.long_text), mCollapsedStatus, position)
            }
        }

        override fun getItemCount(): Int = 20
    }
}
