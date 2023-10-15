import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class EjercicioA {
    public static void main(String[] args) {
        try {
            // Analizar el archivo JSON.
            JSONParser parser = new JSONParser();
            // Leer archivo y almacenar en JSONArray.
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src\\car_sales.json"));

            // Maps para almacenar datos.
            Map<String, Double> preciosPorMarca = new HashMap<>();
            Map<String, Integer> carrosPorMarca = new HashMap<>();

            for (Object object : jsonArray) {
                // Obtener los datos del carro de cada objeto.
                JSONObject datosCarro = (JSONObject) object;
                String marca = (String) datosCarro.get("car");
                // Extraer el precio.
                double precio = Double.parseDouble(((String) datosCarro.get("price")).substring(1));

                // Actualizar maps con información por marca de carro.
                preciosPorMarca.put(marca, preciosPorMarca.getOrDefault(marca, 0.0) + precio);
                carrosPorMarca.put(marca, carrosPorMarca.getOrDefault(marca, 0) + 1);
            }

            // Imprimir el reporte.
            System.out.println("Precio promedio por marca de carro:");
            for (String car : preciosPorMarca.keySet()) {
                double precioTotal = preciosPorMarca.get(car);
                int contador = carrosPorMarca.get(car);
                double precioPromedio = precioTotal / contador;
                System.out.println(car + ": $" + precioPromedio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




