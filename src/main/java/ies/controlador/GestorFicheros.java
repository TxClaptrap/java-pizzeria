package ies.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ies.modelo.Cliente;

public class GestorFicheros {

    //
    public static List<Cliente> leerArchivo(String ruta) throws IOException {
        Stream<String> lineas = Files.lines(Path.of(ruta));
        {
            return lineas.map(linea -> {
                List<String> cosas = List.of(linea.split("[,;|]"));
                cosas = cosas.stream().map(String::trim).toList();

                return new Cliente(Integer.parseInt(cosas.get(0)), cosas.get(1), cosas.get(2), cosas.get(3),
                        cosas.get(4),
                        cosas.get(5), cosas.get(6), true);
            }).toList();
        }
    }

    public static boolean exportarXML(List<Cliente> clientes) throws JAXBException {
        ClientesWrapper wrap = new ClientesWrapper(clientes);
        JAXBContext context = JAXBContext.newInstance(Cliente.class, ClientesWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(wrap, new File("clientes.xml"));
        return true;
    }

}
