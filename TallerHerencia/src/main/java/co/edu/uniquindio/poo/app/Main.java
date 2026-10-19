package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.CategoriaEmpleado;
import co.edu.uniquindio.poo.model.Empleado;
import co.edu.uniquindio.poo.model.Empresa;
import co.edu.uniquindio.poo.model.ResumenPago;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main() {

        Empresa empresa = new Empresa("Mi Empresa");
        int opcion = 0;

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "MENÚ - NÓMINA\n\n"
                                + "1. Agregar empleado de planta\n"
                                + "2. Agregar empleado de ventas\n"
                                + "3. Agregar empleado temporal\n"
                                + "4. Mostrar todos los empleados\n"
                                + "5. Buscar empleado por documento\n"
                                + "6. Mostrar empleado con mayor salario neto\n"
                                + "7. Mostrar nómina total\n"
                                + "8. Mostrar resumen de pagos\n"
                                + "9. Salir\n\n"
                                + "Elija una opción:"));

                switch (opcion) {

                    case 1: {
                        String nombre = JOptionPane.showInputDialog("Nombre:");
                        String documento = JOptionPane.showInputDialog("Documento:");
                        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
                        float salarioBase = Float.parseFloat(JOptionPane.showInputDialog("Salario base:"));
                        int opcionCategoria = Integer.parseInt(JOptionPane.showInputDialog(
                                "Categoría:\n1. JUNIOR\n2. SEMI_SENIOR\n3. SENIOR"));
                        CategoriaEmpleado categoria;
                        if (opcionCategoria == 1) {
                            categoria = CategoriaEmpleado.JUNIOR;
                        } else if (opcionCategoria == 2) {
                            categoria = CategoriaEmpleado.SEMI_SENIOR;
                        } else if (opcionCategoria == 3) {
                            categoria = CategoriaEmpleado.SENIOR;
                        } else {
                            throw new IllegalArgumentException("Categoría no válida.");
                        }
                        float descuentoSalud = Float.parseFloat(JOptionPane.showInputDialog("Descuento de salud (0 a 100):"));
                        float descuentoPension = Float.parseFloat(JOptionPane.showInputDialog("Descuento de pensión (0 a 100):"));
                        String cargo = JOptionPane.showInputDialog("Cargo:");
                        int horasExtra = Integer.parseInt(JOptionPane.showInputDialog("Horas extra:"));
                        float valorHoraExtra = Float.parseFloat(JOptionPane.showInputDialog("Valor de la hora extra:"));
                        float auxilioTransporte = Float.parseFloat(JOptionPane.showInputDialog("Auxilio de transporte:"));

                        Optional<Empleado> empleado = empresa.registrarEmpleadoPlanta(nombre, documento, edad,
                                salarioBase, categoria, descuentoSalud, descuentoPension, cargo, horasExtra,
                                valorHoraExtra, auxilioTransporte);
                        if (empleado.isPresent()) {
                            JOptionPane.showMessageDialog(null, "Empleado de planta agregado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese documento.");
                        }
                        break;
                    }

                    case 2: {
                        String nombre = JOptionPane.showInputDialog("Nombre:");
                        String documento = JOptionPane.showInputDialog("Documento:");
                        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
                        float salarioBase = Float.parseFloat(JOptionPane.showInputDialog("Salario base:"));
                        int opcionCategoria = Integer.parseInt(JOptionPane.showInputDialog(
                                "Categoría:\n1. JUNIOR\n2. SEMI_SENIOR\n3. SENIOR"));
                        CategoriaEmpleado categoria;
                        if (opcionCategoria == 1) {
                            categoria = CategoriaEmpleado.JUNIOR;
                        } else if (opcionCategoria == 2) {
                            categoria = CategoriaEmpleado.SEMI_SENIOR;
                        } else if (opcionCategoria == 3) {
                            categoria = CategoriaEmpleado.SENIOR;
                        } else {
                            throw new IllegalArgumentException("Categoría no válida.");
                        }
                        float descuentoSalud = Float.parseFloat(JOptionPane.showInputDialog("Descuento de salud (0 a 100):"));
                        float descuentoPension = Float.parseFloat(JOptionPane.showInputDialog("Descuento de pensión (0 a 100):"));
                        float totalVentas = Float.parseFloat(JOptionPane.showInputDialog("Total de ventas:"));
                        float porcentajeComision = Float.parseFloat(JOptionPane.showInputDialog("Porcentaje de comisión (0 a 100):"));

                        Optional<Empleado> empleado = empresa.registrarEmpleadoVentas(nombre, documento, edad,
                                salarioBase, categoria, descuentoSalud, descuentoPension, totalVentas,
                                porcentajeComision);
                        if (empleado.isPresent()) {
                            JOptionPane.showMessageDialog(null, "Empleado de ventas agregado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese documento.");
                        }
                        break;
                    }

                    case 3: {
                        String nombre = JOptionPane.showInputDialog("Nombre:");
                        String documento = JOptionPane.showInputDialog("Documento:");
                        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
                        float salarioBase = Float.parseFloat(JOptionPane.showInputDialog("Salario base:"));
                        int opcionCategoria = Integer.parseInt(JOptionPane.showInputDialog(
                                "Categoría:\n1. JUNIOR\n2. SEMI_SENIOR\n3. SENIOR"));
                        CategoriaEmpleado categoria;
                        if (opcionCategoria == 1) {
                            categoria = CategoriaEmpleado.JUNIOR;
                        } else if (opcionCategoria == 2) {
                            categoria = CategoriaEmpleado.SEMI_SENIOR;
                        } else if (opcionCategoria == 3) {
                            categoria = CategoriaEmpleado.SENIOR;
                        } else {
                            throw new IllegalArgumentException("Categoría no válida.");
                        }
                        float descuentoSalud = Float.parseFloat(JOptionPane.showInputDialog("Descuento de salud (0 a 100):"));
                        float descuentoPension = Float.parseFloat(JOptionPane.showInputDialog("Descuento de pensión (0 a 100):"));
                        int diasTrabajados = Integer.parseInt(JOptionPane.showInputDialog("Días trabajados:"));
                        float valorDia = Float.parseFloat(JOptionPane.showInputDialog("Valor por día:"));

                        Optional<Empleado> empleado = empresa.registrarEmpleadoTemporal(nombre, documento, edad,
                                salarioBase, categoria, descuentoSalud, descuentoPension, diasTrabajados, valorDia);
                        if (empleado.isPresent()) {
                            JOptionPane.showMessageDialog(null, "Empleado temporal agregado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe un empleado con ese documento.");
                        }
                        break;
                    }

                    case 4:
                        JOptionPane.showMessageDialog(null, empresa.mostrarEmpleados());
                        break;

                    case 5: {
                        String documento = JOptionPane.showInputDialog("Documento a buscar:");
                        Optional<Empleado> empleado = empresa.buscarEmpleado(documento);
                        if (empleado.isPresent()) {
                            JOptionPane.showMessageDialog(null, empleado.get().mostrarInformacion());
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró un empleado con ese documento.");
                        }
                        break;
                    }

                    case 6: {
                        Optional<Empleado> empleado = empresa.obtenerEmpleadoMayorSalarioNeto();
                        if (empleado.isPresent()) {
                            JOptionPane.showMessageDialog(null,
                                    "Empleado con mayor salario neto:\n\n" + empleado.get().mostrarInformacion());
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
                        }
                        break;
                    }

                    case 7:
                        JOptionPane.showMessageDialog(null, "Nómina total: " + empresa.calcularNominaTotal());
                        break;

                    case 8: {
                        ArrayList<ResumenPago> resumenes = empresa.obtenerResumenesPago();
                        if (resumenes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
                        } else {
                            String texto = "RESUMEN DE PAGOS\n\n";
                            for (ResumenPago resumen : resumenes) {
                                texto += "Documento: " + resumen.documento()
                                        + "\nNombre: " + resumen.nombre()
                                        + "\nTipo: " + resumen.tipoEmpleado()
                                        + "\nSalario bruto: " + resumen.salarioBruto()
                                        + "\nDescuentos: " + resumen.descuentos()
                                        + "\nSalario neto: " + resumen.salarioNeto()
                                        + "\n--------------------\n";
                            }
                            JOptionPane.showMessageDialog(null, texto);
                        }
                        break;
                    }

                    case 9:
                        JOptionPane.showMessageDialog(null, "¡Hasta pronto!");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: debe ingresar un número válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
            }
        } while (opcion != 9);
    }
}