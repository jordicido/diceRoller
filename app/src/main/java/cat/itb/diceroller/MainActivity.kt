package cat.itb.diceroller

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

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
        R.drawable.dice_6
    )
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.roll_button)
        resetButton = findViewById(R.id.reset_button)
        leftDieImageView = findViewById(R.id.left_die)
        rightDieImageView = findViewById(R.id.right_die)

        mediaPlayer = MediaPlayer.create(this, R.raw.dicerolling)

        rollButton.setOnClickListener {
            rollDie(leftDieImageView)
            rollDie(rightDieImageView)
            checkForSixes()
        }

        resetButton.setOnClickListener {
            leftDieImageView.visibility = INVISIBLE
            rightDieImageView.visibility = INVISIBLE
        }

        leftDieImageView.setOnClickListener {
            rollDie(leftDieImageView)
            checkForSixes()
        }

        rightDieImageView.setOnClickListener {
            rollDie(rightDieImageView)
            checkForSixes()
        }
    }

    fun rollDie(dieImageView: ImageView) {
        val randomNumber = (0..5).random()
        dieImageView.setImageResource(diceArray[randomNumber])
        dieImageView.visibility = VISIBLE
        mediaPlayer.start()
        YoYo.with(Techniques.Shake).duration(600).playOn(dieImageView)
    }

    fun checkForSixes() {
        if (leftDieImageView.drawable.constantState == getDrawable(R.drawable.dice_6)?.constantState &&
            rightDieImageView.drawable.constantState == getDrawable(R.drawable.dice_6)?.constantState) {
            Toast.makeText(this, "Jackpot!", Toast.LENGTH_SHORT).show()

        }
    }
}