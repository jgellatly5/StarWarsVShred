package com.jordangellatly.starwarsvshred.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.application.StarWarsApplication
import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
import com.jordangellatly.starwarsvshred.prefs.Const
import com.jordangellatly.starwarsvshred.ui.main.MainContract
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CharacterAdapter(
    private val context: Context,
    private val characterDataset: MutableList<StarWarsCharacter>,
    private val mainPresenter: MainContract.Presenter,
    private val app: StarWarsApplication
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(), Filterable {
    private var filteredCharacters: MutableList<StarWarsCharacter> = characterDataset

    inner class CharacterViewHolder(
        characterListItem: View
    ) : RecyclerView.ViewHolder(characterListItem), ViewHolderContract.View {

        @Inject
        lateinit var presenter: ViewHolderPresenter

        val nameTextView: TextView = characterListItem.findViewById(R.id.name_text_view)
        val favoriteIcon: ImageView = characterListItem.findViewById(R.id.favorite)

        init {
            app.starWarsComponent.inject(this)
            presenter.setView(this)

            favoriteIcon.setOnClickListener {
                presenter.storeFavoritePreferences(nameTextView.text.toString())
            }
            characterListItem.setOnClickListener {
                mainPresenter.showCharacterDetails(filteredCharacters[adapterPosition])
            }
        }

        override fun setStar() {
            favoriteIcon.setImageResource(R.drawable.ic_star)
        }

        override fun removeStar() {
            favoriteIcon.setImageResource(R.drawable.ic_star_outline)
        }

        override fun setFavoriteMessage() {
            Toast.makeText(context, "${nameTextView.text} is now a favorite", Toast.LENGTH_SHORT)
                .show()
        }

        override fun removeFavoriteMessage() {
            Toast.makeText(
                context,
                "${nameTextView.text} removed as a favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.nameTextView.text = filteredCharacters[position].name
        val sharedPreferences = context.getSharedPreferences(Const.FAVORITES, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(holder.nameTextView.text.toString(), false)) {
            holder.setStar()
        } else {
            holder.removeStar()
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