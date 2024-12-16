# Todo

## Final Objetos

- Repasar TP Integrador
- Repasar patrones de diseño
- Pensar en papel el diagrama de clases usando al menos 3 patrones
- Pensar en los casos de uso
    - Agregar carreras (administadores) CREATE - CARRERA
    - Definir el plan de estudios para cada carreara (administradores) EN CREATE O UPDATE CARRERA
    - Inscripcion de alumnos a una carrera (administrador, profesor).
    - Inscripcion de alumnos a una amteria solo si la pueden cursar, tienen que tener las correlativas aprobadas. (Alumnos)
    - Informar al alumno cuales son las materias que esta en condifciones de cursar (alumnos).
    - verificar si el alumno finalizo la carrera, si tiene todas las materias aprobadas (todos- Una pantalla en el perfil del alumno y un modulo a parte para que los administadores y profes vean que alumnos finalizaron la carreara).
- Diseñar las pantallas y controladores para los casos de uso

Definiciones:

- Cada carrera incluye materias obligatorias y materias optativas. Para graduarse un alumno debe
  aprobar todas las materias obligatorias de la carrera y aprobar una cierta cantidad de materias
  optativas (no la totalidad de las materias optativas). La cantidad de materias optativas depende de
  la carrera.
- Para aprobar una materia se debe rendir primero un parcial y luego un final. Adicionalmente existe
  un régimen de promoción para algunas materias para aquellos alumnos que aprobaran la cursada
  (parcial).
- Tenga en cuenta que las condiciones para que un alumno pueda cursar una materia varían de acuerdo al **plan de estudios de la carrera**:
    - Plan A: aprobó las cursadas de las correlativas
    - Plan B: aprobó los finales de las correlativas
    - Plan C: aprobó las cursadas de las correlativas y los finales de todas las materias de 5 cuatrimestres previos al que se quiere anotar
    - Plan D: aprobó las cursadas de las correlativas y los finales de todas las materias de 3 cuatrimestres previos al que se quiere anotar
    - Plan E: aprobó los finales de las correlativas y los finales de todas las materias de 3 cuatrimestres previos.
- Interface visual (swing) para los siguientes puntos:
    - Alta de alumnos.
    - Alta de carreras y Planes de Estudio.
    - Inscripción de un alumno a una carrera.
    - Inscripción de un alumno a una materia.
    - Verificar si un alumno terminó la carrera.

Buscando Clases:

- Planes de estudio
- Alumnos
- Carrera
- Materia
- Correlativas
- Parcial
- Final
- Cuatrimestre

Tener preparado para la presentación:

- Un alumno que haya finalizado la carrera y se pueda ver que finalizo la carrera.
- Un alumno que este cursando y solo pueda inscribirse a las materias si tiene las correlativas aprobadas. Tener una materia con la correlativa aprobada y otra con la correlativa desaprobada.

Casos de usos funcionando:

- Iniciar sesión
- Cerrar sesión