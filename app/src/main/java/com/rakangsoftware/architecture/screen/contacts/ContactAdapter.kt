package com.rakangsoftware.architecture.screen.contacts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.rakangsoftware.architecture.R
import com.rakangsoftware.architecture.data.Contact

import java.util.ArrayList

class ContactAdapter(private val mOnContactClickListener: ViewHolder.OnContactClickListener) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var mData: List<Contact> = ArrayList()

    init {
        val data = ArrayList<Contact>()
        for (i in 0..99) {
            val contact = Contact()
            contact.name = "Name no." + i
            contact.surename = "Surename no." + i
            data.add(contact)
        }
        setData(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.newInstance(parent, mOnContactClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(data: List<Contact>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, onContactClickListener: OnContactClickListener) : RecyclerView.ViewHolder(itemView) {

        private val mName: TextView
        private val mSurname: TextView

        private var mContact: Contact? = null

        init {

            mName = itemView.findViewById(R.id.name)
            mSurname = itemView.findViewById(R.id.surename)

            itemView.setOnClickListener { onContactClickListener.onClicked(mContact) }

            itemView.setOnLongClickListener {
                onContactClickListener.onLongClicked(mContact)
                true
            }
        }

        fun bind(contact: Contact) {
            mContact = contact

            mName.text = contact.name
            mSurname.text = contact.name
        }

        interface OnContactClickListener {
            fun onClicked(contact: Contact?)

            fun onLongClicked(contact: Contact?)
        }

        companion object {

            fun newInstance(parent: ViewGroup, onContactClickListener: OnContactClickListener): ViewHolder {
                return ViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                                R.layout.contact_view,
                                parent,
                                false
                        ),
                        onContactClickListener
                )
            }
        }
    }
}