package co.edu.uniquindio.poo.model;

public class EmpleadoVentas extends Empleado {

    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          float totalVentas, float porcentajeComision) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        this.totalVentas = totalVentas;
        setPorcentajeComision(porcentajeComision);
    }

    public float calcularComision() {
        return totalVentas * porcentajeComision / 100;
    }

    @Override
    public float calcularSalarioBruto() {
        return salarioBase + calcularBonificacionCategoria() + calcularComision();
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion()
                + "\nTotal ventas: " + totalVentas
                + "\nPorcentaje de comisión: " + porcentajeComision + "%"
                + "\nComisión: " + calcularComision();
    }

    public float getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(float totalVentas) {
        this.totalVentas = totalVentas;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100.");
        }
        this.porcentajeComision = porcentajeComision;
    }
}