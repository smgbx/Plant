package com.example.plantv2.ui


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import com.example.plantv2.R
import com.example.plantv2.alarm.DatePickerHelper
import com.example.plantv2.alarm.TimePickerHelper
import com.example.plantv2.db.Profile
import com.example.plantv2.db.ProfileDatabase
import kotlinx.android.synthetic.main.fragment_add_profile.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*


class AddProfileFragment : BaseFragment() {

    private var profile: Profile? = null
    private lateinit var timePicker: TimePickerHelper
    private lateinit var datePicker: DatePickerHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        timePicker = TimePickerHelper(context!!, false)
        datePicker = DatePickerHelper(context!!)
        val calendar = Calendar.getInstance()

        arguments?.let {
            //display variables
            profile = AddProfileFragmentArgs.fromBundle(it).profile
            edit_text_name.setText(profile?.name)
            edit_text_species.setText(profile?.species)
            edit_text_location.setText(profile?.location)
            if (profile != null) {
                setTimeButtonText(profile!!.plantDate)
                setDateButtonText(profile!!.plantDate)
            }
        }

        buttonTime.setOnClickListener{
            timePicker.showDialog(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), object : TimePickerHelper.Callback {
                override fun onTimeSelected(hourOfDay: Int, minute: Int) {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    setTimeButtonText(calendar)
                }
            })
        }

        //calendarView.setOnDateChangeListener()
        buttonDate.setOnClickListener{
            datePicker.showDialog(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), object : DatePickerHelper.Callback {
                override fun onDateSelected(dayOfMonth: Int, month: Int, year: Int) {
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.YEAR, year)
                    setDateButtonText(calendar)
                }
            })
        }

        button_save.setOnClickListener{ view ->

            val profileName = edit_text_name.text.toString().trim()
            val profileSpecies = edit_text_species.text.toString().trim()
            val profileLocation = edit_text_location.text.toString().trim()
            val profileTime = calendar

            if(profileName.isEmpty()){
                edit_text_name.error = "Name required"
                edit_text_name.requestFocus()
                return@setOnClickListener
            }

            launch{
                context?.let {
                    val mProfile = Profile(profileName, profileSpecies, profileLocation, profileTime)

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

    private fun setTimeButtonText(calendar: Calendar) {
        val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        buttonTime!!.text = dateFormat.format(calendar.time)
    }

    private fun setDateButtonText(calendar: Calendar) {
        val dateFormat = SimpleDateFormat("E, M/d", Locale.getDefault())
        buttonDate!!.text = dateFormat.format(calendar.time)
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
