package co.edu.uniquindio.poo.model;

public class EmpleadoTemporal extends Empleado {

    private int diasTrabajados;
    private float valorDia;

    public EmpleadoTemporal(String nombre, String documento, int edad, float salarioBase,
                            CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                            int diasTrabajados, float valorDia) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        setDiasTrabajados(diasTrabajados);
        setValorDia(valorDia);
    }

    public float calcularPagoDias() {
        return diasTrabajados * valorDia;
    }

    @Override
    public float calcularSalarioBruto() {
        return calcularPagoDias() + calcularBonificacionCategoria();
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion()
                + "\nDías trabajados: " + diasTrabajados
                + "\nValor por día: " + valorDia
                + "\nPago por días: " + calcularPagoDias();
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        if (diasTrabajados < 0) {
            throw new IllegalArgumentException("Los días trabajados no pueden ser negativos.");
        }
        this.diasTrabajados = diasTrabajados;
    }

    public float getValorDia() {
        return valorDia;
    }

    public void setValorDia(float valorDia) {
        if (valorDia < 0) {
            throw new IllegalArgumentException("El valor por día no puede ser negativo.");
        }
        this.valorDia = valorDia;
    }
}