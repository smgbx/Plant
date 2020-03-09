package com.example.plantv2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantv2.R
import com.example.plantv2.db.Profile
import kotlinx.android.synthetic.main.profile_layout.view.*

class ProfileAdapter(val profiles : List<Profile>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_layout, parent, false)
        )
    }

    override fun getItemCount() = profiles.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.view.text_view_name.text = profiles[position].name
        holder.view.text_view_species.text = profiles[position].species
    }

    class ProfileViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}