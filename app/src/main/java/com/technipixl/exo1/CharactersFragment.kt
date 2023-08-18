package com.technipixl.exo1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.FragmentCharactersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersFragment : Fragment() {

    private var binding: FragmentCharactersBinding? = null
    private val marvelService by lazy { MarvelImplementation() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        getMarvelData()
    }

    private fun getMarvelData(){
        //se met sur le thread IO, execute sur le thread secondaire
        CoroutineScope(Dispatchers.IO).launch {
            //tâches en arriere-plan
            val response = marvelService.getMarvel()
            //retourne sur le main thread
            withContext(Dispatchers.Main){
                //Log.d("test", (response.body()?.data?.size ?: 0).toString())
                val charactersList = response.body()?.data?.results ?: emptyList()
                setupRecyclerView(charactersList)

            }
        }
    }

    private fun setupRecyclerView(charactersList: List<Marvel>) {
        val recyclerView = binding?.maRecyclerView
            recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            recyclerView?.adapter = MarvelCharViewAdapter(charactersList) { character ->
               // Log.d("test", "test")
                findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToComicsFragment(character.id.toLong()))
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}