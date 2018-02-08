package com.rakangsoftware.architecture.screen.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakangsoftware.architecture.R;
import com.rakangsoftware.architecture.data.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mData = new ArrayList<>();
    private ViewHolder.OnContactClickListener mOnContactClickListener;

    public ContactAdapter(ViewHolder.OnContactClickListener onContactClickListener) {
        mOnContactClickListener = onContactClickListener;
        List<Contact> data = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Contact contact = new Contact();
            contact.setName("Name no." + i);
            contact.setSurename("Surename no." + i);
            data.add(contact);
        }
        setData(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.newInstance(parent, mOnContactClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(final List<Contact> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName;
        private final TextView mSurname;

        private Contact mContact;

        public ViewHolder(View itemView, final OnContactClickListener onContactClickListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
            mSurname = itemView.findViewById(R.id.surename);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onContactClickListener.onClicked(mContact);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    onContactClickListener.onLongClicked(mContact);
                    return true;
                }
            });
        }

        public void bind(Contact contact) {
            mContact = contact;

            mName.setText(contact.getName());
            mSurname.setText(contact.getName());
        }

        public interface OnContactClickListener {
            void onClicked(final Contact contact);

            void onLongClicked(final Contact contact);
        }

        public static ViewHolder newInstance(ViewGroup parent, OnContactClickListener onContactClickListener) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.contact_view,
                            parent,
                            false
                    ),
                    onContactClickListener
            );
        }
    }
}