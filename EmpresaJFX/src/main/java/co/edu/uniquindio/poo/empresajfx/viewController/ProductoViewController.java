package co.edu.uniquindio.poo.empresajfx.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.empresajfx.App;
import co.edu.uniquindio.poo.empresajfx.controller.ProductoController;
import co.edu.uniquindio.poo.empresajfx.model.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductoViewController {

    ProductoController productoController;
    ObservableList<Producto> listProductos = FXCollections.observableArrayList();
    Producto selectedProducto;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCategoria;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private TableView<Producto> tblListProducto;

    @FXML
    private TableColumn<Producto, String> tbcCodigo;

    @FXML
    private TableColumn<Producto, String> tbcNombre;

    @FXML
    private TableColumn<Producto, String> tbcCategoria;

    private App app;

    @FXML
    void onAgregarProducto() {
        agregarProducto();
    }

    @FXML
    void onActualizarProducto() {
        actualizarProducto();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarProducto();
    }

    @FXML
    void initialize() {
        productoController = new ProductoController(App.empresa);
        initView();
    }

    private void initView() {
        // Traer los datos del producto a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerProductos();

        // Limpiar la tabla
        tblListProducto.getItems().clear();

        // Agregar los elementos a la tabla
        tblListProducto.setItems(listProductos);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void initDataBinding() {
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
    }

    private void obtenerProductos() {
        listProductos.addAll(productoController.obtenerListaProductos());
    }

    private void listenerSelection() {
        tblListProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            mostrarInformacionProducto(selectedProducto);
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        if (producto != null) {
            txtCodigo.setText(producto.getCodigo());
            txtNombre.setText(producto.getNombre());
            txtCategoria.setText(producto.getCategoria());
        }
    }

    private void agregarProducto() {
        Producto producto = buildProducto();
        if (productoController.crearProducto(producto)) {
            listProductos.add(producto);
            limpiarCamposProducto();
        }
    }

    private Producto buildProducto() {
        Producto producto = new Producto(txtCodigo.getText(), txtNombre.getText(), txtCategoria.getText());
        return producto;
    }

    private void eliminarProducto() {
        if (productoController.eliminarProducto(txtCodigo.getText())) {
            listProductos.remove(selectedProducto);
            limpiarCamposProducto();
            limpiarSeleccion();
        }
    }

    private void actualizarProducto() {

        if (selectedProducto != null &&
                productoController.actualizarProducto(selectedProducto.getCodigo(), buildProducto())) {

            int index = listProductos.indexOf(selectedProducto);
            if (index >= 0) {
                listProductos.set(index, buildProducto());
            }

            tblListProducto.refresh();
            limpiarSeleccion();
            limpiarCamposProducto();
        }
    }

    private void limpiarSeleccion() {
        tblListProducto.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCategoria.clear();
    }

    public void setApp(App app) {
        this.app = app;
    }
}
