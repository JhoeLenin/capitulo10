# Layouts

Un **Layout** (o `ViewGroup`) es un contenedor que organiza elementos visuales (`Views` y otros `Layouts`) en la pantalla. Cada Layout tiene una estrategia diferente: `LinearLayout` apila elementos linealmente, `FrameLayout` superpone, `RelativeLayout` posiciona relativamente, `ConstraintLayout` usa restricciones, `GridLayout` organiza en cuadrícula y `TableLayout` en tablas.

Este capítulo explora cada tipo de Layout disponible en Android Studio: **`LinearLayout`** para apilamientos lineales, **`FrameLayout`** para superposición y Fragments, **`TableLayout`** para tablas de datos, **`RelativeLayout`** para posicionamiento relativo, **`ConstraintLayout`** para diseño moderno basado en restricciones, **`GridLayout`** para cuadrículas, y **`Space`** para separadores invisibles. Se comienza con una visión general del Palette Layouts, cómo elegir el Layout correcto, y luego se desarrollan ejemplos funcionales replicables para laboratorio de estudiantes, cada uno en su propio archivo XML y Activity.

---

## El Palette Layouts

El **Palette Layouts** es el panel que contiene todos los contenedores disponibles para organizar elementos visuales en la interfaz.

![Palette Layouts mostrando las categorías: Linear Layout, Frame Layout, Relative Layout, Constraint Layout, Table Layout, Grid Layout, Space.](img/cap11/palette_layouts_overview.png)

### Estructura del Palette Layouts

El Palette está organizado en categorías colapsables:

- **Linear** — `LinearLayout` (horizontal/vertical)
- **Frame** — `FrameLayout` (superposición)
- **Relative** — `RelativeLayout` (posicionamiento relativo)
- **Constraint** — `ConstraintLayout` (restricciones, el más moderno)
- **Table** — `TableLayout`, `TableRow`
- **Grid** — `GridLayout`
- **Spacer** — `Space` (separador invisible)

Para acceder al Palette:

1. Abre un archivo de layout (`activity_main.xml` o similar) en el editor visual.
2. En el panel izquierdo, verás la pestaña **Palette**.
3. Expande la categoría **Layouts** para ver los contenedores disponibles.

::: note
> El Palette solo aparece cuando tienes abierto un archivo XML de layout. Si ves un editor de código Kotlin, el Palette no estará visible.
:::

---

## Visión General de los Layouts Disponibles

Cada Layout tiene un propósito específico. La siguiente tabla resume sus características y casos de uso principales:

<div class="tabla" style="--col1:20%; --col2:30%; --col3:50%;">
| Layout | Propósito | Casos de Uso |
| :--- | :--- | :--- |
| **LinearLayout** | Apilar elementos en fila o columna | Formularios, menús, listas simples |
| **FrameLayout** | Superponer elementos | Contenedor de Fragments, overlays, badges |
| **RelativeLayout** | Posicionar relativamente al padre o a otros elementos | Cards, diseños asimétricos (deprecado) |
| **ConstraintLayout** | Restricciones flexibles | Diseños complejos, responsive, sin anidamiento profundo |
| **TableLayout** | Tablas de datos | Reportes, facturas, grillas simples |
| **GridLayout** | Cuadrícula regular | Calculadora, galería de iconos, tableros de juego |
| **Space** | Separador invisible | Espaciado entre elementos en lugar de margins |
</div>

### Cómo Elegir el Layout Correcto

El siguiente diagrama de flujo ayuda a decidir qué Layout usar según la necesidad del diseño.

```{.text .numberLines}
¿Elementos en línea o columna?
├─ SÍ → LinearLayout (orientación vertical u horizontal)
└─ NO
   ├─ ¿Superponer elementos (Fragments, overlays)?
   │  └─ SÍ → FrameLayout
   └─ ¿Posicionamiento complejo y responsive?
      ├─ SÍ → ConstraintLayout (recomendado para nuevos proyectos)
      ├─ NO, pero relativo al padre o a otros elementos
      │  └─ RelativeLayout (solo para código legacy)
      └─ ¿Tabla de datos o cuadrícula regular?
         ├─ Tabla → TableLayout
         ├─ Cuadrícula → GridLayout
```

::: tip
> **ConstraintLayout es la opción recomendada por Google para la mayoría de los diseños modernos.** Reduce el anidamiento, mejora el rendimiento y permite diseños totalmente responsivos.
:::

---

## Agregar un Layout al Proyecto

Existen dos formas de añadir un Layout al proyecto: mediante **arrastrar y soltar** en el editor visual o escribiendo **XML directamente**.

### Método 1: Arrastrar y Soltar (`Drag & Drop`)

Este método es útil para principiantes o para prototipado rápido, ya que permite ver el contenedor en tiempo real mientras se arrastra.

1. **Abre el layout en el editor visual** — `activity_main.xml` en vista **Design**.
2. **Localiza el Layout en el Palette** — Expande la categoría **Layouts**.
3. **Arrastra hacia el editor** — Verás una previsualización del contenedor.
4. **Suéltalo donde quieras** — Se inserta en la jerarquía.

![Editor visual mostrando un LinearLayout siendo arrastrado desde el Palette hacia el canvas. La zona de destino se resalta en verde.](img/cap11/drag_drop_layout.png)

### Método 2: XML Directo

Escribir el XML directamente en la pestaña **Code** es más preciso y rápido una vez que conoces la sintaxis.

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- Contenido principal -->
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">
        
    </androidx.constraintlayout.widget.ConstraintLayout>

    <!-- Tabla al final -->
    <TableLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <!-- Filas -->
    </TableLayout>

</LinearLayout>
```

::: tip
> La estructura anidada (un Layout raíz que contiene otros Layouts) es normal, pero **evita anidar más de 3-4 niveles** porque el rendimiento se degrada. Para diseños complejos, usa `ConstraintLayout`.
:::

**Resumen rápido:**

<div class="tabla" style="--col1:10%; --col2:25%; --col3:65%;">

| Paso | Acción | Detalle |
| :--- | :--- | :--- |
| 1 | Elegir Layout | LinearLayout, ConstraintLayout, etc., según necesidad |
| 2 | Agregar al proyecto | Arrastra desde Palette o escribe XML |
| 3 | Configurar atributos | `orientation`, `gravity`, `layout_weight`, constraints, etc. |
| 4 | Agregar elementos hijos | Views, otros Layouts dentro |
| 5 | Probar en preview | Pestaña **Design** muestra resultado visual |

</div>

---

## `LinearLayout`

`LinearLayout` apila elementos en una línea — horizontal o vertical. Es el Layout más simple y versátil, ideal para formularios, menús y listas simples.

![Ejemplo visual de LinearLayout vertical (elementos apilados uno debajo de otro) y horizontal (elementos en fila).](img/cap11/linear_layout_example.png)

### Definición en XML

A continuación se muestran las dos orientaciones básicas de `LinearLayout`: vertical (por defecto) y horizontal. Cada ejemplo debe guardarse en su propio archivo XML.

**Archivo:** `res/layout/activity_linear_basic.xml`

```{.xml .numberLines}
<!-- Vertical (por defecto) -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
	tools:context=".MainActivity" 
	android:id="@+id/main">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Título" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Contraseña" />

    <Button
		android:id ="@+id/btn_enviar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Enviar" />

</LinearLayout>

<!-- Horizontal -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal"
    android:padding="16dp"
	tools:context=".MainActivity" 
	android:id="@+id/main">

    <ImageView
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:src="@drawable/ic_avatar" />

    <TextView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Nombre del usuario" />

    <Button
		android:id ="@+id/btn_enviar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Seguir" />

</LinearLayout>
```

**Activity correspondiente:** `LinearBasicActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class LinearBasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_basic)

        val btnEnviar = findViewById<Button>(R.id.btn_enviar) // Asumiendo ID btn_enviar
        btnEnviar.setOnClickListener {
            Log.d("LinearBasic", "Botón Enviar clickeado")
            Toast.makeText(this, "Formulario enviado", Toast.LENGTH_SHORT).show()
        }
    }
}
```

### Atributos Esenciales

Los siguientes atributos controlan la disposición y el comportamiento del `LinearLayout` y sus hijos.

<div class="tabla" style="--col1:32%; --col2:28%; --col3:40%;">

| Atributo | Valores | Descripción |
| :--- | :--- | :--- |
| `android:orientation` | `vertical`, `horizontal` | Dirección de apilamiento |
| `android:gravity` | `top`, `center`, `bottom`, `start`, `end` | Alineación de los hijos dentro del Layout |
| `android:layout_weight` | `0`, `1`, `2`, etc. | Proporción de espacio que ocupa un hijo (requiere `width` o `height = 0dp`) |
| `android:weightSum` | `1.0`, `100`, etc. | Suma total de pesos (opcional, se calcula automáticamente) |
| `android:padding` | `16dp` | Espacio interior del Layout |
| `android:layout_margin` | `8dp` | Espacio exterior de un elemento hijo |

</div>

### LinearLayout Vertical — Ejemplo: Formulario de Login

**Archivo XML:** `res/layout/activity_linear_login.xml`

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp"
    android:gravity="center">

    <ImageView
        android:id="@+id/img_logo"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/ic_app_logo"
        android:contentDescription="Logo"
        android:layout_marginBottom="32dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Iniciar Sesión"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <EditText
        android:id="@+id/input_email"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:hint="Email"
        android:inputType="textEmailAddress"
        android:padding="12dp"
        android:layout_marginBottom="12dp"
        android:background="@drawable/input_background" />

    <EditText
        android:id="@+id/input_password"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:hint="Contraseña"
        android:inputType="textPassword"
        android:padding="12dp"
        android:layout_marginBottom="24dp"
        android:background="@drawable/input_background" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/btn_login"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:text="Iniciar Sesión"
        android:textSize="16sp" />

    <TextView
        android:id="@+id/txt_registro"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="¿No tienes cuenta? Regístrate"
        android:textColor="@color/primary"
        android:layout_marginTop="16dp" />

</LinearLayout>
```

**Activity correspondiente:** `LinearLoginActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.databinding.ActivityLinearLoginBinding
import android.util.Log

class LinearLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinearLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinearLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.inputEmail.text.toString().trim()
            val password = binding.inputPassword.text.toString()
            if (email.isNotEmpty() && password.length >= 6) {
                Log.d("LOGIN", "Validación exitosa - Email: $email")
                Toast.makeText(this, "Conectando...", Toast.LENGTH_SHORT).show()
            } else {
                Log.w("LOGIN", "Datos inválidos - Email: $email, Password length: ${password.length}")
                Toast.makeText(this, "Datos inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegistro.setOnClickListener {
            Log.d("NAVIGATION", "Usuario navega a registro")
            Toast.makeText(this, "Registro no implementado en este ejemplo", Toast.LENGTH_SHORT).show()
        }
    }
}
```

### LinearLayout Horizontal — Ejemplo: Card de Producto

**Archivo XML:** `res/layout/activity_linear_card.xml`

```{.xml .numberLines}
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="16dp"
    android:gravity="center_vertical">

    <ImageView
        android:id="@+id/img_producto"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:src="@drawable/ic_producto"
        android:contentDescription="Producto"
        android:scaleType="centerCrop" />

    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:orientation="vertical"
        android:layout_marginStart="16dp">

        <TextView
            android:id="@+id/txt_nombre"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Producto Premium"
            android:textStyle="bold"
            android:textSize="16sp" />

        <TextView
            android:id="@+id/txt_precio"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="$99.99"
            android:textColor="@color/primary"
            android:layout_marginTop="4dp" />

    </LinearLayout>

    <com.google.android.material.button.MaterialButton
        android:id="@+id/btn_agregar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Agregar" />

</LinearLayout>
```

**Activity correspondiente:** `LinearCardActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.databinding.ActivityLinearCardBinding
import android.util.Log

class LinearCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinearCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinearCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgregar.setOnClickListener {
            Log.d("CARD", "Usuario agregó producto: ${binding.txtNombre.text}")
            Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
        }
    }
}
```

### Diseño Responsive con `layout_weight`

El `layout_weight` es la clave para crear diseños responsivos en `LinearLayout`. Permite que los elementos ocupen espacio proporcionalmente al tamaño de la pantalla.

**Archivo XML:** `res/layout/activity_linear_weight.xml`

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Diseño Responsive con layout_weight"
        android:textSize="18sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <!-- Tres botones que ocupan el mismo ancho -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:weightSum="3">

        <Button
            android:id="@+id/btn_a"
            android:layout_width="0dp"
            android:layout_height="48dp"
            android:layout_weight="1"
            android:text="A" />

        <Button
            android:id="@+id/btn_b"
            android:layout_width="0dp"
            android:layout_height="48dp"
            android:layout_weight="1"
            android:text="B" />

        <Button
            android:id="@+id/btn_c"
            android:layout_width="0dp"
            android:layout_height="48dp"
            android:layout_weight="1"
            android:text="C" />

    </LinearLayout>

</LinearLayout>
```

![Ejemplo de tres botones con igual peso (layout_weight=1) ocupando el mismo ancho en cualquier pantalla.](img/cap11/linear_weight_equal.png)


**Conceptos clave del `layout_weight`:**

- Si usas `layout_weight`, establece `layout_width="0dp"` (para horizontal) o `layout_height="0dp"` (para vertical). Esto evita conflictos y asegura que el peso sea el único determinante del tamaño.
- El `weightSum` en el padre es opcional; por defecto es la suma de los pesos de los hijos.
- Los valores de peso pueden ser enteros o decimales (ej. `0.7` y `0.3` para un 70%-30%).

**Activity correspondiente:** `LinearWeightActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class LinearWeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_weight)

        val btnA = findViewById<Button>(R.id.btn_a)
        val btnB = findViewById<Button>(R.id.btn_b)
        val btnC = findViewById<Button>(R.id.btn_c)

        listOf(btnA, btnB, btnC).forEach { btn ->
            btn.setOnClickListener {
                Log.d("LinearWeight", "Botón ${btn.text} clickeado")
                Toast.makeText(this, "Botón ${btn.text} presionado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
```

### LinearLayout Combinados (Anidamiento)

Puedes anidar `LinearLayout`s para crear estructuras más complejas, pero evita profundidades mayores a 3-4 niveles.

**Archivo XML:** `res/layout/activity_linear_nested.xml`

```{.xml .numberLines}
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!-- Cabecera horizontal -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:orientation="horizontal"
        android:background="@color/primary"
        android:padding="16dp">

        <ImageView
            android:id="@+id/ic_menu"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/ic_menu" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Mi App"
            android:textColor="@color/white"
            android:textSize="20sp"
            android:layout_marginStart="16dp" />

        <ImageView
            android:id="@+id/ic_settings"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/ic_settings" />

    </LinearLayout>

    <!-- Contenido principal -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Contenido Principal" />

    </LinearLayout>

    <!-- Pie de página -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:orientation="horizontal"
        android:gravity="center"
        android:background="@color/light_gray">

        <Button
            android:id="@+id/btn_atras"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:text="Atrás" />

        <Button
            android:id="@+id/btn_siguiente"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:text="Siguiente" />

    </LinearLayout>

</LinearLayout>
```

**Activity correspondiente:** `LinearNestedActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.layouts.databinding.ActivityLinearNestedBinding

class LinearNestedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinearNestedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinearNestedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras.setOnClickListener {
            Log.d("Nested", "Botón Atrás presionado")
            Toast.makeText(this, "Atrás", Toast.LENGTH_SHORT).show()
        }
        binding.btnSiguiente.setOnClickListener {
            Log.d("Nested", "Botón Siguiente presionado")
            Toast.makeText(this, "Siguiente", Toast.LENGTH_SHORT).show()
        }
    }
}
```

![Ejemplo de estructura anidada: LinearLayout vertical que contiene una cabecera horizontal, contenido central y pie de página horizontal.](img/cap11/linear_nested_structure.png)


### Acceso desde Kotlin

Para interactuar con un `LinearLayout` desde código, se puede cambiar su orientación, gravedad u otras propiedades dinámicamente. A continuación se muestran los dos métodos estándar aplicados a `LinearNestedActivity.kt`.

**Método 1: `findViewById` (en `LinearNestedActivity.kt`)**

```{.kotlin .numberLines}
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_linear_nested)

    val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutHorizontal)
    linearLayout.orientation = LinearLayout.VERTICAL
    linearLayout.gravity = Gravity.CENTER
    Log.d("LinearNested", "Orientación cambiada a vertical")
    Toast.makeText(this, "Layout cambiado a vertical", Toast.LENGTH_SHORT).show()
}
```

**Método 2: View Binding (en `LinearNestedActivity.kt` con binding)**

```{.kotlin .numberLines}
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLinearNestedBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.linearLayoutHorizontal.orientation = LinearLayout.VERTICAL
    binding.linearLayoutHorizontal.gravity = Gravity.CENTER

    val numeroHijos = binding.linearLayoutHorizontal.childCount
    Log.d("LinearNested", "Elementos hijos: $numeroHijos")
    Toast.makeText(this, "Número de hijos: $numeroHijos", Toast.LENGTH_SHORT).show()
}
```

**Resumen rápido:**

<div class="tabla" style="--col1:30%; --col2:70%;">

| Aspecto | Descripción |
| :--- | :--- |
| `orientation` | `vertical` (por defecto), `horizontal` |
| `gravity` | Alinea hijos (center, top, bottom, start, end) |
| `layout_weight` | Proporción de espacio (requiere `layout_width/height="0dp"`) |
| `weightSum` | Suma total de pesos (opcional) |
| Anidamiento | LinearLayout dentro de LinearLayout |
| Performance | Evita anidamiento profundo (máx 3-4 niveles) |
</div>

::: warning
> **Anidamiento profundo es lento.** Cada Layout requiere medir y dibujar sus hijos. Si tienes más de 5-6 niveles anidados, el rendimiento decae. Usa `ConstraintLayout` en su lugar.
:::

> **Reglas de Oro para LinearLayout**
>
> 1. **Usa `layout_weight` para diseño responsivo:** No calcules tamaños manualmente; el peso se adapta a cualquier pantalla.
> 2. **`layout_width="0dp"` con weight en horizontal, `layout_height="0dp"` en vertical:** Es el patrón correcto para que el peso sea efectivo.
> 3. **Limita anidamiento a 3-4 niveles máximo:** El rendimiento se degrada rápidamente con anidamiento profundo.
> 4. **Combina orientaciones estratégicamente:** Usa un LinearLayout vertical como raíz y horizontales para filas internas.
> 5. **Usa `android:gravity` para alineación interior:** Los hijos se alinean dentro del contenedor; `layout_gravity` alinea el hijo dentro del padre.

::: note
> 📖 Consulta la documentación oficial de LinearLayout:
> - [LinearLayout – Android Developers](https://developer.android.com/reference/android/widget/LinearLayout)
> - [Layout Weight – Guía](https://developer.android.com/develop/ui/views/layout/linear#weight)
:::

---

## `FrameLayout`

`FrameLayout` superpone elementos — el último agregado aparece al frente. Ideal para contenedores de Fragment y overlays (capas superpuestas).

![Ejemplo de FrameLayout con una imagen de fondo, un texto centrado y un badge en la esquina superior derecha.](img/cap11/framelayout_overlay.png)

### Definición en XML

A continuación se muestra la estructura básica de `FrameLayout` y cómo superponer elementos.

**Archivo:** `res/layout/activity_frame_basic.xml`

```{.xml .numberLines}
<FrameLayout
    android:id="@+id/frame_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Fondo (primer elemento) -->
    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/background_image"
        android:scaleType="centerCrop" />

    <!-- Contenido frontal (segundo elemento) -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:gravity="center"
        android:padding="16dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Contenido Frontal"
            android:textSize="24sp"
            android:textColor="@color/white" />

    </LinearLayout>

</FrameLayout>
```

### Superposición de Elementos — Ejemplo: Badge sobre imagen

Un caso de uso común es mostrar una etiqueta de "oferta" o "nuevo" sobre una imagen.

**Archivo XML:** `res/layout/activity_frame_badge.xml`

```{.xml .numberLines}
<FrameLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="16dp">

    <!-- Imagen base -->
    <ImageView
        android:id="@+id/img_producto"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/product_image"
        android:scaleType="centerCrop" />

    <!-- Badge de oferta (se superpone en la esquina superior derecha) -->
    <TextView
        android:id="@+id/badge_oferta"
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:text="50%\nOff"
        android:textSize="12sp"
        android:gravity="center"
        android:background="@drawable/badge_red"
        android:textColor="@color/white"
        android:layout_gravity="top|end"
        android:layout_margin="4dp" />

</FrameLayout>
```

### Uso como Contenedor de Fragment

**Archivo XML:** `res/layout/activity_frame_fragment.xml`

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/primary" />

    <FrameLayout
        android:id="@+id/frame_container"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_nav"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        app:menu="@menu/bottom_nav_menu" />

</LinearLayout>
```

**Activity correspondiente:** `FrameFragmentActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.util.Log
import com.example.layouts.databinding.ActivityFrameFragmentBinding

class FrameFragmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrameFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_perfil -> loadFragment(PerfilFragment())
                R.id.nav_configuracion -> loadFragment(ConfiguracionFragment())
                else -> false
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
        Log.d("FRAGMENT", "Fragment cargado: ${fragment::class.simpleName}")
        Toast.makeText(this, "Cargando sección: ${fragment::class.simpleName}", Toast.LENGTH_SHORT).show()
    }
}
```

### Acceso desde Kotlin

Para modificar dinámicamente un `FrameLayout` (por ejemplo, cambiar su elevación u obtener sus hijos), se utilizan los siguientes métodos dentro de la Activity correspondiente (ej. `FrameBasicActivity.kt`).

**Método 1: `findViewById` (en `FrameBasicActivity.kt`)**

```{.kotlin .numberLines}
val frameContainer = findViewById<FrameLayout>(R.id.frame_container)
frameContainer.elevation = 10f
val primerElemento = frameContainer.getChildAt(0)
Log.d("FRAME", "Primer elemento: ${primerElemento::class.simpleName}")
Toast.makeText(this, "Elevación del FrameLayout cambiada", Toast.LENGTH_SHORT).show()
```

**Método 2: View Binding (en `FrameBasicActivity.kt` con binding)**

```{.kotlin .numberLines}
binding.frameContainer.elevation = 10f
val primerElemento = binding.frameContainer.getChildAt(0)
Log.d("FRAME", "Primer elemento: ${primerElemento::class.simpleName}")
Toast.makeText(this, "FrameLayout modificado", Toast.LENGTH_SHORT).show()
```

**Resumen rápido:**

<div class="tabla" style="--col1:35%; --col2:65%;">
| Operación | Código |
| :--- | :--- |
| Elemento al frente | Agregarlo al final en XML |
| Posición dentro del FrameLayout | `android:layout_gravity="top|end"` |
| Superposición | Último elemento agregado visualmente está al frente |
| Contenedor de Fragment | `FragmentManager.beginTransaction().replace(id, fragment)` |
</div>

> **Reglas de Oro para FrameLayout**
>
> 1. **FrameLayout es el contenedor estándar para Fragments:** Es su propósito principal en apps modernas.
> 2. **Orden en XML = orden de superposición:** El último elemento en el XML se dibuja encima de los anteriores.
> 3. **Usa `layout_gravity` para posicionar elementos dentro del FrameLayout:** Valores como `top|start`, `center`, `bottom|end` son muy útiles.
> 4. **No uses múltiples FrameLayouts anidados sin necesidad:** Confunde la jerarquía y degrada el rendimiento.
> 5. **Para overlays, establece fondo semi-transparente:** `@color/black_semi_transparent` (ej. `#80000000`).

::: note
> 📖 Consulta la documentación oficial de FrameLayout:
> - [FrameLayout – Android Developers](https://developer.android.com/reference/android/widget/FrameLayout)
> - [Fragment Transactions](https://developer.android.com/guide/fragments/transactions)
:::

---

## `RelativeLayout`

`RelativeLayout` posiciona elementos relativamente al padre o a otros elementos hermanos. Es más flexible que `LinearLayout` pero menos potente que `ConstraintLayout`. Actualmente se considera una opción legada; para nuevos proyectos se recomienda `ConstraintLayout`.

![Ejemplo de RelativeLayout: imagen a la izquierda, texto a la derecha, email debajo del nombre, y botón en la esquina inferior derecha.](img/cap11/relative_layout_example.png)

### Definición en XML

A continuación se muestra la estructura básica de `RelativeLayout` con posicionamiento relativo.

**Archivo:** `res/layout/activity_relative_basic.xml`

```{.xml .numberLines}
<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Imagen al inicio (top-left) -->
    <ImageView
        android:id="@+id/img_perfil"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:src="@drawable/ic_user"
        android:contentDescription="Perfil" />

    <!-- Nombre a la derecha de la imagen -->
    <TextView
        android:id="@+id/txt_nombre"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Juan Pérez"
        android:textSize="18sp"
        android:textStyle="bold"
        android:layout_toRightOf="@id/img_perfil"
        android:layout_marginStart="16dp" />

    <!-- Email debajo del nombre -->
    <TextView
        android:id="@+id/txt_email"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="juan@example.com"
        android:layout_below="@id/txt_nombre"
        android:layout_toRightOf="@id/img_perfil"
        android:layout_marginStart="16dp"
        android:layout_marginTop="4dp" />

    <!-- Botón editar en la esquina inferior derecha -->
    <Button
        android:id="@+id/btn_editar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Editar"
        android:layout_alignParentBottom="true"
        android:layout_alignParentEnd="true" />

</RelativeLayout>
```

### Posicionamiento Relativo al Padre

Estos atributos permiten posicionar un elemento con respecto a los bordes del `RelativeLayout` padre.

<div class="tabla" style="--col1:44%; --col2:56%;">
| Atributo | Descripción |
| :--- | :--- |
| `android:layout_alignParentTop` | Alinea el borde superior del elemento al borde superior del padre |
| `android:layout_alignParentBottom` | Alinea el borde inferior del elemento al borde inferior del padre |
| `android:layout_alignParentStart` | Alinea el inicio del elemento al inicio del padre (izquierda en LTR) |
| `android:layout_alignParentEnd` | Alinea el final del elemento al final del padre (derecha en LTR) |
| `android:layout_centerInParent` | Centra el elemento en el padre (horizontal y verticalmente) |
| `android:layout_centerHorizontal` | Centra horizontalmente en el padre |
| `android:layout_centerVertical` | Centra verticalmente en el padre |
</div>

### Posicionamiento Relativo a Otro Elemento

Estos atributos permiten posicionar un elemento en relación a otro elemento (referenciado por su `id`).

<div class="tabla" style="--col1:49%; --col2:51%;">
| Atributo | Descripción |
| :--- | :--- |
| `android:layout_above="@id/otro"` | Coloca el borde inferior del elemento encima del borde superior del otro |
| `android:layout_below="@id/otro"` | Coloca el borde superior del elemento debajo del borde inferior del otro |
| `android:layout_toLeftOf="@id/otro"` | Coloca el borde derecho del elemento a la izquierda del borde izquierdo del otro |
| `android:layout_toRightOf="@id/otro"` | Coloca el borde izquierdo del elemento a la derecha del borde derecho del otro |
| `android:layout_alignTop="@id/otro"` | Alinea el borde superior del elemento con el del otro |
| `android:layout_alignBottom="@id/otro"` | Alinea el borde inferior del elemento con el del otro |
| `android:layout_alignStart="@id/otro"` | Alinea el inicio del elemento con el del otro (RTL safe) |
| `android:layout_alignEnd="@id/otro"` | Alinea el final del elemento con el del otro (RTL safe) |
</div>

### Traslaciones y Escala de Elementos

Estas propiedades permiten mover o escalar un elemento visualmente sin afectar su área reservada (el espacio que ocupa para el layout).

**Archivo XML:** `res/layout/activity_relative_transform.xml`

```{.xml .numberLines}
<Button
    android:id="@+id/btn_ejemplo"
    android:layout_width="100dp"
    android:layout_height="50dp"
    android:text="Botón"
    android:layout_centerInParent="true"
    android:translationX="20dp"
    android:translationY="-10dp"
    android:scaleX="1.1"
    android:scaleY="0.9" />
```

**Transformaciones desde Kotlin (dentro de `RelativeTransformActivity.kt`):**

```{.kotlin .numberLines}
// Trasladar elemento
binding.btnEjemplo.translationX = 50f
binding.btnEjemplo.translationY = 30f
Log.d("TRANSFORM", "Botón trasladado a X=50, Y=30")
Toast.makeText(this, "Botón trasladado", Toast.LENGTH_SHORT).show()

// Escalar elemento
binding.btnEjemplo.scaleX = 1.2f
binding.btnEjemplo.scaleY = 0.8f
Log.d("TRANSFORM", "Botón escalado: X=1.2, Y=0.8")
Toast.makeText(this, "Botón escalado", Toast.LENGTH_SHORT).show()

// Rotar elemento
binding.btnEjemplo.rotation = 45f
Log.d("TRANSFORM", "Botón rotado 45 grados")
Toast.makeText(this, "Botón rotado", Toast.LENGTH_SHORT).show()

// Cambiar alpha (transparencia)
binding.btnEjemplo.alpha = 0.7f
Log.d("TRANSFORM", "Alpha cambiado a 0.7")
Toast.makeText(this, "Transparencia aplicada", Toast.LENGTH_SHORT).show()
```

### Acceso desde Kotlin

**Método 1: `findViewById` (en `RelativeBasicActivity.kt`)**

```{.kotlin .numberLines}
val btnEditar = findViewById<Button>(R.id.btn_editar)
val params = btnEditar.layoutParams as RelativeLayout.LayoutParams
params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1)
params.addRule(RelativeLayout.ALIGN_PARENT_START, 1)
btnEditar.layoutParams = params
Log.d("RelativeBasic", "Botón movido a esquina inferior izquierda")
Toast.makeText(this, "Botón Editar movido", Toast.LENGTH_SHORT).show()
```

**Método 2: View Binding (en `RelativeBasicActivity.kt` con binding)**

```{.kotlin .numberLines}
val params = binding.btnEditar.layoutParams as RelativeLayout.LayoutParams
params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1)
params.addRule(RelativeLayout.ALIGN_PARENT_START, 1)
binding.btnEditar.layoutParams = params

binding.btnEditar.setOnClickListener {
    Log.d("PERFIL", "Usuario presionó editar")
    Toast.makeText(this, "Editar perfil", Toast.LENGTH_SHORT).show()
}
```

**Resumen rápido:**

| Operación | Código |
| :--- | :--- |
| Centrar en padre | `android:layout_centerInParent="true"` |
| Debajo de otro | `android:layout_below="@id/otro"` |
| A la derecha de otro | `android:layout_toRightOf="@id/otro"` |
| Alinear bottoms | `android:layout_alignBottom="@id/otro"` |
| Trasladar | `android:translationX="20dp"` |
| Escalar | `android:scaleX="1.1"` |

::: warning
> **RelativeLayout está deprecado en favor de ConstraintLayout.** `ConstraintLayout` es más poderoso, flexible y eficiente. Usa `RelativeLayout` solo en código legacy o proyectos muy simples.
:::

> **Reglas de Oro para RelativeLayout**
>
> 1. **RelativeLayout para diseños simples y asimétricos (legacy):** Perfil de usuario, detalles de producto.
> 2. **Asigna IDs claros a los elementos relacionados:** `@id/img_perfil`, `@id/txt_nombre` — facilita el mantenimiento.
> 3. **Evita ciclos de dependencia:** Un elemento A no puede depender de B si B depende de A.
> 4. **Las traslaciones y escalas NO afectan el área reservada:** Otros elementos seguirán posicionándose respecto al área original.
> 5. **Migra a ConstraintLayout en nuevos proyectos:** Es el estándar actual de Google.

::: note
> 📖 Consulta la documentación oficial de RelativeLayout:
> - [RelativeLayout – Android Developers](https://developer.android.com/reference/android/widget/RelativeLayout)
:::

---

## `ConstraintLayout`

`ConstraintLayout` es el Layout moderno de Android — basado en restricciones (constraints) que definen relaciones flexibles entre elementos. Permite crear interfaces completamente responsivas sin anidamiento profundo.

![Ejemplo de ConstraintLayout mostrando restricciones (líneas azules) entre un botón centrado y un EditText debajo de él, con conexiones a los bordes del padre.](img/cap11/constraint_layout_constraints.png)

### Dependencia Requerida

En `build.gradle` (Module: app):

```groovy
dependencies {
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

### Definición en XML — Conceptos Base

**Archivo:** `res/layout/activity_constraint_basic.xml`

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Botón constreñido a los lados del padre (centrado horizontalmente) -->
    <Button
        android:id="@+id/btn_enviar"
        android:layout_width="wrap_content"
        android:layout_height="48dp"
        android:text="Enviar"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <!-- EditText debajo del botón, constreñido a los lados -->
    <EditText
        android:id="@+id/input_email"
        android:layout_width="0dp"
        android:layout_height="48dp"
        android:hint="Email"
        android:layout_marginTop="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btn_enviar" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Cómo Indicar Constraints (Restricciones)

Un constraint conecta dos "anclas" (anchors). La siguiente tabla resume los tipos principales:

| Anchor / Atributo | Descripción |
| :--- | :--- |
| `layout_constraintStart_toStartOf` | Mi inicio está conectado al inicio de otro elemento |
| `layout_constraintEnd_toEndOf` | Mi final está conectado al final de otro elemento |
| `layout_constraintTop_toTopOf` | Mi tope está conectado al tope de otro elemento |
| `layout_constraintBottom_toBottomOf` | Mi fondo está conectado al fondo de otro elemento |
| `layout_constraintStart_toEndOf` | Mi inicio está conectado al final de otro elemento |
| `layout_constraintEnd_toStartOf` | Mi final está conectado al inicio de otro elemento |
| `layout_constraintTop_toBottomOf` | Mi tope está conectado al fondo de otro elemento |
| `layout_constraintBottom_toTopOf` | Mi fondo está conectado al tope de otro elemento |

**Ejemplo práctico:** Centrar un botón horizontal y verticalmente:

```{.xml .numberLines}
<Button
    android:id="@+id/btn_centrado"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Centrado"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent" />
```

### Guidelines — Guías Visuales

Las `Guidelines` son líneas invisibles que ayudan a posicionar elementos. No aparecen en la app, solo en el editor.

**Archivo:** `res/layout/activity_constraint_guideline.xml`

```{.xml .numberLines}
<androidx.constraintlayout.widget.ConstraintLayout ...>
    <!-- Guía vertical al 50% del ancho -->
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline_vertical"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.5" />

    <!-- Guía horizontal a 100dp del tope -->
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline_horizontal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_begin="100dp" />

    <Button
        android:id="@+id/btn_izquierda"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Izquierda"
        app:layout_constraintEnd_toStartOf="@id/guideline_vertical"
        app:layout_constraintBottom_toBottomOf="parent" />

    <Button
        android:id="@+id/btn_derecha"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Derecha"
        app:layout_constraintStart_toEndOf="@id/guideline_vertical"
        app:layout_constraintBottom_toBottomOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

### Barriers — Barreras Dinámicas

`Barrier` crea una barrera invisible basada en múltiples elementos — útil cuando elementos tienen tamaño variable.

![Ejemplo de Barrier: dos TextView de diferente ancho (Nombre: y Email muy largo:) y un EditText alineado al final del más ancho.](img/cap11/constraint_barrier.png)

**Archivo:** `res/layout/activity_constraint_barrier.xml`

```{.xml .numberLines}
<TextView
        android:id="@+id/lbl_nombre"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Nombre:"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBaselineOf="@id/input_nombre" />

    <TextView
        android:id="@+id/lbl_email"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Email muy largo:"
        android:layout_marginTop="24dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/input_nombre" />

    <!-- Barrera que actúa como el final del elemento más ancho -->
    <androidx.constraintlayout.widget.Barrier
        android:id="@+id/barrier_labels"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barrierDirection="end"
        app:constraint_referenced_ids="lbl_nombre,lbl_email" />

    <EditText
        android:id="@+id/input_nombre"
        android:layout_width="0dp"
        android:layout_height="48dp"
        android:hint="Tu nombre"
        app:layout_constraintStart_toEndOf="@id/barrier_labels"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginStart="16dp" />
```

### Chains — Cadenas de Elementos

`Chains` distribuyen elementos equitativamente en un eje.

![Tres botones en cadena horizontal con estilo spread (espaciado uniforme).](img/cap11/constraint_chain.png)

**Archivo:** `res/layout/activity_constraint_chain.xml`

```{.xml .numberLines}
<Button
    android:id="@+id/btn_a"
    android:layout_width="0dp"
    android:layout_height="48dp"
    android:text="A"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toStartOf="@id/btn_b"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintHorizontal_chainStyle="spread" />

<Button
    android:id="@+id/btn_b"
    android:layout_width="0dp"
    android:layout_height="48dp"
    android:text="B"
    app:layout_constraintStart_toEndOf="@id/btn_a"
    app:layout_constraintEnd_toStartOf="@id/btn_c"
    app:layout_constraintTop_toTopOf="parent" />

<Button
    android:id="@+id/btn_c"
    android:layout_width="0dp"
    android:layout_height="48dp"
    android:text="C"
    app:layout_constraintStart_toEndOf="@id/btn_b"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

**Estilos de Chains:**

<div class="tabla" style="--col1:20%; --col2:40%; --col3:40%;">
| Style | Atributo | Descripción |
| :--- | :--- | :--- |
| `spread` | `layout_constraintHorizontal_chainStyle="spread"` | Espaciado uniforme entre elementos |
| `spread_inside` | `...="spread_inside"` | Elementos pegados a los bordes, espacio interior uniforme |
| `packed` | `...="packed"` | Elementos agrupados juntos, centrados |
</div>

### Start/End vs Left/Right (Soporte RTL)

Para soportar idiomas que se escriben de derecha a izquierda (RTL, como árabe y hebreo), **siempre usa `start`/`end` en lugar de `left`/`right`**. Android invertirá automáticamente la disposición cuando el idioma del dispositivo sea RTL.

```{.xml .numberLines}
<!-- ✓ CORRECTO — Soporta RTL -->
<Button
    android:id="@+id/btn_enviar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Enviar"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />

<!-- ✗ INCORRECTO — Solo LTR -->
<Button
    android:id="@+id/btn_enviar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Enviar"
    android:layout_marginLeft="16dp"
    android:layout_marginRight="16dp" />
```

### Convertir Otros Layouts a ConstraintLayout

Android Studio permite convertir automáticamente:

1. Abre el layout en vista **Design**.
2. Haz clic derecho sobre la raíz del Layout (ej. `LinearLayout`).
3. Selecciona **Convert to ConstraintLayout**.
4. Android Studio generará constraints automáticos.

![Menú contextual en Android Studio mostrando la opción "Convert to ConstraintLayout".](img/cap11/convert_to_constraint.png)

::: warning
> La conversión automática no es perfecta. Siempre revisa y ajusta los constraints generados, especialmente en layouts complejos.
:::

### Infer Constraints / Clear Constraints

En el editor visual de Android Studio:

- **Infer Constraints** (ícono de varita mágica): Genera constraints automáticos para todos los elementos basándose en su posición actual.
- **Clear Constraints** (ícono de X): Elimina todos los constraints del elemento seleccionado.

![Botones de Infer Constraints (varita) y Clear Constraints (X) en la barra de herramientas del editor de layouts.](img/cap11/infer_clear_constraints.png)

::: caution
> No confíes ciegamente en **Infer Constraints**. Aprender a definir constraints manualmente te dará un control total sobre el diseño.
:::

**Resumen rápido de ConstraintLayout:**

<div class="tabla" style="--col1:28%; --col2:72%;">
| Operación | Atributo o Código |
| :--- | :--- |
| Conectar al padre | `app:layout_constraintStart_toStartOf="parent"` |
| Conectar a otro elemento | `app:layout_constraintTop_toBottomOf="@id/otro"` |
| Centrar | `layout_constraintStart_toStartOf="parent"` + `layout_constraintEnd_toEndOf="parent"` |
| Guideline | `<Guideline app:layout_constraintGuide_percent="0.5" />` |
| Barrier | `<Barrier app:barrierDirection="end" app:constraint_referenced_ids="id1,id2" />` |
| Chain style | `layout_constraintHorizontal_chainStyle="spread"` |
| RTL Safe | Usa `start`/`end`, no `left`/`right` |
</div> 

::: important
> **ConstraintLayout es el Layout recomendado para todos los proyectos nuevos.** Es más flexible, responsivo y eficiente que cualquier otro Layout. Elimina la necesidad de anidamiento profundo.
:::

> **Reglas de Oro para ConstraintLayout**
>
> 1. **Cada elemento necesita al menos 2 constraints en X y 2 en Y:** Sin constraints, la posición es indefinida y habrá error.
> 2. **Usa `0dp` (match_constraint) para elementos que deben expandirse:** Es el equivalente a `match_parent` pero con constraints.
> 3. **Guidelines para alineación consistente:** Facilitan mantener una estructura visual limpia.
> 4. **Barriers para layouts con elementos de tamaño variable:** La barrera se ajusta automáticamente.
> 5. **Chains para distribuir elementos uniformemente:** Usa `spread`, `spread_inside` o `packed` según necesidad.
> 6. **Siempre usa `start`/`end` en lugar de `left`/`right`:** Proporciona soporte RTL automático.

::: note
> 📖 Consulta la documentación oficial de ConstraintLayout:
> - [ConstraintLayout – Android Developers](https://developer.android.com/training/constraint-layout)
> - [Guidelines and Barriers](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout)
> - [Chains](https://developer.android.com/training/constraint-layout#chains)
:::

---

## `TableLayout` y `TableRow`

`TableLayout` organiza elementos en tabla — filas y columnas. Cada fila es un `TableRow`. Es ideal para mostrar datos tabulares simples como facturas o reportes.

![Ejemplo de TableLayout con cabecera en color primario, filas de datos y una fila de total al final.](img/cap11/table_layout_example.png)

### Definición en XML

A continuación se muestra la estructura básica de una tabla con cabecera y datos.

**Archivo:** `res/layout/activity_table_basic.xml`

```{.xml .numberLines}
<TableLayout
    android:id="@+id/table_datos"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:stretchColumns="1"
    android:padding="16dp">

    <!-- Encabezado -->
    <TableRow
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/primary">

        <TextView
            android:layout_width="80dp"
            android:layout_height="48dp"
            android:text="ID"
            android:textColor="@color/white"
            android:gravity="center" />

        <TextView
            android:layout_width="0dp"
            android:layout_height="48dp"
            android:text="Nombre"
            android:textColor="@color/white"
            android:gravity="center"
            android:layout_weight="1" />

        <TextView
            android:layout_width="80dp"
            android:layout_height="48dp"
            android:text="Precio"
            android:textColor="@color/white"
            android:gravity="center" />

    </TableRow>

    <!-- Datos Fila 1 -->
    <TableRow>
        <TextView android:text="001" android:gravity="center" />
        <TextView android:text="Producto A" android:gravity="center" />
        <TextView android:text="$25.00" android:gravity="center" android:textColor="@color/primary" />
    </TableRow>

</TableLayout>
```

### Atributos de Columnas

Los siguientes atributos se aplican a `TableLayout` (el contenedor) para controlar el comportamiento de las columnas.

| Atributo | Descripción | Ejemplo |
| :--- | :--- | :--- |
| `android:stretchColumns` | Columnas que se expanden para ocupar espacio extra | `android:stretchColumns="1"` (columna índice 1) |
| `android:shrinkColumns` | Columnas que pueden reducirse si es necesario | `android:shrinkColumns="0,2"` |
| `android:collapseColumns` | Columnas que se ocultan | `android:collapseColumns="2"` |
| `android:layout_column` | Columna específica donde se coloca el elemento (en un `TableRow`) | `android:layout_column="2"` |
| `android:layout_span` | Número de columnas que ocupa el elemento | `android:layout_span="2"` |

### Agregar Filas Dinámicamente desde Kotlin

Para construir una tabla cuyas filas no se conocen en tiempo de diseño (por ejemplo, datos provenientes de una base de datos), se pueden agregar filas programáticamente.

**Archivo XML:** `res/layout/activity_table_dinamic.xml` (similar al básico, con un `TableLayout` con id `tableProductos`)

**Activity correspondiente:** `TableDinamicActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.layouts.databinding.ActivityTableDinamicBinding

class TableDinamicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTableDinamicBinding
    private val productos = listOf(
        Triple("001", "Producto A", "$25.00"),
        Triple("002", "Producto B", "$35.00"),
        Triple("003", "Producto C", "$45.00")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableDinamicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productos.forEach { (id, nombre, precio) ->
            val fila = TableRow(this)
            fila.addView(createTextView(id))
            fila.addView(createTextView(nombre))
            fila.addView(createTextView(precio, true))
            binding.tableProductos.addView(fila)
            Log.d("TableDinamic", "Fila agregada: $id - $nombre")
        }
        Toast.makeText(this, "Tabla cargada con ${productos.size} productos", Toast.LENGTH_SHORT).show()
    }

    private fun createTextView(texto: String, isPrecio: Boolean = false): TextView {
        return TextView(this).apply {
            this.text = texto
            gravity = Gravity.CENTER
            setPadding(8, 8, 8, 8)
            if (isPrecio) setTextColor(Color.parseColor("#6200EE"))
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        }
    }
}
```

### Ejemplo Real para Laboratorio — Factura o Reporte de Ventas

El siguiente ejemplo muestra una factura o reporte de ventas construido dinámicamente, incluyendo cálculo del total general. Se implementa como un `Fragment` para mayor reutilización.

**Archivo XML:** `res/layout/fragment_reporte.xml`

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Reporte de Ventas"
        android:textSize="20sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TableLayout
            android:id="@+id/table_ventas"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:stretchColumns="1" />

    </ScrollView>
</LinearLayout>
```

**Fragment correspondiente:** `ReporteFragment.kt`

```{.kotlin .numberLines}
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.util.Log
import com.example.layouts.databinding.FragmentReporteBinding

class ReporteFragment : Fragment() {
    private lateinit var binding: FragmentReporteBinding
    private val ventas = listOf(
        Venta("2024-01-15", "Cliente A", 150.0),
        Venta("2024-01-16", "Cliente B", 200.0),
        Venta("2024-01-17", "Cliente C", 120.0)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentReporteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agregarEncabezado()
        ventas.forEach { agregarFilaVenta(it) }
        agregarFilaTotal()
        Log.d("Reporte", "Tabla de ventas generada con ${ventas.size} filas")
        Toast.makeText(requireContext(), "Reporte generado", Toast.LENGTH_SHORT).show()
    }

    private fun agregarEncabezado() {
        val fila = TableRow(requireContext()).apply { setBackgroundColor(Color.parseColor("#6200EE")) }
        listOf("Fecha", "Cliente", "Monto").forEach { titulo ->
            fila.addView(createTextView(titulo, true))
        }
        binding.tableVentas.addView(fila)
    }

    private fun agregarFilaVenta(venta: Venta) {
        val fila = TableRow(requireContext())
        fila.addView(createTextView(venta.fecha, false))
        fila.addView(createTextView(venta.cliente, false))
        fila.addView(createTextView("$${venta.monto}", false, Color.parseColor("#4CAF50")))
        binding.tableVentas.addView(fila)
    }

    private fun agregarFilaTotal() {
        val total = ventas.sumOf { it.monto }
        val fila = TableRow(requireContext()).apply { setBackgroundColor(Color.parseColor("#F5F5F5")) }
        fila.addView(createTextView("", false))
        fila.addView(createTextView("TOTAL", false, isBold = true))
        fila.addView(createTextView("$${String.format("%.2f", total)}", false, Color.parseColor("#FF6200EE"), true))
        binding.tableVentas.addView(fila)
    }

    private fun createTextView(texto: String, esEncabezado: Boolean = false, color: Int = Color.BLACK, isBold: Boolean = false): TextView {
        return TextView(requireContext()).apply {
            this.text = texto
            gravity = Gravity.CENTER
            setTextColor(if (esEncabezado) Color.WHITE else color)
            if (isBold) setTypeface(null, Typeface.BOLD)
            layoutParams = TableRow.LayoutParams(0, 60, 1f)
            setPadding(8, 8, 8, 8)
        }
    }

    data class Venta(val fecha: String, val cliente: String, val monto: Double)
}
```

**Resumen rápido:**

| Operación | Código |
| :--- | :--- |
| Crear tabla | `<TableLayout>` como contenedor raíz |
| Añadir fila | `<TableRow>` dentro de `TableLayout` |
| Añadir columna dinámica | `fila.addView(textView)` |
| Expandir columna | `android:stretchColumns="1"` |
| Span múltiples columnas | `android:layout_span="2"` |

::: warning
> `TableLayout` es adecuado solo para **tablas simples con pocas filas**. Para grandes volúmenes de datos, usa `RecyclerView` con un adaptador, que es mucho más eficiente.
:::

> **Reglas de Oro para TableLayout**
>
> 1. **TableLayout para datos tabulares simples (facturas, reportes pequeños):** Si necesitas scroll, búsqueda o grandes cantidades de datos, usa `RecyclerView`.
> 2. **Cada `TableRow` es una fila:** Los elementos dentro de un `TableRow` se convierten automáticamente en columnas.
> 3. **`stretchColumns` expande las columnas indicadas:** El valor es el índice de la columna (0-index).
> 4. **Envuelve `TableLayout` en un `ScrollView` para tablas largas:** Evita que la tabla se salga de la pantalla.
> 5. **Crea funciones auxiliares (`createTextView`) para evitar repetir código:** Mejora la mantenibilidad.

::: note
> 📖 Consulta la documentación oficial de TableLayout:
> - [TableLayout – Android Developers](https://developer.android.com/reference/android/widget/TableLayout)
> - [TableRow – Android Developers](https://developer.android.com/reference/android/widget/TableRow)
:::

---

## `GridLayout`

`GridLayout` organiza elementos en una cuadrícula (grid) definida por filas y columnas. Es ideal para interfaces como calculadoras, galerías de iconos o tableros de juego.

![Ejemplo de GridLayout de 4x4 simulando una calculadora básica.](img/cap11/grid_layout_calculator.png)

### Definición en XML

A continuación se muestra la estructura básica de un `GridLayout` con elementos que ocupan diferentes celdas.

**Archivo:** `res/layout/activity_grid_basic.xml`

```{.xml .numberLines}
<GridLayout
    android:id="@+id/grid_galeria"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:columnCount="3"
    android:rowCount="4"
    android:padding="8dp">

    <!-- Elemento 1 (fila 0, col 0) -->
    <ImageView
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:src="@drawable/img_1"
        android:scaleType="centerCrop"
        android:contentDescription="Imagen 1"
        android:layout_row="0"
        android:layout_column="0"
        android:layout_rowWeight="1"
        android:layout_columnWeight="1"
        android:layout_margin="4dp" />

    <!-- Elemento 2 — ocupa 2 columnas -->
    <ImageView
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:src="@drawable/img_2"
        android:scaleType="centerCrop"
        android:contentDescription="Imagen 2"
        android:layout_row="0"
        android:layout_column="1"
        android:layout_rowWeight="1"
        android:layout_columnSpan="2"
        android:layout_columnWeight="1"
        android:layout_margin="4dp" />

</GridLayout>
```

### Posicionamiento de Elementos

Los siguientes atributos se aplican a los hijos directos de `GridLayout` para controlar su posición y tamaño.

| Atributo | Descripción |
| :--- | :--- |
| `android:layout_row` | Fila donde comienza el elemento (0-index) |
| `android:layout_column` | Columna donde comienza el elemento (0-index) |
| `android:layout_rowSpan` | Número de filas que ocupa el elemento |
| `android:layout_columnSpan` | Número de columnas que ocupa el elemento |
| `android:layout_rowWeight` | Peso en el eje vertical (para distribuir altura) |
| `android:layout_columnWeight` | Peso en el eje horizontal (para distribuir ancho) |

### Aplicación Práctica — Calculadora (Diseño Visual)

El siguiente ejemplo construye el diseño visual de una calculadora funcional combinando `GridLayout` (para los botones) y `ConstraintLayout` (para la pantalla y organización general). La funcionalidad se puede implementar como ejercicio práctico.

**Archivo XML:** `res/layout/activity_grid_calculator.xml`

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/display"
        android:layout_width="0dp"
        android:layout_height="100dp"
        android:text="0"
        android:textSize="48sp"
        android:gravity="end|center_vertical"
        android:background="@drawable/display_background"
        android:padding="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <GridLayout
        android:id="@+id/grid_botones"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:columnCount="4"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@id/display"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginTop="16dp">

        <Button android:text="7" android:id="@+id/btn_7" style="@style/CalculadoraBoton" />
        <Button android:text="8" android:id="@+id/btn_8" style="@style/CalculadoraBoton" />
        <Button android:text="9" android:id="@+id/btn_9" style="@style/CalculadoraBoton" />
        <Button android:text="/" android:id="@+id/btn_div" style="@style/CalculadoraBoton" />

        <Button android:text="4" android:id="@+id/btn_4" style="@style/CalculadoraBoton" />
        <Button android:text="5" android:id="@+id/btn_5" style="@style/CalculadoraBoton" />
        <Button android:text="6" android:id="@+id/btn_6" style="@style/CalculadoraBoton" />
        <Button android:text="*" android:id="@+id/btn_mul" style="@style/CalculadoraBoton" />

        <Button android:text="1" android:id="@+id/btn_1" style="@style/CalculadoraBoton" />
        <Button android:text="2" android:id="@+id/btn_2" style="@style/CalculadoraBoton" />
        <Button android:text="3" android:id="@+id/btn_3" style="@style/CalculadoraBoton" />
        <Button android:text="-" android:id="@+id/btn_sub" style="@style/CalculadoraBoton" />

        <Button android:text="0" android:id="@+id/btn_0" style="@style/CalculadoraBoton" android:layout_columnSpan="2" />
        <Button android:text="." android:id="@+id/btn_dot" style="@style/CalculadoraBoton" />
        <Button android:text="+" android:id="@+id/btn_sum" style="@style/CalculadoraBoton" />

    </GridLayout>
    <Button
        android:id="@+id/btn_igual"
        android:layout_width="0dp"
        android:layout_height="56dp"
        android:text="="
        android:textSize="24sp"
        android:backgroundTint="@color/primary"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@id/grid_botones"
        android:layout_marginTop="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

```{.xml .numberLines}
<!-- styles.xml — Estilo para botones de calculadora -->
<style name="CalculadoraBoton">
    <item name="android:layout_width">0dp</item>
    <item name="android:layout_height">56dp</item>
    <item name="android:textSize">24sp</item>
    <item name="android:layout_rowWeight">1</item>
    <item name="android:layout_columnWeight">1</item>
    <item name="android:layout_margin">4dp</item>
</style>
```

### Acceso desde Kotlin

Agregar elementos dinámicamente a un `GridLayout` es útil cuando el número de elementos no se conoce en tiempo de diseño.

**Archivo XML:** `res/layout/activity_grid_galeria.xml` (con un `GridLayout` con id `gridGaleria`)

**Activity correspondiente:** `GridGaleriaActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.gridlayout.widget.GridLayout
import com.example.layouts.databinding.ActivityGridGaleriaBinding

class GridGaleriaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridGaleriaBinding
    private val imagenes = listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imagenes.forEachIndexed { index, res ->
            val imageView = ImageView(this).apply {
                setImageResource(res)
                scaleType = ImageView.ScaleType.CENTER_CROP
                contentDescription = "Imagen ${index + 1}"
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 150
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(4, 4, 4, 4)
                }
                setOnClickListener {
                    Toast.makeText(this@GridGaleriaActivity, "Imagen ${index + 1}", Toast.LENGTH_SHORT).show()
                    Log.d("GridGaleria", "Imagen ${index + 1} clickeada")
                }
            }
            binding.gridGaleria.addView(imageView)
            Log.d("GridGaleria", "Imagen ${index + 1} añadida al grid")
        }
        Toast.makeText(this, "Galería cargada con ${imagenes.size} imágenes", Toast.LENGTH_SHORT).show()
    }
}
```

**Resumen rápido:**

<div class="tabla" style="--col1:30%; --col2:70%;">
| Operación | Código |
| :--- | :--- |
| Definir grid 3x3 | `android:columnCount="3"` + `android:rowCount="3"` |
| Posicionar elemento | `android:layout_row="0" android:layout_column="1"` |
| Ocupar múltiples columnas | `android:layout_columnSpan="2"` |
| Peso flexible | `android:layout_columnWeight="1"` (requiere `layout_width="0dp"`) |
</div>

> **Reglas de Oro para GridLayout**
>
> 1. **GridLayout para cuadrículas regulares (galerías, menús de opciones, calculadoras):** No es adecuado para listas dinámicas (usa `RecyclerView`).
> 2. **Usa `layout_columnWeight` y `layout_rowWeight` para elementos flexibles:** Así se adaptan a diferentes tamaños de pantalla.
> 3. **No confundas `GridLayout` con `GridView`:** `GridLayout` es para layouts estáticos; `GridView` es para listas grandes con scroll.
> 4. **Proporciona `columnCount` y `rowCount` para definir la estructura base:** Aunque se puede omitir, es recomendable para claridad.
> 5. **Combina `GridLayout` con `ConstraintLayout` para resultados profesionales:** Como en el ejemplo de la calculadora.

::: note
> 📖 Consulta la documentación oficial de GridLayout:
> - [GridLayout – Android Developers](https://developer.android.com/reference/android/widget/GridLayout)
:::

---

## `Space`

`Space` es un elemento invisible que ocupa espacio — una alternativa moderna a `margin` y `padding` cuando se necesita espaciado flexible.

![Ejemplo de Space usado para empujar un botón hacia la derecha en un LinearLayout horizontal.](img/cap11/space_example.png)

### Definición en XML

A continuación se muestran dos usos típicos de `Space`: como espaciador fijo y como espaciador flexible con `layout_weight`.

**Archivo:** `res/layout/activity_space_basic.xml`

```{.xml .numberLines}
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Elemento 1" />

    <!-- Space de 16dp entre elementos -->
    <Space
        android:layout_width="match_parent"
        android:layout_height="16dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Elemento 2" />

    <!-- Space flexible (ocupa todo espacio disponible) -->
    <Space
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <Button
        android:id="@+id/btn_final"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:text="Botón al final" />

</LinearLayout>
```

### Uso como Separador Invisible con `layout_weight`

Cuando se necesita que un elemento se empuje hacia un extremo y otro hacia el extremo opuesto, se puede usar `Space` con `layout_weight="1"` para ocupar el espacio intermedio de forma flexible.

```{.xml .numberLines}
<!-- Layout horizontal con espacio flexible en el medio -->
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <Button
        android:id="@+id/btn_atras"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Atrás" />

    <!-- Espacio que empuja el segundo botón al final -->
    <Space
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1" />

    <Button
        android:id="@+id/btn_siguiente"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Siguiente" />

</LinearLayout>
```

**Activity correspondiente:** `SpaceBasicActivity.kt`

```{.kotlin .numberLines}
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.layouts.databinding.ActivitySpaceBasicBinding

class SpaceBasicActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpaceBasicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpaceBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinal.setOnClickListener {
            Log.d("Space", "Botón al final presionado")
            Toast.makeText(this, "Botón final", Toast.LENGTH_SHORT).show()
        }
    }
}
```

### Ventajas sobre Margin/Padding

Aunque `margin` y `padding` son suficientes para la mayoría de los casos, `Space` ofrece ventajas específicas cuando se necesita un espaciado flexible o dinámico. La siguiente tabla compara las características de cada enfoque.

<div class="tabla" style="--col1:20%; --col2:30%; --col3:25%; --col4:25%;">
| Aspecto | Space | Margin | Padding |
| :--- | :--- | :--- | :--- |
| **Visibilidad** | Invisible | No controla visibilidad | No controla visibilidad |
| **Flexibilidad** | Puede usar `layout_weight` (tamaño variable) | Valor fijo (dp) | Valor fijo (dp) |
| **Responsive** | Sí, con weight | No | No |
| **Uso recomendado** | Espaciado dinámico entre elementos | Espacio exterior fijo | Espacio interior fijo |
</div>

**Resumen rápido:**

<div class="tabla" style="--col1:25%; --col2:75%;">
| Uso | Código |
| :--- | :--- |
| Espacio fijo vertical | `<Space android:layout_height="16dp" android:layout_width="match_parent" />` |
| Espacio flexible horizontal | `<Space android:layout_weight="1" android:layout_width="0dp" android:layout_height="match_parent" />` |
</div>

> **Reglas de Oro para Space**
>
> 1. **Usa `Space` con `layout_weight` para espaciado flexible:** Es el patrón moderno y responsivo.
> 2. **Prefiere `Space` en lugar de `margin` cuando necesitas que el espacio se adapte a la pantalla:** Especialmente útil en barras de herramientas o pies de página.
> 3. **`Space` es invisible — no aparece en preview:** Es normal; solo verás su área en el editor.
> 4. **No uses `Space` para separaciones muy pequeñas (menos de 8dp):** Para esos casos, `margin` es más legible.

::: note
> 📖 Consulta la documentación oficial de Space:
> - [Space – Android Developers](https://developer.android.com/reference/android/widget/Space)
:::

---

## Organización de los Ejemplos y Visualización en el Simulador

Para poder visualizar cada Layout de forma independiente en el simulador, se recomienda crear una **Activity principal (`MainActivity`)** con botones que lancen cada ejemplo mediante `Intent` explícito. De esta manera, cada Layout tendrá su propio archivo XML y su propia Activity, facilitando el estudio y la experimentación.

### Estructura de Archivos Propuesta

A continuación se muestra la organización de archivos sugerida para mantener el código ordenado y cada ejemplo autocontenido.

```{.text .numberLines}
app/
├── java/.../ (paquete de la app)
│   ├── MainActivity.kt
│   ├── LinearBasicActivity.kt
│   ├── LinearLoginActivity.kt
│   ├── LinearCardActivity.kt
│   ├── LinearNestedActivity.kt
│   ├── LinearWeightActivity.kt
│   ├── FrameBasicActivity.kt
│   ├── FrameBadgeActivity.kt
│   ├── FrameFragmentActivity.kt
│   ├── RelativeBasicActivity.kt
│   ├── RelativeTransformActivity.kt
│   ├── ConstraintBasicActivity.kt
│   ├── ConstraintGuidelineActivity.kt
│   ├── ConstraintBarrierActivity.kt
│   ├── ConstraintChainActivity.kt
│   ├── TableBasicActivity.kt
│   ├── TableDinamicActivity.kt
│   ├── GridBasicActivity.kt
│   ├── GridCalculatorActivity.kt
│   ├── GridGaleriaActivity.kt
│   ├── SpaceBasicActivity.kt
│	└──	ReporteFragment.kt
└── res/layout/
    ├── activity_main.xml
    ├── activity_linear_basic.xml
    ├── activity_linear_login.xml
    ├── activity_linear_card.xml
    ├── activity_linear_nested.xml
    ├── activity_linear_weight.xml
    ├── activity_frame_basic.xml
    ├── activity_frame_badge.xml
    ├── activity_frame_fragment.xml
    ├── activity_relative_basic.xml
    ├── activity_relative_transform.xml
    ├── activity_constraint_basic.xml
    ├── activity_constraint_guideline.xml
    ├── activity_constraint_barrier.xml
    ├── activity_constraint_chain.xml
    ├── activity_table_basic.xml
    ├── activity_table_dinamic.xml
    ├── activity_grid_basic.xml
    ├── activity_grid_calculator.xml
    ├── activity_grid_galeria.xml
    ├── activity_space_basic.xml
    └── fragment_reporte.xml
```

### Código de la Actividad Principal (`MainActivity.kt`)

La siguiente clase `MainActivity` contiene los botones que, al ser presionados, lanzan cada una de las Activities de ejemplo mediante `Intent` explícito.

```{.kotlin .numberLines}
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MainActivity", "Aplicación iniciada")
        Toast.makeText(this, "Bienvenido a la demo de Layouts", Toast.LENGTH_SHORT).show()

        // LinearLayout
        binding.btnLinearBasic.setOnClickListener {
            Log.d("Navigation", "Abriendo LinearBasicActivity")
            startActivity(Intent(this, LinearBasicActivity::class.java))
        }
        binding.btnLinearLogin.setOnClickListener {
            startActivity(Intent(this, LinearLoginActivity::class.java))
        }
        binding.btnLinearCard.setOnClickListener {
            startActivity(Intent(this, LinearCardActivity::class.java))
        }
        binding.btnLinearNested.setOnClickListener {
            startActivity(Intent(this, LinearNestedActivity::class.java))
        }
        binding.btnLinearWeight.setOnClickListener {
            startActivity(Intent(this, LinearWeightActivity::class.java))
        }

        // FrameLayout
        binding.btnFrameBasic.setOnClickListener {
            startActivity(Intent(this, FrameBasicActivity::class.java))
        }
        binding.btnFrameBadge.setOnClickListener {
            startActivity(Intent(this, FrameBadgeActivity::class.java))
        }
        binding.btnFrameFragment.setOnClickListener {
            startActivity(Intent(this, FrameFragmentActivity::class.java))
        }

        // RelativeLayout
        binding.btnRelativeBasic.setOnClickListener {
            startActivity(Intent(this, RelativeBasicActivity::class.java))
        }

        // ConstraintLayout
        binding.btnConstraintBasic.setOnClickListener {
            startActivity(Intent(this, ConstraintBasicActivity::class.java))
        }
        binding.btnConstraintGuideline.setOnClickListener {
            startActivity(Intent(this, ConstraintGuidelineActivity::class.java))
        }
        binding.btnConstraintBarrier.setOnClickListener {
            startActivity(Intent(this, ConstraintBarrierActivity::class.java))
        }
        binding.btnConstraintChain.setOnClickListener {
            startActivity(Intent(this, ConstraintChainActivity::class.java))
        }

        // TableLayout
        binding.btnTableBasic.setOnClickListener {
            startActivity(Intent(this, TableBasicActivity::class.java))
        }
        binding.btnTableDinamic.setOnClickListener {
            startActivity(Intent(this, TableDinamicActivity::class.java))
        }
		
        binding.btnFragmetReport.setOnClickListener {
            startActivity(Intent(this, ReporteFragment::class.java))
        }
		
        // GridLayout
        binding.btnGridBasic.setOnClickListener {
            startActivity(Intent(this, GridBasicActivity::class.java))
        }
        binding.btnGridCalculator.setOnClickListener {
            startActivity(Intent(this, GridCalculatorActivity::class.java))
        }
        binding.btnGridGaleria.setOnClickListener {
            startActivity(Intent(this, GridGaleriaActivity::class.java))
        }

        // Space
        binding.btnSpaceBasic.setOnClickListener {
            startActivity(Intent(this, SpaceBasicActivity::class.java))
        }
		
		
    }
}
```

### Layout de la Actividad Principal (`activity_main.xml`)

El archivo de layout asociado a `MainActivity` contiene los botones organizados en grupos según el tipo de Layout. Asegúrate de incluir un botón para `LinearBasicActivity` (el ejemplo básico).

```{.xml .numberLines}
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Ejemplos de Layouts"
            android:textSize="24sp"
            android:textStyle="bold"
            android:layout_marginBottom="16dp" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="LinearLayout"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnLinearBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Básico (Vertical/Horizontal)" />

        <Button
            android:id="@+id/btnLinearLogin"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Login (Vertical)" />

        <Button
            android:id="@+id/btnLinearCard"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Card Horizontal" />

        <Button
            android:id="@+id/btnLinearNested"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Estructura Anidada" />

        <Button
            android:id="@+id/btnLinearWeight"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Responsive con weight" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="FrameLayout"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnFrameBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Básico (Superposición)" />

        <Button
            android:id="@+id/btnFrameBadge"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Badge sobre Imagen" />

        <Button
            android:id="@+id/btnFrameFragment"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Contenedor de Fragment" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="RelativeLayout"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnRelativeBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Posicionamiento Relativo" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="ConstraintLayout"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnConstraintBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Básico (Constraints)" />

        <Button
            android:id="@+id/btnConstraintGuideline"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Guidelines" />

        <Button
            android:id="@+id/btnConstraintBarrier"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Barriers" />

        <Button
            android:id="@+id/btnConstraintChain"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Chains" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="TableLayout"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnTableBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Tabla Básica" />

        <Button
            android:id="@+id/btnTableDinamic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Tabla Dinámica" />
			
        <Button
            android:id="@+id/btnFragmetReport"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Fragmet Report" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="GridLayout"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnGridBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Galería Básica" />

        <Button
            android:id="@+id/btnGridCalculator"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Calculadora" />

        <Button
            android:id="@+id/btnGridGaleria"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Grid Dinámico" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Space"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />

        <Button
            android:id="@+id/btnSpaceBasic"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Espaciador Invisible" />

    </LinearLayout>
</ScrollView>
```

### Cómo Ejecutar en el Simulador

1. **Crea todas las Activities y layouts** según los nombres indicados en la estructura de archivos.
2. **Asegúrate de registrar todas las Activities** en el `AndroidManifest.xml`. Por ejemplo:

```{.xml .numberLines}
<activity android:name=".MainActivity" android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
<activity android:name=".LinearBasicActivity" />
<activity android:name=".LinearLoginActivity" />
<activity android:name=".LinearCardActivity" />
<activity android:name=".LinearNestedActivity" />
<activity android:name=".LinearWeightActivity" />
<!-- ... y así sucesivamente ... -->
```

3. **Compila y ejecuta** la app. La `MainActivity` mostrará botones para cada ejemplo. Al presionar un botón, se abrirá la Activity correspondiente con el layout específico.

Este enfoque didáctico permite estudiar cada Layout de forma aislada, facilitando la comprensión y la experimentación.

---

## Conclusión

Los **Layouts** son la base de cualquier interfaz Android. Cada uno tiene un propósito específico y elegir el correcto desde el inicio es fundamental para un diseño eficiente y mantenible.

### Reglas de Oro Generales para Layouts

1. **Elige el Layout adecuado para cada caso:** No intentes hacer una calculadora con `LinearLayout` puro; usa `GridLayout`. No hagas una pantalla de login compleja con `RelativeLayout`; usa `ConstraintLayout`.
2. **Evita anidamiento profundo:** Máximo 3-4 niveles de anidamiento. El anidamiento excesivo degrada el rendimiento dramáticamente. Usa `ConstraintLayout` para reducir niveles.
3. **Usa `layout_weight` para diseño responsivo:** No calcules tamaños fijos en dp para elementos que deben adaptarse a diferentes pantallas.
4. **ConstraintLayout es el Layout del presente y futuro:** Migra a él en todos los proyectos nuevos. Es el recomendado por Google.
5. **Prueba en diferentes tamaños de pantalla y orientaciones:** Usa el editor de diseño de Android Studio con diferentes dispositivos virtuales.
6. **Proporciona `contentDescription` en imágenes:** Es obligatorio para accesibilidad, incluso dentro de layouts.
7. **Soporta RTL (idiomas de derecha a izquierda):** Usa `start`/`end` en lugar de `left`/`right` en todos los layouts que lo permitan (`ConstraintLayout`, `RelativeLayout`, `LinearLayout`).
8. **Mantén los layouts simples y legibles:** Un XML bien estructurado es más fácil de mantener que uno lleno de atributos redundantes.

### Documentación Oficial Recomendada

- [Guía de Layouts en Android](https://developer.android.com/develop/ui/views/layout/declaring-layout)
- [ConstraintLayout](https://developer.android.com/training/constraint-layout)
- [LinearLayout](https://developer.android.com/reference/android/widget/LinearLayout)
- [FrameLayout](https://developer.android.com/reference/android/widget/FrameLayout)
- [RelativeLayout](https://developer.android.com/reference/android/widget/RelativeLayout)
- [TableLayout](https://developer.android.com/reference/android/widget/TableLayout)
- [GridLayout](https://developer.android.com/reference/android/widget/GridLayout)
- [Space](https://developer.android.com/reference/android/widget/Space)

::: note
> La elección del Layout correcto es una de las decisiones de diseño más importantes en Android. Tómate el tiempo para analizar la estructura de tu interfaz antes de empezar a codificar. Un buen diseño de layouts reduce errores, mejora el rendimiento y facilita el mantenimiento futuro.
:::
