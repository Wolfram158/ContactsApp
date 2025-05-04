package ru.yadro.android.presentation.contacts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.yadro.android.databinding.FragmentContactsBinding
import ru.yadro.android.presentation.App
import ru.yadro.android.presentation.ViewModelFactory
import ru.yadro.android.presentation.contacts.adapter.ContactsAdapter
import javax.inject.Inject

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding: FragmentContactsBinding
        get() = _binding ?: throw RuntimeException("FragmentContactsBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]
    }

    private lateinit var adapter: ContactsAdapter

    @Suppress("UNUSED")
    @Inject
    fun init(context: Context) {
        adapter = ContactsAdapter(context, onContactClickListener = { viewModel.call(it) })
    }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.contactsRv.adapter = adapter
        adapter.submitList(viewModel.getContacts())
    }

    companion object {
        fun newInstance() = ContactsFragment()
    }
}