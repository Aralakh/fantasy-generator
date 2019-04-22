package com.example.fantasygenerator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fantasygenerator.R
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.viewModels.CharacterDetailViewModel
import com.example.fantasygenerator.viewModels.CharacterDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_character_detail.*
import java.util.*

class CharacterDetailFragment : Fragment() {
    private lateinit var characterId: UUID

    companion object {
        private val ARG_CHARACTER_ID = "characterId"
        fun newInstance(characterId: UUID): CharacterDetailFragment {
            val args = Bundle()
            args.putSerializable(ARG_CHARACTER_ID, characterId)
            val fragment = CharacterDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let{
            if(it.containsKey(ARG_CHARACTER_ID)){
                characterId = it.getSerializable(ARG_CHARACTER_ID) as UUID

                val characterRepository = CharacterRepository.getInstance(
                    AppDatabase.getInstance(context!!.applicationContext).characterDao())

                val characterDetailViewModel = ViewModelProviders.of(this,
                    CharacterDetailViewModelFactory(characterRepository, characterId))
                    .get(CharacterDetailViewModel::class.java)

                characterDetailViewModel.character.observe(this, Observer { character ->
                    name.text = character.name
                    motivation.text = character.motivation
                    profession.text = character.profession
                    positiveTrait.text = character.traits.positiveTrait
                    negativeTrait.text = character.traits.negativeTrait
                    neutralTrait.text = character.traits.neutralTrait
                })
            }
        }
    }
}