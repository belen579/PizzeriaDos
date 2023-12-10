package com.example.pizzeriados

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast

class MainPortada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_portada)


        val button = findViewById<Button>(R.id.button);


        button.setOnClickListener { v ->

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            Toast.makeText(
                this,
                "Accediendo a la aplicación!",
                Toast.LENGTH_SHORT
            )
                .show()
        }



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


