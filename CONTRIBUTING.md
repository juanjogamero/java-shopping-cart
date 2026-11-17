# Guía de contribución

Gracias por interesarte en este proyecto educativo de Java.

## Preparación

Necesitas **JDK 17 o superior**. No hay dependencias externas ni gestor de paquetes obligatorio.

```bash
mkdir -p build/classes
javac -encoding UTF-8 -d build/classes src/main/java/dev/juanjogamero/carrito/*.java src/test/java/dev/juanjogamero/carrito/*.java
java -ea -cp build/classes dev.juanjogamero.carrito.CarritoTest
```

En Windows PowerShell, crea el directorio con `New-Item -ItemType Directory -Force build/classes` en lugar de `mkdir -p`.

## Antes de enviar una pull request

- Mantén la lógica del carrito en **Java 17**, dentro de `src/main/java`.
- Utiliza `BigDecimal` para importes monetarios; no sustituyas los cálculos por `float` o `double`.
- Añade pruebas a `CarritoTest.java` al cambiar reglas del carrito.
- Ejecuta las pruebas y el ejemplo de consola localmente.
- Describe el problema, el cambio y cómo lo comprobaste en la pull request.

## Demo web

La carpeta `docs/` contiene una representación visual **independiente** en HTML/CSS/JavaScript. La web no ejecuta la lógica Java ni procesa pagos. Si actualizas la lógica Java, revisa que el comportamiento equivalente de la demo continúe siendo coherente.

## Proponer mejoras

Abre una issue para explicar errores reproducibles o propuestas concretas. Evita publicar credenciales, datos personales o información sensible.
