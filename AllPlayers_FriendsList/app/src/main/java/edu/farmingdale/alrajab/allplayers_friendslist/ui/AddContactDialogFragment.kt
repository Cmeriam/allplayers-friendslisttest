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


class AddContactDialogFragment : DialogFragment() {

  private var _binding: FragmentAddContactBinding? = null
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
       _binding = FragmentAddContactBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner) {
            val message = if(it == null){
                getString(R.string.added_contact)
            }else{
                getString(R.string.error, it.message)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
            }

        binding.buttonAdd.setOnClickListener{
            val username = binding.editTextFullName.text.toString().trim()

            if(username.isEmpty()){
                binding.editTextFullName.error = "This field is required"
                return@setOnClickListener
            }

            val contact = Contact()
            contact.username = username

            viewModel.addContact(contact)
        }
    }


}