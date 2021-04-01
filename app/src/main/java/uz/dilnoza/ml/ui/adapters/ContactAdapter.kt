package uz.dilnoza.ml.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contact.view.*
import uz.dilnoza.ml.R
import uz.dilnoza.ml.room.entity.ContactData
import uz.dilnoza.ml.utils.SingleBlock
import uz.dilnoza.ml.utils.extentions.bindItem
import uz.dilnoza.ml.utils.extentions.inflate

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private val ls = ArrayList<ContactData>()
    private var listenerItem: SingleBlock<ContactData>? = null
    private var listenerEdit: SingleBlock<ContactData>? = null
    private var listenerDelete: SingleBlock<ContactData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_contact))
    override fun getItemCount(): Int = ls.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun submitList(data: List<ContactData>) {
        ls.clear()
        ls.addAll(data)
        notifyItemRangeRemoved(0, data.size)
    }

    fun removeItem(data: ContactData){
        val index = ls.indexOfFirst { it.id == data.id }
        ls.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(data: ContactData){
        val index = ls.indexOfFirst { it.id == data.id }
        ls[index] = data
        notifyItemChanged(index)
    }

    fun insertItem(data: ContactData){
        ls.add(data)
        notifyItemInserted(ls.size - 1)
    }

    fun setOnItemClickListener(block: SingleBlock<ContactData>) {
        listenerItem = block
    }

    fun setOnItemEditListener(block: SingleBlock<ContactData>) {
        listenerEdit = block
    }

    fun setOnItemDeleteListener(block: SingleBlock<ContactData>) {
        listenerDelete = block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init{
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(ls[adapterPosition]) }
                buttonMore.setOnClickListener { it ->
                    val menu = PopupMenu(context,it)
                    menu.inflate(R.menu.menu_more)
                    menu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.menuDelete -> listenerDelete?.invoke(ls[adapterPosition])
                            R.id.menuEdit -> listenerEdit?.invoke(ls[adapterPosition])
                        }
                        true
                    }
                    menu.show()
                }
            }
        }

        fun bind() = bindItem {
            val d = ls[adapterPosition]
            Name.text = d.name
            Number.text=d.number
        }
    }
}