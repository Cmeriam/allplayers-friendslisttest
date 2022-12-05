package edu.farmingdale.alrajab.allplayers_friendslist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import edu.farmingdale.alrajab.allplayers_friendslist.R
import edu.farmingdale.alrajab.allplayers_friendslist.data.Contact
import edu.farmingdale.alrajab.allplayers_friendslist.databinding.FragmentAddContactBinding
import edu.farmingdale.alrajab.allplayers_friendslist.databinding.FragmentUpdateContactBinding


class UpdateContactDialogFragment(private val contact: Contact) : DialogFragment() {

  private var _binding: FragmentUpdateContactBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentUpdateContactBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.editTextFullName.setText(contact.username)

        binding.buttonUpdate.setOnClickListener{
            val username = binding.editTextFullName.text.toString().trim()

            if(username.isEmpty()){
                binding.editTextFullName.error = "This field is required"
                return@setOnClickListener
            }

            contact.username = username

            viewModel.updateContact(contact)
            dismiss()
            Toast.makeText(context, "Contact has been updated", Toast.LENGTH_SHORT).show()

        }
    }


}