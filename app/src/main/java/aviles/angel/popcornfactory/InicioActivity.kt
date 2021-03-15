package aviles.angel.popcornfactory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.pelicula.view.*
import java.util.ArrayList

class InicioActivity : AppCompatActivity() {
    var adapterPeliculas: PeliculaAdapter? = null
    var adapterSeries: SerieAdapter? = null
    var peliculas = ArrayList<Pelicula>()
    var series = ArrayList<Pelicula>()

    // Arreglos de los asientos por peliculas y series
    companion object {
        var asientosDrHouse = ArrayList<Cliente>()
        var asientosSmallville = ArrayList<Cliente>()
        var asientosDrwho = ArrayList<Cliente>()
        var asientosBones = ArrayList<Cliente>()
        var asientosSuits = ArrayList<Cliente>()
        var asientosFriends = ArrayList<Cliente>()
        var asientosP1917 = ArrayList<Cliente>()
        var asientosBigHero6 = ArrayList<Cliente>()
        var asientosLeapYear = ArrayList<Cliente>()
        var asientosMib = ArrayList<Cliente>()
        var asientosToyStory = ArrayList<Cliente>()
        var asientosInception = ArrayList<Cliente>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        cargarAsientos()

        cargarPeliculas()

        adapterPeliculas = PeliculaAdapter(this, peliculas)
        adapterSeries = SerieAdapter(this, series)
        gv_peliculas.adapter = adapterPeliculas
        gv_series.adapter = adapterSeries
    }

    fun cargarAsientos() {
        val bundle = intent.extras

        if (bundle != null) {

            var asientos = bundle.getParcelableArrayList<Cliente>("asientos") as ArrayList<Cliente>
            var id = bundle.getInt("id")

            when (id) {
                0 -> asientosDrHouse = asientos
                1 -> asientosSmallville = asientos
                2 -> asientosDrwho = asientos
                3 -> asientosBones = asientos
                4 -> asientosSuits = asientos
                5 -> asientosFriends = asientos
                6 -> asientosP1917 = asientos
                7 -> asientosBigHero6 = asientos
                8 -> asientosLeapYear = asientos
                9 -> asientosMib = asientos
                10 -> asientosToyStory = asientos
                11 -> asientosInception = asientos
            }
        }
    }

    fun cargarPeliculas() {
        series.add(Pelicula(getString(R.string.dr_house), R.drawable.drhouse, R.drawable.househeader, getString(R.string.dr_house_desc), asientosDrHouse))
        series.add(Pelicula(getString(R.string.smallville), R.drawable.smallville, R.drawable.smallvilleheader, getString(R.string.smallville_desc), asientosSmallville))
        series.add(Pelicula(getString(R.string.dr_who), R.drawable.drwho, R.drawable.drwhoheader, getString(R.string.dr_who_desc), asientosDrwho))
        series.add(Pelicula(getString(R.string.bones), R.drawable.bones, R.drawable.bonesheader, getString(R.string.bones_desc), asientosBones))
        series.add(Pelicula(getString(R.string.suits), R.drawable.suits, R.drawable.suitsheader, getString(R.string.suits_desc), asientosSuits))
        series.add(Pelicula(getString(R.string.friends), R.drawable.friends, R.drawable.friendsheader, getString(R.string.friends_desc), asientosFriends))
        peliculas.add(Pelicula(getString(R.string.p1917), R.drawable.p1917, R.drawable.p1917header, getString(R.string.p1917_desc), asientosP1917))
        peliculas.add(Pelicula(getString(R.string.big_hero_6), R.drawable.bighero6, R.drawable.headerbighero6, getString(R.string.big_hero_6_desc), asientosBigHero6))
        peliculas.add(Pelicula(getString(R.string.leap_year), R.drawable.leapyear, R.drawable.leapyearheader, getString(R.string.leap_year_desc), asientosLeapYear))
        peliculas.add(Pelicula(getString(R.string.men_in_black), R.drawable.mib, R.drawable.mibheader, getString(R.string.men_in_black_desc), asientosMib))
        peliculas.add(Pelicula(getString(R.string.toy_story), R.drawable.toystory, R.drawable.toystoryheader, getString(R.string.toy_story_desc), asientosToyStory))
        peliculas.add(Pelicula(getString(R.string.inception), R.drawable.inception, R.drawable.inceptionheader, getString(R.string.inception_desc), asientosInception))
    }
}

class PeliculaAdapter: BaseAdapter {
    var peliculas = ArrayList<Pelicula>()
    var context: Context? = null

    constructor(context: Context, peliculas: ArrayList<Pelicula>) {
        this.context = context
        this.peliculas = peliculas
    }

    override fun getCount(): Int {
        return peliculas.size
    }

    override fun getItem(position: Int): Any {
        return peliculas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var pelicula = peliculas[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.pelicula, null)
        vista.iv_pelicula.setImageResource(pelicula.image)
        vista.iv_pelicula_nombre.setText(pelicula.titulo)

        vista.iv_pelicula.setOnClickListener {
            var intent = Intent(context, DetallePelicula::class.java)
            intent.putExtra("titulo", pelicula.titulo)
            intent.putExtra("header", pelicula.header)
            intent.putExtra("sinopsis", pelicula.sinopsis)
            intent.putExtra("seatsNumber", 20 - pelicula.seats.size)
            intent.putExtra("pos", position + 6)
            intent.putExtra("asientos", pelicula.seats)
            context!!.startActivity(intent)
        }

        return vista
    }

}

class SerieAdapter: BaseAdapter {
    var series = ArrayList<Pelicula>()
    var context: Context? = null

    constructor(context: Context, series: ArrayList<Pelicula>) {
        this.context = context
        this.series = series
    }

    override fun getCount(): Int {
        return series.size
    }

    override fun getItem(position: Int): Any {
        return series[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var serie = series[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.pelicula, null)
        vista.iv_pelicula.setImageResource(serie.image)
        vista.iv_pelicula_nombre.setText(serie.titulo)

        vista.iv_pelicula.setOnClickListener {
            var intent = Intent(context, DetallePelicula::class.java)
            intent.putExtra("titulo", serie.titulo)
            intent.putExtra("header", serie.header)
            intent.putExtra("sinopsis", serie.sinopsis)
            intent.putExtra("seatsNumber", 20 - serie.seats.size)
            intent.putExtra("pos", position)
            intent.putExtra("asientos", serie.seats)
            context!!.startActivity(intent)
        }
        return vista
    }

}
