# Evolución Semana 4 - Mi Formación CTMA

Se ha completado la evolución de la aplicación para la Semana 4, implementando navegación, gestión de estado, validación y persistencia en memoria.

## Cambios Realizados

### 1. Navegación
- Se integró `Navigation Compose`.
- Se definieron 3 destinos:
    - `lista`: Pantalla principal con el listado.
    - `crear`: Formulario para nuevas actividades.
    - `detalle/{actividadId}`: Detalle de una actividad específica.
- Se configuró el `NavHost` en `MainActivity`.

### 2. Gestión de Estado y Validación
- **State Hoisting**: El formulario es stateless (`FormularioActividad`) y recibe su estado de un contenedor (`PantallaCrearActividad`).
- **UI State**: Se creó `FormularioActividadUiState` para manejar de forma inmutable los campos y errores.
- **Validación**:
    - Título: 3-80 caracteres.
    - Descripción: Máximo 240 caracteres.
    - Fecha: Formato YYYY-MM-DD, no anterior a hoy.
    - Progreso: 0-100.
- El botón "Guardar" se deshabilita automáticamente si hay errores.

### 3. Persistencia y Recreación
- Se utiliza `remember { mutableStateListOf(...) }` en `MainActivity` para mantener la lista de actividades durante la sesión.
- Se implementó `rememberSaveable` en el formulario para conservar el borrador ante rotaciones o recreación de la Activity.

### 4. Componentes Visuales
- Se añadió un **Floating Action Button (FAB)** en la lista para acceder a la creación.
- Se actualizó `TarjetaActividad` para navegar al detalle al pulsar sobre ella.
- Se implementó una pantalla de detalle que muestra toda la información y permite regresar.

## Verificación Realizada

### Pruebas de Compilación
- El proyecto compila correctamente con `app:assembleDebug`.

### Pruebas Manuales Recomendadas
1. **Validación de Título**: Intentar guardar con menos de 3 caracteres o vacío. El botón debe estar deshabilitado y mostrar error.
2. **Validación de Fecha**: Introducir una fecha pasada (ej. 2020-01-01). Debe mostrar error.
3. **Borrador**: Escribir algo en el formulario de creación, rotar la pantalla y verificar que el texto permanece.
4. **Doble Pulsación**: Pulsar rápido el botón Guardar. La lógica de `guardando` impide duplicados.
5. **Navegación**:
    - Lista -> Crear -> Guardar -> Lista (verificar que la nueva aparece).
    - Lista -> Detalle -> Atrás -> Lista.
    - Intentar ir a un ID inexistente (ej. modificando el código o flujo manual) y verificar el estado controlado.

## Archivos Creados/Modificados

### Nuevos
- [FormularioActividadUiState.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/FormularioActividadUiState.kt)
- [FormularioActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/components/FormularioActividad.kt)
- [PantallaCrearActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/PantallaCrearActividad.kt)
- [PantallaDetalleActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/PantallaDetalleActividad.kt)

### Modificados
- [ActividadFormativa.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/domain/ActividadFormativa.kt)
- [ReglasActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/domain/ReglasActividad.kt)
- [PantallaActividades.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/PantallaActividades.kt)
- [MainActivity.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/MainActivity.kt)
- [build.gradle.kts](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/build.gradle.kts)
- [libs.versions.toml](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/gradle/libs.versions.toml)
