package co.edu.uniquindio.poo.model;

public class EmpleadoPlanta extends Empleado {

    private String cargo;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte;

    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          String cargo, int horasExtra, float valorHoraExtra, float auxilioTransporte) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        this.cargo = cargo;
        setHorasExtra(horasExtra);
        setValorHoraExtra(valorHoraExtra);
        this.auxilioTransporte = auxilioTransporte;
    }

    @Override
    public float calcularSalarioBruto() {
        float pagoHorasExtra = horasExtra * valorHoraExtra;
        return salarioBase + calcularBonificacionCategoria() + pagoHorasExtra + auxilioTransporte;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion()
                + "\nCargo: " + cargo
                + "\nHoras extra: " + horasExtra
                + "\nValor hora extra: " + valorHoraExtra
                + "\nAuxilio de transporte: " + auxilioTransporte;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        if (horasExtra < 0) {
            throw new IllegalArgumentException("Las horas extra no pueden ser negativas.");
        }
        this.horasExtra = horasExtra;
    }

    public float getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(float valorHoraExtra) {
        if (valorHoraExtra < 0) {
            throw new IllegalArgumentException("El valor de la hora extra no puede ser negativo.");
        }
        this.valorHoraExtra = valorHoraExtra;
    }

    public float getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(float auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }
}
