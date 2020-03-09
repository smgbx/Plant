package com.example.plantv2.ui


import android.os.AsyncTask
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.example.plantv2.R
import com.example.plantv2.db.Profile
import com.example.plantv2.db.ProfileDatabase
import kotlinx.android.synthetic.main.fragment_add_profile.*
import kotlinx.coroutines.launch


class AddProfileFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_save.setOnClickListener{

            val profileName = edit_text_name.text.toString().trim()
            val profileSpecies = edit_text_species.text.toString().trim()
            val profileLocation = edit_text_location.text.toString().trim()

            if(profileName.isEmpty()){
                edit_text_name.error = "Name required"
                edit_text_name.requestFocus()
                return@setOnClickListener
            }

            launch{
                val profile = Profile(profileName, profileSpecies, profileLocation)
                context?.let {
                    ProfileDatabase(it).getProfileDao().addProfile(profile)
                    it.toast("Profile saved")
                }
            }

        }

    }

}
