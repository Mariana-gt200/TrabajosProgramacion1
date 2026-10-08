package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.*;

public class Tienda {

    private final String nombre;
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final List<Factura> listaFacturas = new LinkedList<>();
    private final Map<String, Producto> hashMapListaProductos = new HashMap<>();

    public Tienda(String nombre, String nit, String telefono) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }

    // Setters y getters
    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // CRUD CLIENTE
    //Registrar usuario
    public String registrarCliente(Cliente cliente) {
        return buscarCliente(cliente.getDocumentoIdentidad())
                .map(c -> "No se puede registrar, ya existe un cliente con esa informacion registrado anteriormente.")
                .orElseGet(() -> {
                    listaClientes.add(cliente);
                    return "El cliente fue registrado exitosamente";
                });
    }

    // Buscar cliente por documento de identidad
    public Optional<Cliente> buscarCliente(String documentoIdentidad) {
        return listaClientes.stream()
                .filter(c -> c.getDocumentoIdentidad().equals(documentoIdentidad))
                .findFirst();
    }

    //Lista de los clientes
    public List<Cliente> obtenerClientes() {
        return Collections.unmodifiableList(listaClientes);
    }

    //Actualizar cliente
    public String actualizarCliente(String documentoIdentidad, Cliente clienteActualizado) {
        if (!documentoIdentidad.equals(clienteActualizado.getDocumentoIdentidad())) {
            return "No se puede actualizar, el documento de identidad no puede modificarse.";
        }
        return buscarCliente(documentoIdentidad)
                .map(c -> {
                    listaClientes.set(listaClientes.indexOf(c), clienteActualizado);
                    return "El cliente fue actualizado exitosamente";
                })
                .orElse("No se puede actualizar, no existe un cliente con ese documento.");
    }

    // Eliminar cliente
    public String eliminarCliente(String documentoIdentidad) {
        return buscarCliente(documentoIdentidad)
                .map(c -> {
                    if (!c.getListaFacturas().isEmpty()) {
                        return "No se puede eliminar, el cliente tiene facturas asociadas.";
                    }
                    listaClientes.remove(c);
                    return "El cliente fue eliminado exitosamente";
                })
                .orElse("No se puede eliminar, no existe un cliente con ese documento.");
    }

    // CRUD PRODUCTO
    //Registrar producto
    public String registrarProducto(Producto producto) {
        if (buscarProducto(producto.getCodigo()).isPresent()) {
            return "No se puede registrar, ya existe un producto con ese codigo.";
        }
        hashMapListaProductos.put(producto.getCodigo(), producto);
        return "El producto fue registrado exitosamente";
    }

    // Buscar producto por codigo
    public Optional<Producto> buscarProducto(String codigo) {
        if (hashMapListaProductos.containsKey(codigo)) {
            return Optional.of(hashMapListaProductos.get(codigo));
        }
        return Optional.empty();
    }

    // Lista de los productos
    public Collection<Producto> obtenerProductos() {
        return Collections.unmodifiableCollection( hashMapListaProductos.values());
    }

    // Actualizar producto
    public String actualizarProducto(String codigo, Producto productoActualizado) {
        if (!codigo.equals(productoActualizado.getCodigo())) {
            return "No se puede actualizar, el codigo del producto no puede modificarse.";
        }
        return buscarProducto(codigo)
                .map(p -> {
                    hashMapListaProductos.put(codigo, productoActualizado);
                    return "El producto fue actualizado exitosamente";
                })
                .orElse("No se puede actualizar, no existe un producto con ese codigo.");
    }

    // Eliminar producto
    public String eliminarProducto(String codigo) {
        return buscarProducto(codigo)
                .map(p -> {
                    hashMapListaProductos.remove(codigo);
                    return "El producto fue eliminado exitosamente";
                })
                .orElse("No se puede eliminar, no existe un producto con ese codigo.");
    }

    // CRUD FACTURA
    // Registrar factura
    public String registrarFactura(Factura factura) {
        if (buscarFactura(factura.codigo()).isPresent()) {
            return "No se puede registrar, ya existe una factura con ese codigo.";
        }
        if (buscarCliente(factura.cliente().getDocumentoIdentidad()).isEmpty()) {
            return "No se puede registrar, el cliente de la factura no esta registrado en la tienda.";
        }
        listaFacturas.add(factura);
        return "La factura fue registrada exitosamente";
    }

    // Buscar factura por codigo
    public Optional<Factura> buscarFactura(String codigo) {
        return listaFacturas.stream()
                .filter(f -> f.codigo().equals(codigo))
                .findFirst();
    }

    // Lista de las facturas
    public List<Factura> obtenerFacturas() {
        return Collections.unmodifiableList(listaFacturas);
    }

    // Actualizar factura
    public String actualizarFactura(String codigo, Factura facturaActualizada) {
        if (!codigo.equals(facturaActualizada.codigo())) {
            return "No se puede actualizar, el codigo de la factura no puede modificarse.";
        }
        return buscarFactura(codigo)
                .map(f -> {
                    listaFacturas.set(listaFacturas.indexOf(f), facturaActualizada);
                    return "La factura fue actualizada exitosamente";
                })
                .orElse("No se puede actualizar, no existe una factura con ese codigo.");
    }

    //Cambiar el estado de la factura
    public String cambiarEstadoFactura(String codigo, EstadoFactura nuevoEstado) {
        return buscarFactura(codigo)
                .map(f -> {
                    Factura copia = new Factura(f.codigo(), f.fecha(), f.total(), nuevoEstado,
                            f.metodoPago(), f.cliente(), f.listaDetallesFactura(), f.ownedByTienda());
                    listaFacturas.set(listaFacturas.indexOf(f), copia);
                    return "El estado de la factura fue actualizado a " + nuevoEstado;
                })
                .orElse("No se puede cambiar el estado, no existe una factura con ese codigo.");
    }

    //Borrar factura
    public String eliminarFactura(String codigo) {
        return buscarFactura(codigo)
                .map(f -> {
                    listaFacturas.remove(f);
                    return "La factura fue eliminada exitosamente";
                })
                .orElse("No se puede eliminar, no existe una factura con ese codigo.");
    }

    // Calcular el valor total de la factura
    // Suma los subtotales de todos los detalles de la factura.
    public double calcularValorFactura(Factura factura) {
        return factura.listaDetallesFactura().stream()
                .mapToDouble(DetalleFactura::getSubTotal)
                .sum();
    }

    // Igual que el anterior, pero buscando la factura por codigo.
    public Optional<Double> calcularValorFactura(String codigo) {
        return buscarFactura(codigo).map(this::calcularValorFactura);
    }
     // Actualizar el total de la factura
    public String actualizarTotalFactura(String codigo) {
        return buscarFactura(codigo)
                .map(f -> {
                    Factura copia = new Factura(f.codigo(), f.fecha(), calcularValorFactura(f),
                            f.estadoFactura(), f.metodoPago(), f.cliente(),
                            f.listaDetallesFactura(), f.ownedByTienda());
                    listaFacturas.set(listaFacturas.indexOf(f), copia);
                    return "El total de la factura fue actualizado a " + copia.total();
                })
                .orElse("No se puede calcular, no existe una factura con ese codigo.");
    }

    //Taller
    //1. Obtener los productos con una cantidad mayor o igual a 10
    public List<Producto> obtenerProductosCantidadMayorA10() {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : hashMapListaProductos.values()) {
            if (producto.getCantidadDisponible() >= 10) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

    //2.Obtener la lista de codigos de los productos con una cantidad disponible mayor igual a 10 y menor que 50
    public List<String> obtenerCodigosProductosEntre10y50() {
        List<String> codigos = new ArrayList<>();
        for (Producto producto : hashMapListaProductos.values()) {
            if (producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50) {
                codigos.add(producto.getCodigo());
            }
        }
        return codigos;
    }

    //3. Obtener la lista de clientes que hayan comprado el 07/10/2026
    public List<Cliente> obtenerClientesPorFecha() {
        List<Cliente> clientes = new ArrayList<>();
        LocalDate fechaBuscada = LocalDate.of(2026, 10, 7);
        for (Factura factura : listaFacturas) {
            if (factura.fecha().equals(fechaBuscada)) {
                clientes.add(factura.cliente());
            }
        }
        return clientes;
    }

    //4. Obtener facturas que tengan un cliente donde su nombre empiece por "R"
    public List<Factura> obtenerFacturasPorInicialCliente(char inicial) {
        List<Factura> facturas = new ArrayList<>();
        for (Factura factura : listaFacturas) {
            if (factura.cliente().getNombreCompleto().charAt(0) == inicial) {
                facturas.add(factura);
            }
        }
        return facturas;
    }


}