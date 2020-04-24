package com.example.plantv2.alarm

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.plantv2.R

class NotificationHelper : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (arguments != null)
        {
            if (arguments?.getBoolean("notAlertDialog")!!)
            {
                return super.onCreateDialog(savedInstanceState)
            }
        }

        val builder = AlertDialog.Builder(context!!).apply {
            setTitle("Are you sure you want to delete this plant?")
            setMessage("You can't undo this operation.")
            setPositiveButton("Yes"){_,_ ->
                dismiss()
            }
            setNegativeButton("No"){_,_ ->
                dismiss()
            }
        }

/*        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Alert Dialog")
        builder.setMessage("Hello! I am Alert Dialog")
        builder.setPositiveButton("Cool", object: DialogInterface.OnClickListener {
            override fun onClick(dialog:DialogInterface, which:Int) {
                dismiss()
            }
        })
        builder.setNegativeButton("Cancel", object: DialogInterface.OnClickListener {
            override fun onClick(dialog:DialogInterface, which:Int) {
                dismiss()
            }
        })*/
        return builder.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDone = view.findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener {
            dismiss()
        }
    }

    interface DialogListener {
        //fun onFinishEditDialog(inputText:String)
    }
}