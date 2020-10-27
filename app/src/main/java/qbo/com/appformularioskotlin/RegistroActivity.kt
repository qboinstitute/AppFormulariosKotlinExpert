package qbo.com.appformularioskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    private val listaPreferencias = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        chkdeporte.setOnClickListener {
            agregarQuitarPreferencias(it)
        }
        chkarte.setOnClickListener {
            agregarQuitarPreferencias(it)
        }
        chkotros.setOnClickListener {
            agregarQuitarPreferencias(it)
        }

        btnregistrar.setOnClickListener {
            if(validarFormulario()){
                enviarMensaje( etnombre.text.toString()+" " +
                        etapellido.text.toString() +" "+
                        obtenerGenero() + " "+
                        obtenerPreferencias()+ " "+
                        obtenerUsuarioVip())
                setearControles()
            }
        }
    }

    fun obtenerUsuarioVip(): String{
        var vip = "No es usuario VIP"
        if(swvip.isChecked){
            vip = "Usuario VIP"
        }
        return vip
    }

    fun obtenerGenero(): String{
        var genero = ""
        when(rggenero.checkedRadioButtonId){
            R.id.rbtnmasculino -> {
                genero = rbtnmasculino.text.toString()
            }
            R.id.rbtnfemenino -> {
                genero = rbtnfemenino.text.toString()
            }
        }
        return genero
    }

    fun obtenerPreferencias(): String{
        var preferencias = ""
        for(pref in listaPreferencias){
            preferencias += "$pref -"
        }
        return preferencias
    }

    fun agregarQuitarPreferencias(vista : View){
        if(vista is CheckBox){
            val check: Boolean = vista.isChecked
            when(vista.id){
                R.id.chkdeporte ->{
                    if(check){
                        listaPreferencias.add(vista.text.toString())
                    }else{
                        listaPreferencias.remove(vista.text.toString())
                    }
                }
                R.id.chkarte ->{
                    if(check){
                        listaPreferencias.add(vista.text.toString())
                    }else{
                        listaPreferencias.remove(vista.text.toString())
                    }
                }
                R.id.chkotros ->{
                    if(check){
                        listaPreferencias.add(vista.text.toString())
                    }else{
                        listaPreferencias.remove(vista.text.toString())
                    }
                }
            }
        }
    }

    fun validarGenero() : Boolean{
        var respuesta = true
        if(rggenero.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    fun validarPreferencias(): Boolean{
        var respuesta = false
        if(chkdeporte.isChecked || chkarte.isChecked || chkotros.isChecked){
            respuesta = true
        }
        return respuesta
    }

    fun validarNombreApellido(): Boolean{
        var respuesta = true
        if(etnombre.text.toString().trim().isEmpty()){
            etnombre.isFocusableInTouchMode = true
            etnombre.requestFocus()
            respuesta = false
        }else if(etapellido.text.toString().trim().isEmpty()){
            etapellido.isFocusableInTouchMode = true
            etapellido.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun enviarMensaje(mensaje : String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    fun validarFormulario(): Boolean{
        var respuesta = false
        if(!validarNombreApellido()){
            enviarMensaje("Ingrese su nombre y apellido")
        } else if(!validarGenero()){
            enviarMensaje("Seleccione género")
        }else if(!validarPreferencias()){
            enviarMensaje("Seleccione una preferencia")
        }else{
            respuesta = true
        }
        return respuesta
    }

    fun setearControles(){
        listaPreferencias.clear()
        etnombre.setText("")
        etapellido.setText("")
        swvip.isChecked = false
        chkdeporte.isChecked = false
        chkotros.isChecked = false
        chkarte.isChecked = false
        rggenero.clearCheck()
        etnombre.isFocusableInTouchMode = true
        etnombre.requestFocus()
    }

}