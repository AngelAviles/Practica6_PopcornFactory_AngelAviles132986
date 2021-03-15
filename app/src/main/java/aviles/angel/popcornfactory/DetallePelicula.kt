package aviles.angel.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalle_pelicula.*

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val bundle = intent.extras
        var ns = 0
        var id = -1

        if (bundle != null) {
            ns = bundle.getInt("seatsNumber")

            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            iv_nombre_pelicula.setText(bundle.getString("titulo"))
            iv_pelicula_desc.setText(bundle.getString("sinopsis"))

            seatsLeft.setText("$ns seats available")
            id = bundle.getInt("pos")
        }

        if (ns == 0) {
            buyTickets.isEnabled = false
        } else {
            buyTickets.setOnClickListener {
                val intent: Intent = Intent(this, SeatSelection::class.java)
                intent.putExtra("id", id)
                intent.putExtra("imagen", bundle?.getInt("header"))
                intent.putExtra("titulo", bundle?.getString("titulo"))
                intent.putExtra("asientos", bundle?.getParcelableArrayList<Cliente>("asientos"))
                this.startActivity(intent)
            }
        }
    }
}