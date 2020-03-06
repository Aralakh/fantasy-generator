package com.example.fantasygenerator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fantasygenerator.R
import com.example.fantasygenerator.adapters.CharacterAdapter
import com.example.fantasygenerator.interfaces.AdapterOnClickListener
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.database.AppDatabase
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.viewModels.CharacterListViewModel
import com.example.fantasygenerator.viewModels.CharacterListViewModelFactory
import kotlinx.android.synthetic.main.fragment_character_list.view.*

class CharacterListFragment : Fragment(), AdapterOnClickListener {
    private lateinit var viewModel: CharacterListViewModel
    private lateinit var characterRepo: CharacterRepository

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_character_list, container, false)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        view.recyclerView.apply{
            val borderLine = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(borderLine)
        }

        characterRepo = CharacterRepository.getInstance(AppDatabase.getInstance(context!!.applicationContext).characterDao())

        val factory = CharacterListViewModelFactory(characterRepo)
        viewModel = ViewModelProvider(this, factory).get(CharacterListViewModel::class.java)

        val adapter = CharacterAdapter(context!!)
        adapter.adapterOnClickListener = this
        subscribeAdapter(adapter)
        view.recyclerView.adapter = adapter
        return view
    }

    private fun subscribeAdapter(adapter: CharacterAdapter) {
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            if(characters != null) adapter.setCharacters(characters)
        })
    }

    override fun itemClicked(character: Character) {
        val fragment = CharacterDetailFragment.newInstance(character.id)
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(tag)
            .commit()
    }
}