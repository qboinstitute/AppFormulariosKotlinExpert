package qbo.com.appformularioskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btncalcular.setOnClickListener {
            if(etnota1.text.isEmpty()){
                etnota1.error = "Nota 1 obligatorio"
            }else if(etnota2.text.isEmpty()){
                etnota2.error = "Nota 2 obligatorio"
            }else if(etnota3.text.isEmpty()){
                etnota3.error = "Nota 3 obligatorio"
            }else{
                calcularNota()
                Toast.makeText(this, "Calcular nota",
                    Toast.LENGTH_LONG).show()
            }
        }
        btnirregistro.setOnClickListener {
            startActivity(Intent(this , RegistroActivity::class.java))
        }

    }

    private fun calcularNota(){
        val nota1 = etnota1.text.toString().toInt()
        val nota2 = etnota2.text.toString().toInt()
        val nota3 = etnota3.text.toString().toInt()
        val promedio = (nota1 + nota2 + nota3) / 3
        tvresultado.text = "El promedio simple es: ${promedio.toString()}"

    }

}