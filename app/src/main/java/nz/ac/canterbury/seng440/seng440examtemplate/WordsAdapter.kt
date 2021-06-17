package nz.ac.canterbury.seng440.seng440examtemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.ac.canterbury.seng440.seng440examtemplate.databinding.CustomItemRowBinding

class WordsAdapter(var words: MutableList<Word>): RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {


    class WordsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = CustomItemRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item_row, parent, false)
        )
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val word = words[position]

        holder.binding.apply {
            tvWordName.text = "${word.title}: ${word.definition}"

            btnDelete.setOnClickListener {
                words.remove(word)
                notifyDataSetChanged()
            }
        }
    }

    fun setData(newWords: MutableList<Word>) {
        words = newWords
        notifyDataSetChanged()
    }

}