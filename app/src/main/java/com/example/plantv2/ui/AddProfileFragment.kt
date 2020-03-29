package com.example.plantv2.ui


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import com.example.plantv2.R
import com.example.plantv2.alarm.TimePickerHelper
import com.example.plantv2.db.Profile
import com.example.plantv2.db.ProfileDatabase
import kotlinx.android.synthetic.main.fragment_add_profile.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class AddProfileFragment : BaseFragment() {

    private var profile: Profile? = null
    private lateinit var timePicker: TimePickerHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        timePicker = TimePickerHelper(context!!, false)

        arguments?.let {
            profile = AddProfileFragmentArgs.fromBundle(it).profile
            edit_text_name.setText(profile?.name)
            edit_text_species.setText(profile?.species)
            edit_text_location.setText(profile?.location)
            setTimeButtonText(profile!!.hour, profile!!.minute)
        }

        buttonTime.setOnClickListener{
            showTimePickerDialog()
        }

        button_save.setOnClickListener{ view ->

            val profileName = edit_text_name.text.toString().trim()
            val profileSpecies = edit_text_species.text.toString().trim()
            val profileLocation = edit_text_location.text.toString().trim()
            val profileHour = profile!!.hour
            val profileMinute = profile!!.minute

            if(profileName.isEmpty()){
                edit_text_name.error = "Name required"
                edit_text_name.requestFocus()
                return@setOnClickListener
            }

            launch{
                context?.let {
                    val mProfile = Profile(profileName, profileSpecies, profileLocation, profileHour, profileMinute)

                    if(profile == null){
                        ProfileDatabase(it).getProfileDao().addProfile(mProfile)
                        it.toast("Profile saved")
                    }else{
                        mProfile.id = profile!!.id
                        ProfileDatabase(it).getProfileDao().updateProfile(mProfile)
                        it.toast("Profile updated")
                    }

                    val action = AddProfileFragmentDirections.actionSaveProfile()
                    Navigation.findNavController(view).navigate(action)
                }
            }

        }

    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        timePicker.showDialog(h, m, object : TimePickerHelper.Callback {
            override fun onTimeSelected(hourOfDay: Int, minute: Int) {
                setTimeButtonText(hourOfDay, minute)
            }
        })
    }

    private fun setTimeButtonText(hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        buttonTime!!.text = dateFormat.format(calendar.time)
    }

    private fun deleteProfile(){
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure you want to delete this plant?")
            setMessage("You can't undo this operation.")
            setPositiveButton("Yes"){_,_ ->
                launch {
                    ProfileDatabase(context).getProfileDao().deleteProfile(profile!!)
                    val action = AddProfileFragmentDirections.actionSaveProfile()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
            setNegativeButton("No"){_,_ ->

            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> if(profile != null) deleteProfile() else context?.toast("Cannot delete null profile.")
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }


}
