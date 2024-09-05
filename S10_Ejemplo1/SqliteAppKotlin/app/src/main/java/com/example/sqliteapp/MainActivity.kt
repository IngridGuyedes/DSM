package com.example.sqliteapp


import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteapp.db.HelperDB
import com.example.sqliteapp.model.Categoria
import com.example.sqliteapp.model.Productos

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var managerCategoria: Categoria? = null
    private var managerProductos: Productos? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var cursor: Cursor? = null

    private var txtIdDB: TextView? = null
    private var txtId: EditText? = null
    private var txtNombre: EditText? = null
    private var txtPrecio: EditText? = null
    private var txtCantidad: EditText? = null
    private var cmbCategorias: Spinner? = null

    private var btnAgregar: Button? = null
    private var btnActualizar: Button? = null
    private var btnEliminar: Button? = null
    private var btnBuscar: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtIdDB = findViewById(R.id.txtIdDB)
        txtId = findViewById(R.id.txtId)
        txtNombre = findViewById(R.id.txtNombre)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtCantidad = findViewById(R.id.txtCantidad)
        cmbCategorias = findViewById<Spinner>(R.id.cmbCategorias)

        btnAgregar = findViewById(R.id.btnAgregar)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnBuscar = findViewById(R.id.btnBuscar)

        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase

        setSpinnerCategorias()

        btnAgregar!!.setOnClickListener(this)
        btnActualizar!!.setOnClickListener(this)
        btnEliminar!!.setOnClickListener(this)
        btnBuscar!!.setOnClickListener(this)
    }

    fun setSpinnerCategorias() {
        // Cargando valores por defecto
        managerCategoria = Categoria(this)
        managerCategoria!!.insertValuesDefault()
        cursor = managerCategoria!!.showAllCategoria()

        var cat = ArrayList<String>()
        if (cursor != null && cursor!!.count > 0) {
            cursor!!.moveToFirst()
            cat.add(cursor!!.getString(1))
            do {
                cat.add(cursor!!.getString(1))
            } while (cursor!!.moveToNext())
        }

        var adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, cat)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbCategorias!!.adapter = adaptador
    }


    override fun onClick(view: View) {
        managerProductos = Productos(this)

        val nombre: String = txtNombre!!.text.toString().trim()
        val precio: String = txtPrecio!!.text.toString().trim()
        val cantidad: String = txtCantidad!!.text.toString().trim()
        val categoria: String = cmbCategorias!!.selectedItem.toString().trim()
        val idcategoria = managerCategoria!!.searchID(categoria)
        val idproducto = txtId!!.text.toString().trim()
        var idproducto_temp = txtIdDB!!.text.toString().trim()


        if (db != null) {
            if (view === btnAgregar) {
                if (vericarFormulario("insertar")) {
                    managerProductos!!.addNewProducto(
                        idcategoria,
                        nombre,
                        precio.toDouble(),
                        cantidad.toInt()
                    )
                    Toast.makeText(this, "Producto agregado", Toast.LENGTH_LONG).show()

                    txtNombre!!.setText("")
                    txtPrecio!!.setText("")
                    txtCantidad!!.setText("")
                    cmbCategorias!!.setSelection(0)

                }
            } else if (view === btnActualizar) {

                if (vericarFormulario("actualizar")) {

                    cursor = managerProductos!!.searchProducto(idproducto_temp.toInt())

                    if (cursor != null && cursor!!.count > 0) {

                        managerProductos!!.updateProducto(
                            idproducto_temp.toInt(),
                            idcategoria,
                            nombre,
                            precio.toDouble(),
                            cantidad.toInt()
                        )
                        Toast.makeText(this, "Producto actualizado", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(
                            this,
                            "No se ha encontrado el producto cargado",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            } else if (view === btnEliminar) {


                if (vericarFormulario("eliminar")) {

                    cursor = managerProductos!!.searchProducto(idproducto_temp.toInt())

                    if (cursor != null && cursor!!.count > 0) {
                        // Mensaje de confirmacion para eliminar un registro
                        val builder = AlertDialog.Builder(this@MainActivity)
                        builder.setMessage("Desea eliminar el producto ${nombre} ")
                            .setCancelable(false)
                            .setPositiveButton("Si") { dialog, id ->
                                
                                managerProductos!!.deleteProducto(idproducto_temp.toInt())
                                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_LONG).show()

                                txtNombre!!.setText("")
                                txtPrecio!!.setText("")
                                txtCantidad!!.setText("")
                                cmbCategorias!!.setSelection(0)
                            }
                            .setNegativeButton("No") { dialog, id ->
                                dialog.dismiss()
                            }
                        val alert = builder.create()
                        alert.show()


                    } else {
                        Toast.makeText(
                            this,
                            "No se ha encontrado el producto cargado",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            } else if (view === btnBuscar) {


                if (vericarFormulario("buscar")) {
                    cursor = managerProductos!!.searchProducto(idproducto.toInt())

                    if (cursor != null && cursor!!.count > 0) {
                        cursor!!.moveToFirst()
                        var label_categoria: String? =
                            managerCategoria!!.searchNombre(cursor!!.getInt(1))

                        // Guardando ID temporal
                        txtIdDB!!.text = (cursor!!.getInt(0).toString())
                        cmbCategorias!!.setSelection(cursor!!.getInt(1))
                        //cmbCategorias!!.setS
                        txtNombre!!.setText(cursor!!.getString(2))
                        txtPrecio!!.setText(cursor!!.getString(3))
                        txtCantidad!!.setText(cursor!!.getString(4))

                        Log.d("INFORMACION", cursor!!.getInt(1).toString())


                        Toast.makeText(this, "Cargando informacion", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_LONG)
                            .show()
                    }
                }

            } else {
                Toast.makeText(this, "No se puede conectar a la Base de Datos", Toast.LENGTH_LONG)
                    .show()
            }


        }

    }

    private fun vericarFormulario(opc: String): Boolean {
        var notificacion: String = "Se han generado algunos errores, favor verifiquelos"
        var response: Boolean = true
        var idproducto_v: Boolean = true
        var idcategoria_v: Boolean = true
        var nombre_v: Boolean = true
        var precio_v: Boolean = true
        var cantidad_v: Boolean = true

        val nombre: String = txtNombre!!.text.toString().trim()
        val precio: String = txtPrecio!!.text.toString().trim()
        val cantidad: String = txtCantidad!!.text.toString().trim()
        val categoria: String = cmbCategorias!!.selectedItem.toString().trim()
        val idproducto: String = txtId!!.text.toString().trim()
        var idproducto_temp = txtIdDB!!.text.toString().trim()


        if (opc === "insertar" || opc == "actualizar") {
            if (nombre.isEmpty()) {
                txtNombre!!.error = "Ingrese el nombre del producto"
                txtNombre!!.requestFocus()
                nombre_v = false
            }
            if (precio.isEmpty()) {
                txtPrecio!!.error = "Ingrese el precio del producto"
                txtPrecio!!.requestFocus()
                precio_v = false
            }

            if (cantidad.isEmpty()) {
                txtCantidad!!.error = "Ingrese la cantidad inicial"
                txtCantidad!!.requestFocus()
                cantidad_v = false
            }

            if (opc == "actualizar") {
                if (idproducto_temp.isEmpty()) {
                    idproducto_v = false
                    notificacion = "No se ha seleccionado un producto"
                }
                response =
                    !(nombre_v == false || precio_v == false || cantidad_v == false || idproducto_v == false)
            } else {
                response = !(nombre_v == false || precio_v == false || cantidad_v == false)
            }

        } else if (opc === "eliminar") {
            if (idproducto_temp.isEmpty()) {
                response = false
                notificacion = "No se ha seleccionado un producto"
            }
        } else if (opc === "eliminar") {
            if (idproducto.isEmpty()) {
                response = false
                notificacion = "No se ha seleccionado un producto"
            }
        }

        //Mostrar errores
        if (response == false) {
            Toast.makeText(
                this,
                notificacion,
                Toast.LENGTH_LONG
            ).show()
        }
        return response
    }
}