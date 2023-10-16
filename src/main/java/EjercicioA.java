import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EjercicioA {
    public static void main(String[] args) {
        try {
            // Crear un ObjectMapper para manejar JSON.
            ObjectMapper objectMapper = new ObjectMapper();

            // Leer el archivo JSON.
            JsonNode jsonNode = objectMapper.readTree(new File("src\\car_sales.json"));

            // Maps para almacenar datos.
            Map<String, Double> preciosPorMarca = new HashMap<>();
            Map<String, Integer> carrosPorMarca = new HashMap<>();

            for (JsonNode elemento : jsonNode) {
                // Obtener la marca del carro de cada elemento.
                String marca = elemento.get("car").asText();
                // Obtener el precio y convertirlo a un número decimal.
                double precio = Double.parseDouble(elemento.get("price").asText().substring(1));

                // Actualizar los maps con información por marca de carro.
                preciosPorMarca.put(marca, preciosPorMarca.getOrDefault(marca, 0.0) + precio);
                carrosPorMarca.put(marca, carrosPorMarca.getOrDefault(marca, 0) + 1);
            }

            // Imprimir el reporte de precio promedio por marca.
            System.out.println("Precio promedio por marca de carro:");
            for (String marca : preciosPorMarca.keySet()) {
                double precioTotal = preciosPorMarca.get(marca);
                int contador = carrosPorMarca.get(marca);
                double precioPromedio = precioTotal / contador;
                System.out.println(marca + ": $" + precioPromedio);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}