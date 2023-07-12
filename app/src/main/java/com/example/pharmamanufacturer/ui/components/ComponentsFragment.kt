package com.example.pharmamanufacturer.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pharmamanufacturer.databinding.FragmentComponentsBinding

class ComponentsFragment : Fragment() {

    private var _binding: FragmentComponentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val componentsViewModel =
            ViewModelProvider(this).get(ComponentsViewModel::class.java)

        _binding = FragmentComponentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textComponents
        componentsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}