## Conversor de Moneda
Este proyecto es un **conversor de monedas** en Java que permite convertir valores entre distintas monedas usando las tasas de cambio obtenidas desde una API pública. Las tasas de cambio se guardan en un archivo JSON para uso posterior sin conexión.

### Características
•	Obtiene las tasas de cambio desde la API de ExchangeRate-API.
•	Guarda las tasas en un archivo JSON con formato legible.
•	Carga las tasas desde el archivo si está disponible, evitando solicitudes repetidas a la API.
•	Ofrece un menú interactivo para realizar conversiones de moneda en tiempo real.
•	Soporta múltiples pares de conversión.

### Requisitos
•	Java 11 o superior.
•	Gson (Librería para manipular JSON). Descargar Gson

**Nota**: Asegúrate de incluir la librería Gson en el classpath al compilar y ejecutar.

### Instalación y Ejecución

1.	**Clona** el repositorio o descarga los archivos.
2.	**Asegúrate** de tener la librería Gson en tu proyecto.
3.	**Compila** los archivos:

### Uso
1.	Al ejecutar el programa, cargará las tasas desde el archivo tasas_cambio.json si existe. Si no, las obtendrá desde la API.
2.	El menú interactivo permitirá elegir opciones de conversión entre monedas predefinidas.
3.	Si eliges una opción de conversión, se te pedirá ingresar la cantidad y el resultado será mostrado en pantalla.

### Opciones de Conversión
1. ARS → BRL
2. BOB → CLP
3. COP → USD
4. COP → BRL
5. COP → ARS
6. COP → BOB
7. BRL → USD
8. Salir

### Equivalencias 
| Abreviatura  | 	País / Moneda |
| ------------- | ------------- |
| ARS  | Peso argentino  |
| BRL  | Real brasileño  |
| BOB  | Peso boliviano  |
| CLP  | Peso chileno  |
| COP  | Peso colombiano  |
| USD  | Dólar estadounidense  |

