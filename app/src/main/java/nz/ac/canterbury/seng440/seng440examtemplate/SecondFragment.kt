package nz.ac.canterbury.seng440.seng440examtemplate

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import nz.ac.canterbury.seng440.seng440examtemplate.databinding.FragmentFirstBinding
import nz.ac.canterbury.seng440.seng440examtemplate.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(R.layout.fragment_second) {

    val viewModel: SharedViewModel by activityViewModels()

    lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.apply {
            tvQuestion.text = "The definition of ${viewModel.chosenWord.title} is:"

            radioButton1.setText(viewModel.chosenWordList.get(0).definition)
            radioButton2.setText(viewModel.chosenWordList.get(1).definition)
            radioButton3.setText(viewModel.chosenWordList.get(2).definition)
            radioButton4.setText(viewModel.chosenWordList.get(3).definition)

            radioButton1.setOnClickListener {
                checkIsCorrect(radioButton1.text.toString())
            }
            radioButton2.setOnClickListener {
                checkIsCorrect(radioButton2.text.toString())
            }

            radioButton3.setOnClickListener {
                checkIsCorrect(radioButton3.text.toString())
            }

            radioButton4.setOnClickListener {
                checkIsCorrect(radioButton4.text.toString())
            }

        }


        return binding.root
    }

    fun checkIsCorrect(definition: String) {
        val builder = AlertDialog.Builder(requireContext())
        if (definition.equals(viewModel.chosenWord.definition)) {
            builder.setMessage("You picked the right answer. Nice work!")
            builder.setPositiveButton("Okay") { _, _ ->
                viewModel.resetGame()

                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
            builder.create().show()
        } else {
            builder.setMessage("Uh oh. You might need to study more.")
            builder.setPositiveButton("TRY AGAIN") { _, _ ->
            }
            builder.create().show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.resetGame()
    }

}