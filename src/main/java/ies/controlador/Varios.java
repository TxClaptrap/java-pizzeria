package ies.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import ies.modelo.Cliente;
import ies.modelo.Ingrediente;

public class Varios {
    private final String rutaTXT = "administradores.txt";
    private final String rutaXML = "clientes.xml";
    private final String rutaCSV = "ingredientes.csv";

    // Lector a pelo
    public List<Cliente> leerAdministradores() throws IOException {
        try (Stream<String> lineas = Files.lines(Path.of(rutaTXT))) {
            return lineas.map(linea -> {
                List<String> administradores = List.of(linea.split("[,;|]"));
                administradores = administradores.stream().map(String::trim).toList();

                return new Cliente(Integer.parseInt(administradores.get(0)), administradores.get(1),
                        administradores.get(2), administradores.get(3),
                        administradores.get(4),
                        administradores.get(5), administradores.get(6), true);
            }).toList();
        }
    }

    // Sin librería (el fichero no tiene encabezado, tampoco impide exportar con
    // separadores admitidos en la importación, pero esto no había que hacerlo)
    public boolean exportarAdministradoresTXT(List<Cliente> clientes) throws IOException {
        Files.write(Path.of(rutaTXT),
                clientes.stream().map(cliente -> clienteToString(cliente, ",")).toList());
        return true;
    }

    private static String clienteToString(Cliente cliente, String separador) {
        return String.format("%d%s%s%s%s%s%s%s%s%s%s%s%s", cliente.getId(), separador, cliente.getDni(), separador,
                cliente.getNombre(), separador, cliente.getDireccion(), separador, cliente.getTelefono(), separador,
                cliente.getEmail(), separador, cliente.getPassword());
    }

    // Lista de clientes a XML
    public boolean exportarXML(List<Cliente> clientes) throws JAXBException {
        ClientesWrapper wrap = new ClientesWrapper(clientes);
        JAXBContext context = JAXBContext.newInstance(Cliente.class, ClientesWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrap, new File(rutaXML));
        return true;
    }

    // XML a lista de clientes
    public List<Cliente> importarXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ClientesWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ClientesWrapper wrap = (ClientesWrapper) unmarshaller.unmarshal(new File(rutaXML));
        return wrap.getListaClientes();
    }

    // Lista de ingredientes a CSV
    public boolean exportarCSV(List<Ingrediente> ingredientes)
            throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        File archivoCSV = new File(rutaCSV);

        try (PrintWriter pw = new PrintWriter(archivoCSV)) {
            StatefulBeanToCsv<Ingrediente> beanToCsv = new StatefulBeanToCsvBuilder<Ingrediente>(pw).withSeparator(';')
                    .build();
            beanToCsv.write(ingredientes);

            return true;
        }
    }

    public List<Ingrediente> importarCSV() throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader(rutaCSV)) {
            CsvToBean<Ingrediente> csvToBean = new CsvToBeanBuilder<Ingrediente>(fr)
                    .withType(Ingrediente.class)
                    .withSeparator(';') // Especifica el delimitador como punto y coma, si no, no lo pilla, ya que es
                                        // "," por defecto.
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}