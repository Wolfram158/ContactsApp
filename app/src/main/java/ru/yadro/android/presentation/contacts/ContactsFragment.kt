package ru.yadro.android.presentation.contacts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.yadro.android.R
import ru.yadro.android.databinding.FragmentContactsBinding
import ru.yadro.android.domain.entity.ContactOrLetter
import ru.yadro.android.presentation.App
import ru.yadro.android.presentation.Error
import ru.yadro.android.presentation.Initial
import ru.yadro.android.presentation.Result
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
        subscribeContacts()
    }

    private fun setupRecyclerView() {
        binding.contactsRv.adapter = adapter
    }

    @Suppress("UNCHECKED_CAST")
    private fun subscribeContacts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contacts.collectLatest {
                    when (it) {
                        is Error<*> -> {
                            Toast.makeText(
                                context,
                                getString(R.string.error_load_contacts),
                                Toast.LENGTH_LONG
                            ).show()
                            requireActivity().finish()
                        }

                        is Initial<*> -> {}
                        is Result<*> -> {
                            adapter.submitList(it.result as List<ContactOrLetter>)
                        }
                    }
                }
            }
        }
        viewModel.emitContacts()
    }

    companion object {
        fun newInstance() = ContactsFragment()
    }
}