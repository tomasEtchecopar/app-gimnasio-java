import Clases.Gimnasio.Ejercicio;
import Clases.Gimnasio.Rutina;
import Clases.Gimnasio.Serie;

public class Main {
    public static void main(String[] args) {
        Ejercicio pressPlanoConBarra  = new Ejercicio("Press plano con barra", """
    El press de banca inclinado con barra es un ejercicio enfocado en la parte superior del pecho y los hombros anteriores.

    Técnica correcta:
    1. Posición inicial:
       - Ajusta el banco a unos 30–45° de inclinación.
       - Recuéstate con la espalda apoyada y los pies firmes en el suelo.
       - Agarra la barra con un agarre ligeramente más ancho que el de los hombros.

    2. Ejecución:
       - Desbloquea la barra y bájala controladamente hasta la parte superior del pecho (justo por encima de los pezones).
       - Mantén los codos en un ángulo de ~45° respecto al torso.
       - Empuja la barra hacia arriba hasta extender casi por completo los brazos, exhalando al subir.

    3. Consejos clave:
       - Mantén los omóplatos retraídos para estabilizar los hombros.
       - Evita arquear excesivamente la zona lumbar.
       - Controla la fase descendente para maximizar la activación muscular.

    Este ejercicio potencia el desarrollo del pectoral superior y mejora la estabilidad del hombro.
    """);

        Ejercicio pressInclinadoConBarra  = new Ejercicio("Press Inclinado con barra", """
                El press de banca inclinado con barra es un ejercicio orientado principalmente al desarrollo de la porción clavicular del pectoral mayor, así como a los deltoides anteriores y tríceps.
                
                Técnica correcta
                
                Ajuste y posición inicial
                
                Coloca el banco a unos 30–45° de inclinación.
                
                Túmbate con la espalda completamente apoyada, hombros retraídos y pies firmes en el suelo.
                
                Agarra la barra con un agarre ligeramente más ancho que el de los hombros, muñecas rectas.
                
                Fase excéntrica (descenso)
                
                Desbloquea la barra y bájala de forma controlada hasta que toque suavemente la parte superior del pecho (justo por encima de los pezones).
                
                Mantén los codos aproximadamente a 45° respecto al torso para proteger la articulación del hombro.
                
                Inspira mientras desciendes, sintiendo el estiramiento en la zona alta del pecho.
                
                Fase concéntrica (empuje)
                
                Empuja la barra hacia arriba hasta casi estirar completamente los brazos, pero sin bloquear los codos.
                
                Exhala de manera explosiva al iniciar el empuje.
                
                Mantén la espalda baja con una ligera curva natural y los omóplatos pegados al banco.
                
                Consejos clave
                
                Evita arquear en exceso la zona lumbar.
                
                No reviertas el movimiento con rebotes; controla la bajada.
                
                Ajusta la inclinación según tu comodidad: ángulos más altos reclutan más hombro, ángulos más bajos más pecho.""");

        Rutina dia1 = new Rutina("Dia de pecho");
        dia1.agregarSerie(new Serie(pressPlanoConBarra, 7, 100));
        dia1.agregarSerie(new Serie(pressPlanoConBarra, 6, 100));
        dia1.agregarSerie(new Serie(pressPlanoConBarra, 8, 90));
        dia1.agregarSerie(new Serie(pressInclinadoConBarra, 10, 80));
        dia1.mostrarRutina();
    }
}
