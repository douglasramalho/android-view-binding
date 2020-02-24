package br.com.douglasmotta.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.douglasmotta.viewbinding.databinding.ItemNameBinding

class MainAdapter(private val names: MutableList<String>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: ItemNameBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(names[position])
    }

    fun addName(name: String) {
        names.add(name)
        notifyItemInserted(names.size - 1)
    }

    fun clearNames() {
        val itemCount = names.size
        names.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    class MainViewHolder(binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textViewName = binding.itemNameValue

        fun bind(name: String) {
            textViewName.text = name
        }
    }
}