package com.example.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    companion object {
        const val STATE_SCORE_1 = "Team 1 Score"
        const val STATE_SCORE_2 = "Team 2 Score"
    }

    // Member variables for holding the score.
    var mScore1: Int = 0
    var mScore2: Int = 0
    lateinit var mScoreText1: TextView
    lateinit var mScoreText2: TextView

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)

        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mScoreText1 = findViewById(R.id.score_1)
        mScoreText2 = findViewById(R.id.score_2)

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            // Set the score text views.
            mScoreText1.setText(mScore1.toString());
            mScoreText2.setText(mScore2.toString());
        }
    }

    /**
     * Method that handles the onClick of both the increment buttons
     * @param view The button view that was clicked
     */
    fun increaseScore(view: View) {
        val viewID = view.id

        when (viewID) {
            R.id.increaseTeam1 -> {
                mScore1++
                mScoreText1.setText(mScore1.toString())
            }

            R.id.increaseTeam2 -> {
                mScore2++
                mScoreText2.setText(mScore2.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Change the label of the menu based on the state of the app.
        var nightMode = AppCompatDelegate.getDefaultNightMode()

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.day_mode)
        } else {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.night_mode)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Check if the correct item was clicked
        if (item.itemId == R.id.night_mode) {
            // Get the night mode state of the app.
            var nightMode = AppCompatDelegate.getDefaultNightMode()

            //Set the theme mode for the restarted activity.
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            // Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }

    /**
     * Method that handles the onClick of both the decrement buttons
     * @param view The button view that was clicked
     */
    fun decreaseScore(view: View) {
        val viewID = view.id

        when (viewID) {
            R.id.decreaseTeam1 -> {
                if (mScore1 == 0) return
                mScore1--
                mScoreText1.setText(mScore1.toString())
            }

            R.id.decreaseTeam2 -> {
                if (mScore2 == 0) return
                mScore2--
                mScoreText2.setText(mScore2.toString())
            }
        }
    }

}