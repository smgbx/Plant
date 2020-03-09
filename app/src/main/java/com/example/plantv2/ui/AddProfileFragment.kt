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


class AddProfileFragment : Fragment() {

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

            val profile = Profile(profileName, profileSpecies, profileLocation)

            saveProfile(profile)

        }
    }

    private fun saveProfile(profile:Profile){
        class SaveProfile : AsyncTask<Void, Void, Void>(){

            override fun doInBackground(vararg p0: Void?): Void? {
               ProfileDatabase(activity!!).getProfileDao().addProfile(profile)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                Toast.makeText(activity, "Note Saved", Toast.LENGTH_LONG).show()
            }
        }

        SaveProfile().execute()
    }

}
