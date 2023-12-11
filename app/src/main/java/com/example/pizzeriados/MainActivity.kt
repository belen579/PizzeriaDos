package com.example.pizzeriados

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.CheckBox
import android.widget.Toast
import com.example.pizzeriados.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File.separator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val lista = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.boton.setOnClickListener {

            binding.boton.text = "Validado"
            binding.boton.setBackgroundColor(Color.parseColor("#88C68B"))
            val ingredientesMap = mapOf(
                binding.atun to Ingredientes.Atun,
                binding.atun to Ingredientes.Tuna,
                binding.aceitunas to Ingredientes.Aceitunas,
                binding.aceitunas to Ingredientes.Olives,

                binding.extra to Ingredientes.Mozarella,
                binding.champiOnes to Ingredientes.Champiñones,
                binding.champiOnes to Ingredientes.Mushrooms,
                binding.piA to Ingredientes.Piña,
                binding.piA to Ingredientes.Pineapple,
                binding.checkBox to Ingredientes.Pepino,
                binding.checkBox to Ingredientes.Cucumber,
            )




            for ((vista, ingrediente) in ingredientesMap) {

                if (vista.isChecked) {
                    lista.add(ingrediente.toString())
                }
            }

            if (binding.recogida.isChecked) {
               binding.ticket.text  =
                    " Ingredientes en la pizza\n" + "" +lista.joinToString(separator = "\n" + " ") + "\nEscoje:\n Recogida En tienda"

                showalert()
            } else if (binding.reparto.isChecked) {
              binding.ticket.text =
                    " Ingredientes en la pizza\n" +""+ lista.joinToString(separator = "\n") + "\n  Escoje:\n Reparto a domicilio"
               showalert()
            } else {
                Toast.makeText(
                    this,
                    "No ha seleccionado ninguna opcion de recogida",
                    Toast.LENGTH_SHORT
                ).show()
            }




        }

        binding.limpiar.setOnClickListener(){
            binding.atun.isChecked = false
            binding.aceitunas.isChecked = false
            binding.extra.isChecked = false
            binding.champiOnes.isChecked = false
            binding.piA.isChecked = false
            binding.checkBox.isChecked = false
            binding.recogida.isChecked=false
            binding.reparto.isChecked=false
            binding.ticket.text=null
            binding.boton.text="Validar"
            binding.boton.setBackgroundColor(Color.parseColor("#FF673AB7"))

            lista.clear()

            Snackbar.make(binding.limpiar!!, "Datos Borrados", Snackbar.LENGTH_SHORT).show()

        }
    }


    fun showalert(){


        val alert = AlertDialog.Builder(this)

        alert.setTitle(R.string.Confirmacion)
        alert.setMessage(R.string.Desea_validar)


        alert.setPositiveButton(R.string.si){
                dialog, which->
            var toast = Toast.makeText(this, R.string.Validado, Toast.LENGTH_SHORT)
            toast.show()

          val ticketenviar = binding.ticket.text
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("Ticket",ticketenviar )
            startActivity(intent)





            dialog.dismiss()
        }

        alert.setNegativeButton(R.string.no){
                dialog, which->
            var toast = Toast.makeText(this, R.string.pedido_cancelado, Toast.LENGTH_SHORT)
            toast.show()
            dialog.dismiss()

            val intent = Intent(this, MainPortada::class.java);

            startActivity(intent)
        }

        alert.create()
        alert.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.share_action) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Pizzeria Pedido")
                type = "Text/plain"
            }
            try {
                startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No se puede", Toast.LENGTH_SHORT).show()
            }
        }

        if (item.itemId == R.id.Inicio_app) {
            val intent = Intent(this, MainPortada::class.java)
            startActivity(intent)


        }

        if (item.itemId == R.id.acerca) {
            Toast.makeText(this, "Realizado por Belén Bastos", Toast.LENGTH_SHORT).show()

        }

        if (item.itemId == R.id.Enlace_Pizzeria) {
            openWebPage(this, "https://www.piensosbastos.com/inicio")


        }
        return true
    }

    fun openWebPage(context: Context, url: String?) {
        try {
            if (!URLUtil.isValidUrl(url)) {
                Toast.makeText(context, " This is not a valid link", Toast.LENGTH_LONG).show()
            } else {
                //OPCION1
                // val intent = Intent (Intent.ACTION_VIEW, Uri.parse(url));

                //OPCION2
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)

                context.startActivity(intent)

            }
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                " You don't have any browser to open web page",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}



















