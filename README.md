<div align="center">

# Carrito Java

**Ejercicio de programación orientada a objetos: carrito de compra con validaciones, importes exactos y pruebas automatizadas.**

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/CI-GitHub_Actions-2088FF?style=flat-square&logo=githubactions&logoColor=white)
![BigDecimal](https://img.shields.io/badge/Moneda-BigDecimal-315FAC?style=flat-square)
![GitHub Pages](https://img.shields.io/badge/Demo-GitHub_Pages-111827?style=flat-square&logo=github)

[**Probar demo visual**](https://juanjogamero.github.io/Carrito/) · [**Código Java**](src/main/java/dev/juanjogamero/carrito) · [**Ver pruebas**](src/test/java/dev/juanjogamero/carrito/CarritoTest.java) · [**GitHub Actions**](https://github.com/juanjogamero/Carrito/actions/workflows/pages.yml)

</div>

---

## Descripción

**Carrito Java** es una práctica educativa de **Java y programación orientada a objetos** que gestiona una cesta de productos mediante clases y operaciones sencillas. El proyecto original incluía `Carrito.java`, `Elemento.java` y `main.java`. Esta versión conserva esa idea central y la amplía con estructura convencional de proyecto, cálculo preciso de importes y pruebas de regresión.

> La aplicación principal se ejecuta **en consola Java**, no en un navegador. La [demo de GitHub Pages](https://juanjogamero.github.io/Carrito/) es una representación independiente en HTML, CSS y JavaScript de las mismas operaciones; **no ejecuta código Java** ni realiza compras.

## Funcionalidades

| Operación | Comportamiento |
| --- | --- |
| Añadir artículos | Registra producto, precio unitario y cantidad |
| Agrupar repetidos | Si se añade el mismo nombre con el mismo precio, acumula cantidades |
| Actualizar cantidades | Cambia unidades; cantidad cero elimina el artículo |
| Eliminar productos | Elimina por nombre, sin distinguir mayúsculas |
| Vaciar carrito | Elimina todos los productos |
| Consultar unidades | Distingue entre productos diferentes y unidades totales |
| Calcular importe | Suma subtotales exactos mediante `BigDecimal` |
| Validar datos | Rechaza nombres vacíos, precios negativos, cantidades inválidas y precios inconsistentes |
| Proteger estado | Objetos de producto inmutables y lista de solo lectura |

## Estructura

```text
Carrito/
├── src/
│   ├── main/java/dev/juanjogamero/carrito/
│   │   ├── Carrito.java
│   │   ├── Elemento.java
│   │   └── Main.java
│   └── test/java/dev/juanjogamero/carrito/
│       └── CarritoTest.java
├── docs/
│   └── index.html              # Demo visual independiente
└── .github/workflows/
    └── pages.yml               # Compila, prueba y despliega
```

## Requisitos

- JDK **17** o superior.
- No se necesitan bibliotecas externas ni base de datos.

## Compilar y ejecutar

Desde la raíz del repositorio:

```bash
mkdir -p build/classes
javac -encoding UTF-8 -d build/classes src/main/java/dev/juanjogamero/carrito/*.java
java -cp build/classes dev.juanjogamero.carrito.Main
```

En Windows PowerShell también puedes compilar con:

```powershell
New-Item -ItemType Directory -Force build/classes | Out-Null
javac -encoding UTF-8 -d build/classes src/main/java/dev/juanjogamero/carrito/*.java
java -cp build/classes dev.juanjogamero.carrito.Main
```

## Uso de la API

```java
import dev.juanjogamero.carrito.Carrito;
import dev.juanjogamero.carrito.Elemento;
import java.math.BigDecimal;

Carrito carrito = new Carrito();
carrito.agregar(new Elemento("Tarjeta SD 64 GB", new BigDecimal("19.95"), 2));
carrito.agregar(new Elemento("Canon EOS 2000D", new BigDecimal("449.00"), 1));

System.out.println(carrito.importeTotal()); // 488.90

carrito.actualizarCantidad("Tarjeta SD 64 GB", 3);
carrito.eliminar("Canon EOS 2000D");
carrito.vaciar();
```

## Pruebas automatizadas

El archivo `CarritoTest.java` incluye comprobaciones de importes, agrupación, actualización, eliminación, carrito vacío, datos inválidos y protección de la lista.

```bash
javac -encoding UTF-8 -d build/classes src/main/java/dev/juanjogamero/carrito/*.java src/test/java/dev/juanjogamero/carrito/*.java
java -ea -cp build/classes dev.juanjogamero.carrito.CarritoTest
```

Estas pruebas son autocontenidas y **no requieren JUnit ni dependencias adicionales**. GitHub Actions ejecuta la suite y el ejemplo de consola antes de intentar publicar la demo.

## Demo de GitHub Pages

El workflow `.github/workflows/pages.yml` publica `docs/` desde la rama **master**. Para habilitarlo:

1. Entra en [Settings → Pages](https://github.com/juanjogamero/Carrito/settings/pages).
2. Selecciona **GitHub Actions** en `Build and deployment → Source`.
3. Abre [Test Java and deploy showcase](https://github.com/juanjogamero/Carrito/actions/workflows/pages.yml), pulsa **Run workflow** y selecciona `master`.
4. Tras la ejecución correcta, abre **https://juanjogamero.github.io/Carrito/**.

## Alcance

Esta es una implementación **educativa y en memoria**. No hay persistencia, autenticación, inventario, impuestos configurables, checkout ni integración de pagos. Para una aplicación comercial serían necesarios servicios adicionales, almacenamiento y tests de integración.

---

<div align="center">Proyecto Java · Programación orientada a objetos · Práctica y portfolio</div>
