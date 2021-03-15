package aviles.angel.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_resumen_asiento.*

class ResumenAsiento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen_asiento)

        val bundle = intent.extras

        if (bundle != null) {

            iv_pelicula.setImageResource(bundle.getInt("imagen"))
            pelicula.setText(bundle.getString("titulo"))
            num_asiento.setText(bundle.getInt("numAsiento").toString())

            var asientos = bundle.getParcelableArrayList<Cliente>("asientos") as ArrayList<Cliente>
            var id = bundle.getInt("id")

            tipoPago.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId > -1) {
                    tipoPago.check(checkedId)
                }
            }

            reservar.setOnClickListener {

                var tipoDePago = ""

                if (tipoPago.getCheckedRadioButtonId() != -1) {

                    val radio: RadioButton = findViewById(tipoPago.checkedRadioButtonId)
                    when (radio.text.toString()) {
                        "Efectivo" -> tipoDePago = "Efectivo"
                        "Tarjeta" -> tipoDePago = "Tarjeta"
                    }
                }

                var cliente: Cliente = Cliente(nombre.text.toString(), tipoDePago, bundle.getInt("numAsiento"))
                asientos.add(cliente)

                var intent = Intent(this, InicioActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("asientos", asientos)
                this.startActivity(intent)
            }
        }
    }
}