package com.example.plantv2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.plantv2.R
import com.example.plantv2.db.Profile
import kotlinx.android.synthetic.main.profile_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class ProfileAdapter(private val profiles : List<Profile>) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class ProfileViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_layout, parent, false)
        )
    }
    // Replace the contents of a view (invoked by the layout manager); fetches data
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.view.text_view_name.text = profiles[position].name
        holder.view.text_view_species.text = profiles[position].species
        val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        holder.view.text_view_water_time.text = dateFormat.format(profiles[position].plantDate.time)

        //If user clicks on view (profile card), navigates to edit fragment
        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionAddProfile()
            action.profile = profiles[position]
            Navigation.findNavController(it).navigate(action)
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = profiles.size
}