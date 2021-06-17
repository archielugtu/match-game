package nz.ac.canterbury.seng440.seng440examtemplate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    var chosenWord = Word("", "")
    var chosenWordList = mutableListOf<Word>()

    private var _words = MutableLiveData<MutableList<Word>>(arrayListOf())
    val words: LiveData<MutableList<Word>> = _words

    fun addWord(word: Word) {
        _words.value!!.add(word)
        _words.notifyObserver()
    }

    fun playMatch() {

        chosenWord = _words.value!![(0.._words.value!!.size-1).random()]
        chosenWordList.add(chosenWord)

        for (i in (0..2)) {
            var randomWord: Word = _words.value!![(0.._words.value!!.size-1).random()]

            while ((chosenWordList.contains(randomWord))) {
                randomWord = _words.value!![(0.._words.value!!.size-1).random()]
            }

            chosenWordList.add(randomWord)
        }
        chosenWordList.shuffle()
    }

    fun resetGame() {
        chosenWord = Word("", "")
        chosenWordList = mutableListOf()
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}