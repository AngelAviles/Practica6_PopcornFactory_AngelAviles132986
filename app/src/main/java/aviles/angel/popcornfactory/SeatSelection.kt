package aviles.angel.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_resumen_asiento.*

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val title: TextView = findViewById(R.id.titleSeats)
        var posMovie = -1
        var asientos = ArrayList<Cliente>()

        val bundle = intent.extras

        if (bundle != null) {
            title.setText(bundle.getString("titulo"))
            posMovie = bundle.getInt("id")
            asientos = bundle.getParcelableArrayList<Cliente>("asientos") as ArrayList<Cliente>
        }

        val confirm: Button = findViewById(R.id.confirm)

        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        var asientosOcupados = ArrayList<Int>()

        // Despliega cuales son los asientos ocupados
        for ( cliente in asientos ) {
            if (cliente.asiento >= 1 && cliente.asiento <= 5) {
                row1.get(cliente.asiento - 1).setBackgroundResource(R.drawable.radio_disabled)
            } else if (cliente.asiento >= 6 && cliente.asiento <= 10) {
                row2.get(cliente.asiento - 6).setBackgroundResource(R.drawable.radio_disabled)
            } else if (cliente.asiento >= 11 && cliente.asiento <= 15) {
                row3.get(cliente.asiento - 11).setBackgroundResource(R.drawable.radio_disabled)
            } else if (cliente.asiento >= 16 && cliente.asiento <= 20) {
                row4.get(cliente.asiento - 16).setBackgroundResource(R.drawable.radio_disabled)
            }

            asientosOcupados.add(cliente.asiento)
        }

        confirm.setOnClickListener {
            if (row1.getCheckedRadioButtonId() != -1 || row2.getCheckedRadioButtonId() != -1 || row3.getCheckedRadioButtonId() != -1 || row4.getCheckedRadioButtonId() != -1) {
                var numAsiento = -1

                if (row1.getCheckedRadioButtonId() != -1) {
                    val radio: RadioButton = findViewById(row1.checkedRadioButtonId)

                    when (radio.text.toString()) {
                        "1" -> numAsiento = 1
                        "2" -> numAsiento = 2
                        "3" -> numAsiento = 3
                        "4" -> numAsiento = 4
                        "5" -> numAsiento = 5
                    }
                } else if (row2.getCheckedRadioButtonId() != -1) {
                    val radio: RadioButton = findViewById(row2.checkedRadioButtonId)

                    when (radio.text.toString()) {
                        "6" -> numAsiento = 6
                        "7" -> numAsiento = 7
                        "8" -> numAsiento = 8
                        "9" -> numAsiento = 9
                        "10" -> numAsiento = 10
                    }
                } else if (row3.getCheckedRadioButtonId() != -1) {
                    val radio: RadioButton = findViewById(row3.checkedRadioButtonId)

                    when (radio.text.toString()) {
                        "11" -> numAsiento = 11
                        "12" -> numAsiento = 12
                        "13" -> numAsiento = 13
                        "14" -> numAsiento = 14
                        "15" -> numAsiento = 15
                    }
                } else if (row4.getCheckedRadioButtonId() != -1) {
                    val radio: RadioButton = findViewById(row4.checkedRadioButtonId)

                    when (radio.text.toString()) {
                        "16" -> numAsiento = 16
                        "17" -> numAsiento = 17
                        "18" -> numAsiento = 18
                        "19" -> numAsiento = 19
                        "20" -> numAsiento = 20
                    }
                }

                if (!asientosOcupados.contains(numAsiento)) {
                    var intent = Intent(this, ResumenAsiento::class.java)
                    intent.putExtra("titulo", bundle?.getString("titulo"))
                    intent.putExtra("numAsiento", numAsiento)
                    intent.putExtra("id", posMovie)
                    intent.putExtra("asientos", asientos)
                    intent.putExtra("imagen", bundle?.getInt("imagen"))
                    this.startActivity(intent)

                    //Toast.makeText(this, "Enjoy the movie! :D", Toast.LENGTH_LONG).show()
                }
            }
        }

        row1.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1 && !asientosOcupados.contains(checkedId)) {
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row1.check(checkedId)
            }
        }

        row2.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1 && !asientosOcupados.contains(checkedId)) {
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row2.check(checkedId)
            }
        }

        row3.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1 && !asientosOcupados.contains(checkedId)) {
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()

                row3.check(checkedId)
            }
        }

        row4.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1 && !asientosOcupados.contains(checkedId)) {
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()

                row4.check(checkedId)
            }
        }
    }
}