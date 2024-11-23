package com.example.habits.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.databinding.HabitItemBinding

/**
 * RecyclerView adapter for displaying a list of Habits.
 *
 * The UI is based on the [HabitItemBinding].
 * We use the [HabitItem] as a model for the binding.
 */
class HabitListAdapter(private val viewModel: HabitListViewModel) :
    RecyclerView.Adapter<HabitListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<HabitItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HabitItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    /**
     * Updates the list of habits.
     *
     * @see [https://medium.com/quark-works/android-asynclistdiffer-recycleviews-best-friend-365f75d84f4]
     */
    fun updateHabits(habits: List<HabitItem>) {
        asyncListDiffer.submitList(habits)
    }

    class ViewHolder(
        private val binding: HabitItemBinding,
        private val viewModel: HabitListViewModel,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: HabitItem) {
            binding.titleTextView.text = habit.title
            binding.titleTextView.paint.isStrikeThruText = habit.isCompleted
            binding.descriptionTextView.text = habit.description
            binding.completeCheckBox.isChecked = habit.isCompleted
            binding.completeCheckBox.setOnClickListener {
                viewModel.toggleCompleted(habit.id)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<HabitItem>() {
        override fun areItemsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
            return oldItem == newItem
        }
    }
}