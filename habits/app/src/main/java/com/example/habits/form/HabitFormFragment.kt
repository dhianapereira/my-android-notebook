package com.example.habits.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.habits.collections.HabitListAdapter
import com.example.habits.collections.HabitListViewModel
import com.example.habits.databinding.FragmentHabitFormBinding
import com.example.habits.databinding.FragmentHabitListBinding
import com.example.habits.dummy.MockHabits

class HabitFormFragment : Fragment() {

    private var _binding: FragmentHabitFormBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: HabitListAdapter

    private val viewModel: HabitListViewModel by activityViewModels {
        HabitListViewModel.Factory(MockHabits)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() {
        val name = binding.habitTitleInput.editText?.text.toString()
        val habitDaysSelected = binding.daysChipGroup.checkedChipIds
        viewModel.addHabit(name, habitDaysSelected)
        findNavController().navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}