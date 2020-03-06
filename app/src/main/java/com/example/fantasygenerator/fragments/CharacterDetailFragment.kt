package com.example.fantasygenerator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fantasygenerator.R
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.viewModels.CharacterDetailViewModel
import com.example.fantasygenerator.viewModels.CharacterDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {
    private lateinit var characterId: String

    companion object {
        private const val ARG_CHARACTER_ID = "characterId"
        fun newInstance(characterId: String): CharacterDetailFragment {
            val args = Bundle()
            args.putString(ARG_CHARACTER_ID, characterId)
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
        this.arguments?.let{
            if(it.containsKey(ARG_CHARACTER_ID)){
                characterId = it.getString(ARG_CHARACTER_ID)

                val characterRepository = CharacterRepository.getInstance(
                    AppDatabase.getInstance(context!!.applicationContext).characterDao())

                val characterDetailViewModel = ViewModelProvider(this,
                    CharacterDetailViewModelFactory(characterRepository, characterId))
                    .get(CharacterDetailViewModel::class.java)

                characterDetailViewModel.character.observe(this.viewLifecycleOwner, Observer { character ->
                    name.text = character.name.name
                    motivation.text = character.motivation.motivation
                    profession.text = character.profession.profession
                    positiveTrait.text = character.positiveTrait.trait
                    negativeTrait.text = character.negativeTrait.trait
                    neutralTrait.text = character.neutralTrait.trait
                })
            }
        }
    }
}