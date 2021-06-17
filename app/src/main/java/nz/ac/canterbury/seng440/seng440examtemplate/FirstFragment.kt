package nz.ac.canterbury.seng440.seng440examtemplate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import nz.ac.canterbury.seng440.seng440examtemplate.databinding.FragmentFirstBinding


class FirstFragment : Fragment(R.layout.fragment_first) {

    val viewModel: SharedViewModel by activityViewModels()

    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        val adapter = WordsAdapter(arrayListOf())

        binding.apply {
            rvHome.adapter = adapter
            rvHome.layoutManager = LinearLayoutManager(requireContext())

            btnAddNewWord.setOnClickListener {

                val valid: MutableList<Boolean> = mutableListOf()

                if(etvWord.text.isEmpty()) {
                    Toast.makeText(requireContext(), "New word cannot be empty", Toast.LENGTH_SHORT).show()

                } else if (etvDefinition.text.isEmpty()) {
                    Toast.makeText(requireContext(), "New definition cannot be empty", Toast.LENGTH_SHORT).show()

                } else {
                    val word = Word(
                        etvWord.text.toString(),
                        etvDefinition.text.toString()
                    )
                    viewModel.addWord(word)

                    etvWord.setText("")
                    etvDefinition.setText("")
                }
            }

            btnPlayMatch.setOnClickListener {
                if (viewModel.words.value!!.size < 4) {
                    Toast.makeText(requireContext(), "There must be at least 4 matches", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.playMatch()
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
            }

        }

        viewModel.words.observe(viewLifecycleOwner) { newWords ->
            adapter.setData(newWords)
        }


        return binding.root
    }

}