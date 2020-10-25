package com.jordangellatly.starwarsvshred.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.starwarsvshred.R

class CharacterAdapter(
    private val characterDataset: MutableList<String>
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(), Filterable {
    private var filteredCharacters: MutableList<String> = characterDataset

    inner class CharacterViewHolder(
        characterListItem: View
    ) : RecyclerView.ViewHolder(characterListItem) {
        var nameTextView: TextView = characterListItem.findViewById(R.id.name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.nameTextView.text = filteredCharacters[position]
    }

    override fun getItemCount(): Int = filteredCharacters.size

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            filteredCharacters = if (constraint.isEmpty()) {
                characterDataset
            } else {
                val filteredList = ArrayList<String>()
                for (name in characterDataset) {
                    if (name.toLowerCase().startsWith(constraint)) {
                        filteredList.add(name)
                    }
                }
                filteredList
            }
            val results = FilterResults()
            results.values = filteredCharacters
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            filteredCharacters = results.values as ArrayList<String>
            notifyDataSetChanged()
        }
    }
}