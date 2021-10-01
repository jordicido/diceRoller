package cat.itb.diceroller

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var rollButton: Button
    lateinit var resetButton: Button
    lateinit var leftDieImageView: ImageView
    lateinit var rightDieImageView: ImageView
    private val diceArray = arrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.roll_button)
        resetButton = findViewById(R.id.reset_button)
        leftDieImageView = findViewById(R.id.left_die)
        rightDieImageView = findViewById(R.id.right_die)

        rollButton.setOnClickListener {
            rollDie(leftDieImageView)
            rollDie(rightDieImageView)
        }

        resetButton.setOnClickListener {
            leftDieImageView.visibility = INVISIBLE
            rightDieImageView.visibility = INVISIBLE
        }

        leftDieImageView.setOnClickListener {
            rollDie(leftDieImageView)
        }

        rightDieImageView.setOnClickListener {
            rollDie(rightDieImageView)
        }
    }

    fun rollDie(dieImageView: ImageView) {
        var randomNumber = (0..5).random()
        dieImageView.setImageResource(diceArray[randomNumber])
        dieImageView.visibility = VISIBLE
    }
}