package aviles.angel.popcornfactory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_detalle_pelicula.view.*
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.pelicula.view.*
import java.util.ArrayList

class InicioActivity : AppCompatActivity() {
    var adapterPeliculas: PeliculaAdapter? = null
    var adapterSeries: SerieAdapter? = null
    var peliculas = ArrayList<Pelicula>()
    var series = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        cargarPeliculas()

        adapterPeliculas = PeliculaAdapter(this, peliculas)
        adapterSeries = SerieAdapter(this, series)
        gv_peliculas.adapter = adapterPeliculas
        gv_series.adapter = adapterSeries
    }

    fun cargarPeliculas() {
        series.add(Pelicula(getString(R.string.dr_house), R.drawable.drhouse, R.drawable.househeader, getString(R.string.dr_house_desc)))
        series.add(Pelicula(getString(R.string.smallville), R.drawable.smallville, R.drawable.smallvilleheader, getString(R.string.smallville_desc)))
        series.add(Pelicula(getString(R.string.dr_who), R.drawable.drwho, R.drawable.drwhoheader, getString(R.string.dr_who_desc)))
        series.add(Pelicula(getString(R.string.bones), R.drawable.bones, R.drawable.bonesheader, getString(R.string.bones_desc)))
        series.add(Pelicula(getString(R.string.suits), R.drawable.suits, R.drawable.suitsheader, getString(R.string.suits_desc)))
        series.add(Pelicula(getString(R.string.friends), R.drawable.friends, R.drawable.friendsheader, getString(R.string.friends_desc)))
        peliculas.add(Pelicula(getString(R.string.p1917), R.drawable.p1917, R.drawable.p1917header, getString(R.string.p1917_desc)))
        peliculas.add(Pelicula(getString(R.string.big_hero_6), R.drawable.bighero6, R.drawable.headerbighero6, getString(R.string.big_hero_6_desc)))
        peliculas.add(Pelicula(getString(R.string.leap_year), R.drawable.leapyear, R.drawable.leapyearheader, getString(R.string.leap_year_desc)))
        peliculas.add(Pelicula(getString(R.string.men_in_black), R.drawable.mib, R.drawable.mibheader, getString(R.string.men_in_black_desc)))
        peliculas.add(Pelicula(getString(R.string.toy_story), R.drawable.toystory, R.drawable.toystoryheader, getString(R.string.toy_story_desc)))
        peliculas.add(Pelicula(getString(R.string.inception), R.drawable.inception, R.drawable.inceptionheader, getString(R.string.inception_desc)))
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
            context!!.startActivity(intent)
        }
        return vista
    }

}
