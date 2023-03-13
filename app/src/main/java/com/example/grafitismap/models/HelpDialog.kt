package com.example.grafitismap.models

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.grafitismap.R
import com.example.grafitismap.viewmodel.RegisterViewModel

class HelpDialogFragment : DialogFragment() {

    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Quieres cerrar sesión?")
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        viewModel.logout()
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id -> })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
