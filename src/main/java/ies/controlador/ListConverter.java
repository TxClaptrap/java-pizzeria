package ies.controlador;
import com.opencsv.bean.AbstractBeanField;
import java.util.Arrays;
import java.util.List;
//No hace falta porque está el @CsvBindAndSplitByName
public class ListConverter extends AbstractBeanField<List<String>, String> {
    @Override
    protected List<String> convert(String value) {
        if (value == null || value.trim().isEmpty()) {
            return List.of(); // Retorna una lista vacía, por si el valor es nulo o vacío
        }
        return Arrays.asList(value.split(",\\s*")); // Divide por coma y espacio, te tienes que empollar los matches...
    }
}
