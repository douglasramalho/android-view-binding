package br.com.douglasmotta.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.douglasmotta.viewbinding.databinding.ItemNameBinding

class MainAdapter(private val names: MutableList<String>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class MainViewHolder(val binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textViewName = binding.itemNameValue

        fun bind(name: String) {
            textViewName.text = name

            binding.root.setOnClickListener {
                Toast.makeText(it.context, "Olá, $name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}