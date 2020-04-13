package com.example.plantv2.alarm

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import com.example.plantv2.R
import java.util.*

class DatePickerHelper(context: Context) {
    private var dialog: DatePickerDialog
    private var callback: Callback? = null
    private val listener = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        callback?.onDateSelected(dayOfMonth, monthOfYear, year)
    }
    init {
        val style = R.style.SpinnerDatePickerDialog
        val cal = Calendar.getInstance()
        dialog = DatePickerDialog(context, style, listener,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }
    fun showDialog(dayOfMonth: Int, month: Int, year: Int, callback: Callback?) {
        this.callback = callback
        dialog.datePicker.init(year, month, dayOfMonth, null)
        dialog.show()
    }
    fun setMinDate(minDate: Long) {
        dialog.datePicker.minDate = minDate
    }
    fun setMaxDate(maxDate: Long) {
        dialog.datePicker.maxDate = maxDate
    }
    interface Callback {
        fun onDateSelected(dayOfMonth: Int, month: Int, year: Int)
    }
}