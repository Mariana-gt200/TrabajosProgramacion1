package co.edu.uniquindio.poo.model;

public abstract class Empleado {

    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;

    public Empleado(String nombre, String documento, int edad, float salarioBase,
                    CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.categoria = categoria;
        setSalarioBase(salarioBase);
        setDescuentoSalud(descuentoSalud);
        setDescuentoPension(descuentoPension);
    }

    public abstract float calcularSalarioBruto();

    public float calcularBonificacionCategoria() {
        switch (categoria) {
            case JUNIOR:
                return salarioBase * 0.05f;
            case SEMI_SENIOR:
                return salarioBase * 0.10f;
            case SENIOR:
                return salarioBase * 0.15f;
            default:
                return 0;
        }
    }

    public float calcularDescuentos() {
        float salarioBruto = calcularSalarioBruto();
        float salud = salarioBruto * descuentoSalud / 100;
        float pension = salarioBruto * descuentoPension / 100;
        return salud + pension;
    }

    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    public String mostrarInformacion() {
        return "Tipo: " + getClass().getSimpleName()
                + "\nNombre: " + nombre
                + "\nDocumento: " + documento
                + "\nEdad: " + edad
                + "\nCategoría: " + categoria
                + "\nSalario base: " + salarioBase
                + "\nDescuento salud: " + descuentoSalud + "%"
                + "\nDescuento pensión: " + descuentoPension + "%"
                + "\nSalario bruto: " + calcularSalarioBruto()
                + "\nDescuentos: " + calcularDescuentos()
                + "\nSalario neto: " + calcularSalarioNeto();
    }

    public ResumenPago generarResumenPago() {
        return new ResumenPago(documento, nombre, getClass().getSimpleName(), calcularSalarioBruto(), calcularDescuentos(), calcularSalarioNeto());
    }

    //Get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    public CategoriaEmpleado getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }

    public float getDescuentoSalud() {
        return descuentoSalud;
    }

    public void setDescuentoSalud(float descuentoSalud) {
        if (descuentoSalud < 0 || descuentoSalud > 100) {
            throw new IllegalArgumentException("El descuento de salud debe estar entre 0 y 100.");
        }
        this.descuentoSalud = descuentoSalud;
    }

    public float getDescuentoPension() {
        return descuentoPension;
    }

    public void setDescuentoPension(float descuentoPension) {
        if (descuentoPension < 0 || descuentoPension > 100) {
            throw new IllegalArgumentException("El descuento de pensión debe estar entre 0 y 100.");
        }
        this.descuentoPension = descuentoPension;
    }
}