package ies.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

public class GestorFicheros {

    // Lector a pelo
    public List<Cliente> leerArchivo(String ruta) throws IOException {
        try (Stream<String> lineas = Files.lines(Path.of(ruta))) {
            return lineas.map(linea -> {
                List<String> cosas = List.of(linea.split("[,;|]"));
                cosas = cosas.stream().map(String::trim).toList();

                return new Cliente(Integer.parseInt(cosas.get(0)), cosas.get(1), cosas.get(2), cosas.get(3),
                        cosas.get(4),
                        cosas.get(5), cosas.get(6), true);
            }).toList();
        }
    }

    // Lista de clientes a XML
    public boolean exportarXML(List<Cliente> clientes) throws JAXBException {
        ClientesWrapper wrap = new ClientesWrapper(clientes);
        JAXBContext context = JAXBContext.newInstance(Cliente.class, ClientesWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrap, new File("clientes.xml"));
        return true;
    }

    // XML a lista de clientes
    public List<Cliente> importarXML(String ruta) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ClientesWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ClientesWrapper wrap = (ClientesWrapper) unmarshaller.unmarshal(new File(ruta));
        return wrap.getListaClientes();
    }

    //Lista de ingredientes a CSV
    public boolean exportarCSV(List<Ingrediente> ingredientes)
            throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        File archivoCSV = new File("ingredientes.csv");

        try (PrintWriter pw = new PrintWriter(archivoCSV)) {
            StatefulBeanToCsv<Ingrediente> beanToCsv = new StatefulBeanToCsvBuilder<Ingrediente>(pw).withSeparator(';').build();
            beanToCsv.write(ingredientes);

            return true;
        }
    }

    public List<Ingrediente> importarCSV(String ruta) throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader("ingredientes.csv")) {
            CsvToBean<Ingrediente> csvToBean = new CsvToBeanBuilder<Ingrediente>(fr)
                    .withType(Ingrediente.class)
                    .withSeparator(';') // Especifica el delimitador como punto y coma, si no, no lo pilla, ya que es "," por defecto.
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }



}
