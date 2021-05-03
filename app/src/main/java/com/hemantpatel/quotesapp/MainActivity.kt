package com.hemantpatel.quotesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    val textQuote: TextView
        get() = findViewById(R.id.quoteText)

    val textQuoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.getPreviousQuotes())
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.getNextQuotes())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            mainViewModel.getQuotes().text + " -" + mainViewModel.getQuotes().author
        )
        startActivity(intent)
    }

    fun setQuote(quotes: Quotes) {
        textQuote.text = quotes.text
        textQuoteAuthor.text = quotes.author
    }
}