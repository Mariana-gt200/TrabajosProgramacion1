package co.edu.uniquindio.poo.model;
import java.util.ArrayList;

public class Huespedes {
    private String documento;
    private String nombre;
    private byte edad;
    private String telefono;
    private String ciudadProsedencia;

    private Hotel ownedByHotel;

    //Constructor
    public Huespedes(String documento, String nombre, byte edad, String telefono, String ciudadProsedencia) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudadProsedencia = ciudadProsedencia;
        this.ownedByHotel = ownedByHotel;
    }

    //Get y Set

    public String getDocumento() { return documento;
    }
    public void setDocumento(String documento) { this.documento = documento;
    }

    public String getNombre() { return nombre;
    }
    public void setNombre(String nombre) { this.nombre = nombre;
    }

    public byte getEdad() { return edad;
    }
    public void setEdad(byte edad) { this.edad = edad;
    }

    public String getTelefono() { return telefono;
    }
    public void setTelefono(String telefono) { this.telefono = telefono;
    }

    public String getCiudadProsedencia() { return ciudadProsedencia;
    }
    public void setCiudadProsedencia(String ciudadProsedencia) { this.ciudadProsedencia = ciudadProsedencia;
    }

    public Hotel getOwnedByHotel() { return ownedByHotel;
    }
    public void setOwnedByHotel(Hotel ownedByHotel) { this.ownedByHotel = ownedByHotel;
    }

    //toString
    @Override
    public String toString() {
        return "Huespedes{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", ciudadProsedencia='" + ciudadProsedencia + '\'' +
                ", ownedByHotel=" + ownedByHotel +
                '}';
    }

}
