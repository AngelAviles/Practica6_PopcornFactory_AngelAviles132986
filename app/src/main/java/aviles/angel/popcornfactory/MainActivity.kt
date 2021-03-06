package aviles.angel.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById<Button>(R.id.button_get_started)

        button.setOnClickListener {
            var intento: Intent = Intent(this, InicioActivity::class.java)
            startActivity(intento)
        }
    }
}