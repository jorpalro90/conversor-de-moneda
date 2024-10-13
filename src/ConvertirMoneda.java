import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ConvertirMoneda {

    private static final String API_URL =
            "https://v6.exchangerate-api.com/v6/5f4619bd83d5b3888892757d/latest/USD";
    private static final String RUTA_ARCHIVO_JSON = "tasas_cambio.json";
    private static Map<String, Double> tasasCambio;

    public static void obtenerTasasCambio() throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() == 200) {
            procesarRespuestaJSON(respuesta.body());
            guardarTasasEnArchivo();  // Guardar tasas en archivo.
            System.out.println("Tasas de cambio obtenidas y guardadas con éxito.");
        } else {
            System.out.println("Error al obtener las tasas de cambio: " + respuesta.statusCode());
        }
    }

    private static void procesarRespuestaJSON(String cuerpoJSON) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(cuerpoJSON, JsonObject.class);
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        Type type = new TypeToken<Map<String, Double>>() {}.getType();
        tasasCambio = gson.fromJson(conversionRates, type);

        System.out.println("Tasas de cambio procesadas. Cantidad de tasas: " + tasasCambio.size());
    }

    // Guardar tasas en un archivo JSON con formato bonito (sangría y líneas nuevas).
    private static void guardarTasasEnArchivo() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();  // Configuración de impresión bonita.
        String json = gson.toJson(tasasCambio);

        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO_JSON)) {
            writer.write(json);
        }
        System.out.println("Tasas guardadas en formato legible.");
    }

    public static void cargarTasasDesdeArchivo() throws IOException {
        if (Files.exists(Paths.get(RUTA_ARCHIVO_JSON))) {
            String contenidoJSON = Files.readString(Paths.get(RUTA_ARCHIVO_JSON));
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Double>>() {}.getType();
            tasasCambio = gson.fromJson(contenidoJSON, type);

            if (tasasCambio != null) {
                System.out.println("Tasas de cambio cargadas desde el archivo.");
            } else {
                System.out.println("Error: tasasCambio es null después de cargar.");
            }
        } else {
            System.out.println("No se encontró el archivo de tasas, obteniendo desde la API.");
            try {
                obtenerTasasCambio();
            } catch (InterruptedException e) {
                System.out.println("Error: La operación fue interrumpida.");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void convertir(String origen, String destino, double cantidad) {
        if (tasasCambio != null && tasasCambio.containsKey(origen) && tasasCambio.containsKey(destino)) {
            double tasaOrigen = tasasCambio.get(origen);
            double tasaDestino = tasasCambio.get(destino);
            double resultado = (cantidad / tasaOrigen) * tasaDestino;

            System.out.printf("Resultado: %.2f %s = %.2f %s\n", cantidad, origen, resultado, destino);
        } else {
            System.out.println("Error: Moneda no disponible en la API o tasas no cargadas.");
            System.out.println("Tasas disponibles: " + tasasCambio);
        }
    }
}
