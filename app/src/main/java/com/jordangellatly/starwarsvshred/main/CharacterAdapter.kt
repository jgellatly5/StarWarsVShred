package com.jordangellatly.starwarsvshred.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.data.StarWarsCharacter

class CharacterAdapter(
    private val context: Context,
    private val characterDataset: MutableList<StarWarsCharacter>,
    private val listener: CharacterAdapterListener
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(), Filterable {
    private var filteredCharacters: MutableList<StarWarsCharacter> = characterDataset

    inner class CharacterViewHolder(
        characterListItem: View
    ) : RecyclerView.ViewHolder(characterListItem) {
        var nameTextView: TextView = characterListItem.findViewById(R.id.name_text_view)
        var favoriteIcon: ImageView = characterListItem.findViewById(R.id.favorite)
        init {
            favoriteIcon.setOnClickListener {
                handleFavoriteCharacter()
            }
            characterListItem.setOnClickListener {
                listener.onCharacterSelected(filteredCharacters[adapterPosition])
            }
        }
        private fun handleFavoriteCharacter() {
            val sharedPrefs = context.getSharedPreferences(nameTextView.text.toString(), Context.MODE_PRIVATE)
            if (sharedPrefs.getBoolean("is_favorite", false)) {
                with(sharedPrefs.edit()) {
                    putBoolean("is_favorite", false)
                    apply()
                }
                favoriteIcon.setImageResource(R.drawable.ic_star_outline)
                Toast.makeText(context, "${nameTextView.text} removed as a favorite", Toast.LENGTH_SHORT).show()
            } else {
                with(sharedPrefs.edit()) {
                    putBoolean("is_favorite", true)
                    apply()
                }
                favoriteIcon.setImageResource(R.drawable.ic_star)
                Toast.makeText(context, "${nameTextView.text} is now a favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.nameTextView.text = filteredCharacters[position].name
    }

    override fun getItemCount(): Int = filteredCharacters.size

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            filteredCharacters = if (constraint.isEmpty()) {
                characterDataset
            } else {
                val filteredList = ArrayList<StarWarsCharacter>()
                for (character in characterDataset) {
                    if (character.name.toLowerCase().startsWith(constraint)) {
                        filteredList.add(character)
                    }
                }
                filteredList
            }
            val results = FilterResults()
            results.values = filteredCharacters
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            filteredCharacters = results.values as ArrayList<StarWarsCharacter>
            notifyDataSetChanged()
        }
    }

    interface CharacterAdapterListener {
        fun onCharacterSelected(character: StarWarsCharacter)
    }
}