package com.example.onemoretime


import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.onemoretime.R.drawable.*


private const val Tag = "matching"
class matching12 : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    private var points = 0
    private var correctpairs = 0
    private lateinit var score: TextView
    private var name = ""
    private var clicked = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching12)

        val possibleImages = mutableListOf(cloud, fireflower, luigi, mario, mushroom, star)
        possibleImages.shuffle()

        val images = mutableListOf(possibleImages.get(0), possibleImages.get(1),
            possibleImages.get(2),possibleImages.get(3),possibleImages.get(4),
            possibleImages.get(5))


        // Add each image twice so we can create pairs
        images.addAll(images)
        // Randomize the order of images
        images.shuffle()

        score = findViewById(R.id.textView6)
        score.setText("Points: " + points)


        buttons = listOf(
            findViewById(R.id.imageButton29),
            findViewById(R.id.imageButton30),
            findViewById(R.id.imageButton31),
            findViewById(R.id.imageButton15),
            findViewById(R.id.imageButton16),
            findViewById(R.id.imageButton17),
            findViewById(R.id.imageButton35),
            findViewById(R.id.imageButton33),
            findViewById(R.id.imageButton18),
            findViewById(R.id.imageButton36),
            findViewById(R.id.imageButton34),
            findViewById(R.id.imageButton32),
        )

        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        findViewById<Button>(R.id.button102).setOnClickListener {

            if(indexOfSingleSelectedCard == null) {
                restoreCards()
                updateViews()
            }
        }

        findViewById<Button>(R.id.button100).setOnClickListener {
            finish()
            startActivity(getIntent())
            overridePendingTransition(0,0)
        }

        findViewById<Button>(R.id.button101).setOnClickListener {
            finish()
            val intent = Intent(this, Results::class.java)
            intent.putExtra("SCORE", points)
            intent.putExtra("NAME", name)
            startActivity(intent)
        }
        findViewById<Button>(R.id.endGame4).setOnClickListener {
            cards.forEachIndexed { index, card ->
                val button = buttons[index]
                card.isFaceUp = true;
                updateViews()
                toogleTryAgain()
            }
        }


        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked!!")
                // Update models
                updateModels(index)
                // Update the UI for the game
                updateViews()
            }
        }
    }
    private fun toogleTryAgain() {
        findViewById<Button>(R.id.button102).setOnClickListener {
            Toast.makeText(this, "Nah you gave up, start a new game", Toast.LENGTH_SHORT).show()

        }


    }
    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else R.drawable.cardback)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        // Error checking:
        if (card.isFaceUp) {

            Toast.makeText(this, "Nope", Toast.LENGTH_SHORT).show()
            return
        }
        if (indexOfSingleSelectedCard == null) {
            // 0 or 2 selected cards previously

            restoreCards()


            indexOfSingleSelectedCard = position
        } else {
            // exactly 1 card was selected previously
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null


        }
        card.isFaceUp = !card.isFaceUp



    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false

            }
        }
    }




    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "Nice Job!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            correctpairs++
            points += 2
            score.text = "Points: " + points

            if (correctpairs == 6) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Name for your score")

// Set up the input

// Set up the input
                val input = EditText(this)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT
                builder.setView(input)

// Set up the buttons

// Set up the buttons
                builder.setPositiveButton("Set Name",
                    DialogInterface.OnClickListener { dialog, which ->
                        name = input.text.toString()
                    })
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

                builder.show()
            }
        }
        else
            if (points > 0)
                points--
        score.text = "Points: " + points

    }


}
