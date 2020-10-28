package com.jordangellatly.starwarsvshred.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
import com.jordangellatly.starwarsvshred.prefs.Const
import java.util.*
import kotlin.collections.ArrayList

class CharacterAdapter(
    private val context: Context,
    private val characterDataset: MutableList<StarWarsCharacter>,
    private val presenter: MainContract.Presenter
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(), Filterable {
    private var filteredCharacters: MutableList<StarWarsCharacter> = characterDataset

    inner class CharacterViewHolder(
        characterListItem: View
    ) : RecyclerView.ViewHolder(characterListItem) {
        val nameTextView: TextView = characterListItem.findViewById(R.id.name_text_view)
        val favoriteIcon: ImageView = characterListItem.findViewById(R.id.favorite)
        init {
            favoriteIcon.setOnClickListener {
                handleFavoriteCharacter()
            }
            characterListItem.setOnClickListener {
                presenter.showCharacterDetails(filteredCharacters[adapterPosition])
            }
        }

        private fun handleFavoriteCharacter() {
            val sharedPrefs = context.getSharedPreferences(Const.FAVORITES, Context.MODE_PRIVATE)
            val isFavorite = sharedPrefs.getBoolean(nameTextView.text.toString(), false)
            if (isFavorite) {
                favoriteIcon.setImageResource(R.drawable.ic_star_outline)
                Toast.makeText(context, "${nameTextView.text} removed as a favorite", Toast.LENGTH_SHORT).show()
            } else {
                favoriteIcon.setImageResource(R.drawable.ic_star)
                Toast.makeText(context, "${nameTextView.text} is now a favorite", Toast.LENGTH_SHORT).show()
            }
            with(sharedPrefs.edit()) {
                putBoolean(nameTextView.text.toString(), !isFavorite)
                apply()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.nameTextView.text = filteredCharacters[position].name
        val sharedPreferences = context.getSharedPreferences(Const.FAVORITES, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(holder.nameTextView.text.toString(), false)) {
            holder.favoriteIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.favoriteIcon.setImageResource(R.drawable.ic_star_outline)
        }
    }

    override fun getItemCount(): Int = filteredCharacters.size

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            filteredCharacters = if (constraint.isEmpty()) {
                characterDataset
            } else {
                val filteredList = ArrayList<StarWarsCharacter>()
                for (character in characterDataset) {
                    if (character.name.toLowerCase(Locale.getDefault()).startsWith(constraint)) {
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

    fun clear() {
        filteredCharacters.clear()
        notifyDataSetChanged()
    }
}