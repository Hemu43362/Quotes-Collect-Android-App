package com.hemantpatel.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {

    private var quoteList: Array<Quotes> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesList()
    }

    private fun loadQuotesList(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val buffer = ByteArray(inputStream.available()/*size for byteArray*/)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun getQuotes() = quoteList[index]
    fun getNextQuotes(): Quotes {
        return if (index == quoteList.size - 1) {
            index = 0
            quoteList[index]
        } else quoteList[++index]
    }

    fun getPreviousQuotes(): Quotes {
        return if (index == 0) {
            index = quoteList.size - 1
            quoteList[index]
        } else quoteList[--index]
    }
}