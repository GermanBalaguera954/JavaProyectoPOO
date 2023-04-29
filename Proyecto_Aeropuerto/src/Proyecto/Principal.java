/*
Diseñar y modificar una aplicacion informatica para una compañia de gestion aeroportuaria
satisfaciendo los siguientes requisitos:

*Para el aeropuerto es necesario saber:

a) Todas las compañias de vuelo operan en él.
b) Nombre del aeropuerto, la ciudad donde se ubica y el pais al que pertenece.

*Cada compañia se caracteriza por el nombre y las liste de vuelos que ofrece.

*Los vuelos estan definidos por su identificador, la ciudad de origen, la ciudad de destino,
el precio del viaje, la lista de pasajeros, el numero maximo de pasajeros permitidos en el
vuelo  y el numero real de pasajeros que han reservado asiento en el avión.

*Los aeropuertos pueden ser privados o publicos.

a)Los aeropuertos privados tienen una serie de empresas que los patrocinan y es necesario
saber el nombre de cada una de las empresas.
b)Para los aeropuertos publicos se requiere saber la cantidad de dinero correspondiente a
la subvencion gubernamental.

*Se necesita gestionar tambien la informacion de los pasajeros.

a) Para cada pasajero se necesita saber nombre, numero de pasajero y nacionalidad.

La aplicacion tendra un menu con las siguientes opciones:

1)  Consultar los aeropuertos gestionados, indicando separadamente los aeropuertos publico
    y los privados. Para cada uno de ellos debe mostar su nombre, la ciudad de ubicacion y
    el pais al que pertenece.

2)  Visualizar la empresas que patrocinan un determinado aeropuerto en caso que sea privado
    o la cuantia de subvencion en caso que se trate de un aeropuerto publico.

3)  Para un determinado aeropuerto, se debe poder mostrar la lista de compañias que vuelan
    desde ese aeropuerto.

4)  Para una determinada compañia que opera en un aeropuerto en concreto, listar todos los
    posibles vuelos que dicha compañia ofrece, mostrando su identificador, la ciudad de
    origen, el destino y el precio de vuelo.

5)  Mostrar todos los posibles vuelos (identificador) que parten de una ciudad de origen
    a otra ciudad de destino (indicadas por el usuario) y mostrar su precio.
 */
package Proyecto;

import java.util.Scanner;

public class Principal {

    static Scanner entrada = new Scanner(System.in);
    final static int num = 4; //Numero de aeropuertos.
    static Aeropuerto aeropuertos[] = new Aeropuerto[num];

    public static void main(String[] args) {
        //Insertar datos de los aeropuertos. 
        insertarDatosAeropuertos(aeropuertos);
        menu();

    }

    public static void insertarDatosAeropuertos(Aeropuerto aero[]) {
        aero[0] = new AeropuertoPublico(80000000, "Jorge Chavez", "Lima", "Peru");
        aero[0].insertarCompañia(new Compañia("AeroPeru"));
        aero[0].insertarCompañia(new Compañia("LATAM"));
        aero[0].getCompañia("AeroPeru").insertarVuelo(new Vuelo("IB20", "Lima", "Mexico", 150.90, 150));
        aero[0].getCompañia("AeroPeru").insertarVuelo(new Vuelo("IB21", "Lima", "Buenos aires", 180.99, 120));
        aero[0].getCompañia("LATAM").insertarVuelo(new Vuelo("FC12", "Lima", "Londres", 500.90, 180));
        aero[0].getCompañia("AeroPeru").getVuelo("IB20").insertarPasajero(new Pasajero("German", "20BGHP", "Colombiano"));
        aero[0].getCompañia("AeroPeru").getVuelo("IB20").insertarPasajero(new Pasajero("Alejandro", "PJKL20", "Peruano"));
        aero[0].getCompañia("LATAM").getVuelo("FC12").insertarPasajero(new Pasajero("Raul", "JH21KL", "Mexicano"));

        aero[1] = new AeropuertoPrivado("Central Ciudad Real", "Ciudad Real", "España");
        aero[1].insertarCompañia(new Compañia("AirEuropa"));
        String empresas[] = {"Cobresol", "Anguila34"};
        ((AeropuertoPrivado) aero[1]).insertarEmpresas(empresas);
        aero[1].getCompañia("AirEuropa").insertarVuelo(new Vuelo("AE025", "Madrid", "Buenos aires", 180.90, 130));
        aero[1].getCompañia("AirEuropa").getVuelo("AE025").insertarPasajero(new Pasajero("Jose", "MKL21", "Panameño"));

        aero[2] = new AeropuertoPublico(20000000, "Aeropuerto Bogota", "Bogota", "Colombia");
        aero[2].insertarCompañia(new Compañia("AirAmerica"));
        aero[2].insertarCompañia(new Compañia("VuelaBogota"));
        aero[2].getCompañia("AirAmerica").insertarVuelo(new Vuelo("AE30", "Bogota", "Lima", 130.90, 100));
        aero[2].getCompañia("AirAmerica").insertarVuelo(new Vuelo("AE31", "Bogota", "Lima", 100.08, 110));
        aero[2].getCompañia("AirAmerica").getVuelo("AE30").insertarPasajero(new Pasajero("Diego", "12HJK", "Colombiano"));
        aero[2].getCompañia("AirAmerica").getVuelo("AE30").insertarPasajero(new Pasajero("Danilo", "123PL", "Argentino"));

        aero[3] = new AeropuertoPublico(40000000, "Aeropuerto Mexico", "Mexico", "Mexico");
        aero[3].insertarCompañia(new Compañia("AeroMexico"));
        aero[3].getCompañia("AeroMexico").insertarVuelo(new Vuelo("IB2040", "Mexico", "Nueva York", 200.20, 180));
        aero[3].getCompañia("AeroMexico").insertarVuelo(new Vuelo("IB2042", "Mexico", "Lima", 100.70, 150));
        aero[3].getCompañia("AeroMexico").getVuelo("IB2040").insertarPasajero(new Pasajero("Camilo", "MNP56", "Jamaica"));
    }

    public static void menu() {
        String nombreAeropuerto, nombreCompañia, origen, destino;
        int opcion;
        Aeropuerto aeropuerto;
        Compañia compañia;

        do {
            System.out.println("\t MENU");
            System.out.println("1. Ver aeropuertos gestionados (Publicos o Privados)");
            System.out.println("2. Ver empresas (Privado)o subvencion (Publicos)");
            System.out.println("3. Lista de compañias de un aeropuerto");
            System.out.println("4. Lista de vuelos por compañia");
            System.out.println("5. Listar posibles vuelos de origen a destino");
            System.out.println("6. Salir)");
            System.out.print("Opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1://Ver aeropuertos gestionados (Publicos o Privados
                    System.out.println("");
                    mostrarDatosAeropuertos(aeropuertos);
                    break;

                case 2://Ver empresas (Privado)o subvencion (Publicos)
                    System.out.println("");
                    mostrarPatrocinio(aeropuertos);
                    break;

                case 3://Lista de compañias de un aeropuerto
                    entrada.nextLine();
                    System.out.print("\nDigite el nombre del aeropuerto: ");
                    nombreAeropuerto = entrada.nextLine();
                    aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                    if (aeropuerto == null) {
                        System.out.println("El Aeropuerto no existe");
                    } else {
                        mostrarCompañias(aeropuerto);
                    }
                    break;

                case 4://Lista de vuelos por compañia
                    entrada.nextLine();
                    System.out.print("\nDigite el nombre del aeropuerto: ");
                    nombreAeropuerto = entrada.nextLine();
                    aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                    if (aeropuerto == null) {
                        System.out.println("El Aeropuerto no existe");
                    } else {
                        System.out.print("\nDigite el nombre de la compañia: ");
                        nombreCompañia = entrada.nextLine();
                        compañia = aeropuerto.getCompañia(nombreCompañia);
                        mostrarVuelos(compañia);
                    }
                    break;

                case 5://Listar posibles vuelos de origen a destino
                    entrada.nextLine();
                    System.out.print("\nDigite la ciudad origen: ");
                    origen = entrada.nextLine();
                    System.out.print("\nDigite la ciudad destino: ");
                    destino = entrada.nextLine();
                    mostrarVueloOrigenDestino(origen, destino, aeropuertos);
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Error, se equivoco de opcion de menu");
            }
            System.out.println("");
        } while (opcion != 6);
    }

    //1. metodo menu ver aeropuertos gestionados (Publicos o Privados
    public static void mostrarDatosAeropuertos(Aeropuerto aeropuertos[]) {
        for (int i = 0; i < aeropuertos.length; i++) {
            if (aeropuertos[i] instanceof AeropuertoPrivado) {
                System.out.println("Aeropuerto Privado");
                System.out.println("Nombre: " + aeropuertos[i].getNombre());
                System.out.println("Ciudad: " + aeropuertos[i].getCiudad());
                System.out.println("Pais: " + aeropuertos[i].getPais());
            } else {
                System.out.println("Aeropuerto Publico");
                System.out.println("Nombre: " + aeropuertos[i].getNombre());
                System.out.println("Ciudad: " + aeropuertos[i].getCiudad());
                System.out.println("Pais: " + aeropuertos[i].getPais());
            }
            System.out.println("");
        }
    }

    //2. metodo menu ver empresas (Privado)o subvencion (Publicos)
    public static void mostrarPatrocinio(Aeropuerto aeropuertos[]) {
        String empresas[];
        for (int i = 0; i < aeropuertos.length; i++) {
            if (aeropuertos[i] instanceof AeropuertoPrivado) {
                System.out.println("Aeropuerto Privado: " + aeropuertos[i].getNombre());
                empresas = ((AeropuertoPrivado) aeropuertos[i]).getListaEmpresas();
                System.out.println("Empresas: ");
                for (int j = 0; j < empresas.length; j++) {
                    System.out.println(empresas[j]);
                }
            } else {
                System.out.println("Aeropuerto Publico: " + aeropuertos[i].getNombre());
                System.out.println("Subvencion: " + ((AeropuertoPublico) aeropuertos[i]).getSubvencion());
            }
            System.out.println("");
        }
    }

    //3. metodo menu lista de compañias de un aeropuerto
    public static Aeropuerto buscarAeropuerto(String nombre, Aeropuerto aeropuertos[]) {
        boolean encontrado = false;
        int i = 0;
        Aeropuerto aero = null;
        while ((!encontrado) && (i < aeropuertos.length)) {
            if (nombre.equals(aeropuertos[i].getNombre())) {
                encontrado = true;
                aero = aeropuertos[i];
            }
            i++;
        }
        return aero;
    }

    public static void mostrarCompañias(Aeropuerto aeropuerto) {
        for (int i = 0; i < aeropuerto.getNumCompañia(); i++) {
            System.out.println(aeropuerto.getCompañia(i).getNombre());
        }
    }

    public static void mostrarVuelos(Compañia compañia) {
        Vuelo vuelo;
        System.out.println("Los vuelos de la compañia: " + compañia.getNombre());
        for (int i = 0; i < compañia.getNumVuelo(); i++) {
            vuelo = compañia.getVuelo(i);
            System.out.println("Identificador: " + vuelo.getIdentificador());
            System.out.println("Ciudad de Origen: " + vuelo.getCiudadOrigen());
            System.out.println("Ciudad de destino: " + vuelo.getCiudadDestino());
            System.out.println("Precio: $" + vuelo.getPrecio());
            System.out.println("");
        }
    }

    public static Vuelo[] buscarVuelosOrigenDestino(String origen, String destino, Aeropuerto aeropuertos[]) {
        Vuelo vuelo;
        int contador = 0;
        Vuelo listaVuelos[];

        for (int i = 0; i < aeropuertos.length; i++) {//Recorremos los aeropuertos
            for (int j = 0; j < aeropuertos[i].getNumCompañia(); j++) {//recorremos las compañias
                for (int k = 0; k < aeropuertos[i].getCompañia(j).getNumVuelo(); k++) {//recorremos los veuelos
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen())) && (destino.equals(vuelo.getCiudadDestino()))) {
                        contador++;
                    }
                }
            }
        }
        listaVuelos = new Vuelo[contador];
        int q = 0;

        for (int i = 0; i < aeropuertos.length; i++) {
            for (int j = 0; j < aeropuertos[i].getNumCompañia(); j++) {
                for (int k = 0; k < aeropuertos[i].getCompañia(j).getNumVuelo(); k++) {
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen())) && (destino.equals(vuelo.getCiudadDestino()))) {
                        listaVuelos[q] = vuelo;
                        q++;
                    }
                }
            }

        }
        return listaVuelos;
    }

    public static void mostrarVueloOrigenDestino(String origen, String destino, Aeropuerto aeropuertos[]) {
        Vuelo vuelos[];
        vuelos = buscarVuelosOrigenDestino(origen, destino, aeropuertos);
        if (vuelos.length == 0) {
            System.out.println("No existe vuelos de esa ciudad origen a destino");
        } else {
            System.out.println("\nVuelos encontados: ");
            for (int i = 0; i < vuelos.length; i++) {
                System.out.println("Identificador: " + vuelos[i].getIdentificador());
                System.out.println("Ciudad de Origen: " + vuelos[i].getCiudadOrigen());
                System.out.println("Ciudad de destino: " + vuelos[i].getCiudadDestino());
                System.out.println("Precio vuelo: $" + vuelos[i].getPrecio());
                System.out.println("");
            }
        }
    }
}
