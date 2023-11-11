package com.example.androidw7


import android.content.Context
import android.content.Intent
import android.view.*
import android.view.View.OnLongClickListener
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class MyCustomAdapter(private val items: ArrayList<ItemModel>, val context: Context) :
    RecyclerView.Adapter<MyCustomAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.item, p0, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerViewHolder, p0: Int) {
        viewHolder.name.text = items[p0].name
        viewHolder.itemAvatar.text = items[p0].name[0].toString().uppercase()


        viewHolder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick) {
                    val popup = PopupMenu(context, view)
                    popup.inflate(R.menu.menu)
                    popup.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.action_call -> {
                                Toast.makeText(context, "action_call" + items[position].name, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            R.id.action_sendEmail -> {
                                Toast.makeText(context, "action_sendEmail" + items[position].name, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            else -> {
                                Toast.makeText(context, "action_sendSMS" + items[position].name, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        true
                    }
                    popup.show()
                } else {
                    val intent = Intent(context, ItemActivity::class.java)
                    intent.putExtra("name", items[position].name)
                        .putExtra("phone", items[position].phoneNumber)
                        .putExtra("gmail", items[position].gmail)
                    startActivity(context, intent, null)
                }

            }
        })
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, OnLongClickListener {
        val name: TextView = itemView.findViewById(R.id.item_title)
        val itemAvatar: TextView = itemView.findViewById(R.id.item_avatar)
        private var itemClickListener: ItemClickListener? = null

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener?) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(v: View) {
            itemClickListener?.onClick(v, adapterPosition, false)
        }

        override fun onLongClick(v: View): Boolean {
            itemClickListener?.onClick(v, adapterPosition, true)
            return true
        }


    }
}