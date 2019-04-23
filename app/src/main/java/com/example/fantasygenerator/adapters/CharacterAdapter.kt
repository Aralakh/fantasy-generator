package com.example.fantasygenerator.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fantasygenerator.R
import com.example.fantasygenerator.models.Character
import kotlinx.android.synthetic.main.fragment_character_detail.view.*

class CharacterAdapter(private val listener: (Character) -> Unit) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {
    private var characters: MutableList<Character> = mutableListOf()

    override fun onBindViewHolder(holder: CharacterHolder, position: Int){
        val character = characters[position]
        holder.apply {
            bind(character, listener)
            itemView.tag = character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterHolder(parent.inflate(R.layout.list_character_item))


    override fun getItemCount() = characters.size

    fun setCharacters(characters: MutableList<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character, listener: (Character) -> Unit) = with(itemView) {
            name.text = character.name
            setOnClickListener { listener(character) }
        }
    }


}