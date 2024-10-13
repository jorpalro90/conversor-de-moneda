import java.io.IOException;
import java.util.Scanner;

public class Principal {
    private static final ConvertirMoneda ServicioCambio = new ConvertirMoneda();

    public static void main(String[] args) {
        try {
            ServicioCambio.cargarTasasDesdeArchivo();  // Cargar tasas antes de iniciar el menú.
            iniciarMenu();  // Iniciar menú interactivo.
        } catch (IOException e) {
            System.out.println("Error al conectar con la API o al leer el archivo: " + e.getMessage());
        }
    }

    // Menú interactivo para realizar conversiones.
    private static void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        double cantidad;

        while (true) {
            System.out.println("\n**** Conversor de Monedas ****");
            System.out.println("1. Convertir ARS -> BRL");
            System.out.println("2. Convertir BOB -> CLP");
            System.out.println("3. Convertir COP -> USD");
            System.out.println("4. Convertir COP -> BRL");
            System.out.println("5. Convertir COP -> ARS");
            System.out.println("6. Convertir COP -> BOB");
            System.out.println("7. Convertir BRL -> USD");
            System.out.println("8. Salir");
            System.out.println("**** Elige una opción \u2191 ****");

            int opcion = scanner.nextInt();
            if (opcion == 8) {
                System.out.println("¡Gracias por usar el conversor de monedas!");
                break;
            }

            System.out.print("Ingresa la cantidad: ");
            cantidad = scanner.nextDouble();

            switch (opcion) {
                case 1 -> ServicioCambio.convertir("ARS", "BRL", cantidad);
                case 2 -> ServicioCambio.convertir("BOB", "CLP", cantidad);
                case 3 -> ServicioCambio.convertir("COP", "USD", cantidad);
                case 4 -> ServicioCambio.convertir("COP", "BRL", cantidad);
                case 5 -> ServicioCambio.convertir("COP", "ARS", cantidad);
                case 6 -> ServicioCambio.convertir("COP", "BOB", cantidad);
                case 7 -> ServicioCambio.convertir("BRL", "USD", cantidad);
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }
        }
        scanner.close();
    }
}
