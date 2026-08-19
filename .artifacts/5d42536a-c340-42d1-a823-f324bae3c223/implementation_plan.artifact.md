# Plan de Implementación Semana 4 - Mi Formación CTMA

Este plan detalla la evolución de la aplicación desde la Semana 3 a la Semana 4, incluyendo gestión de estado, validación, navegación y persistencia en memoria.

## User Review Required

> [!IMPORTANT]
> Se añadirá la dependencia de Navigation Compose.
> El modelo `ActividadFormativa` se actualizará para incluir un campo de fecha si es necesario para la validación, o se manejará la conversión entre fecha y días restantes.

## Proposed Changes

### Dependencias

#### [MODIFY] [libs.versions.toml](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/gradle/libs.versions.toml)
- Añadir `navigation-compose`.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/build.gradle.kts)
- Añadir la implementación de `libs.androidx.navigation.compose`.

---

### Dominio y Reglas

#### [MODIFY] [ActividadFormativa.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/domain/ActividadFormativa.kt)
- (Opcional) Añadir campo `fecha`.

#### [MODIFY] [ReglasActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/domain/ReglasActividad.kt)
- Implementar las nuevas reglas de validación (Título 3-80, Descripción max 240, Fecha no anterior a hoy, Progreso 0-100).

---

### UI y Estado

#### [NEW] [FormularioActividadUiState.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/FormularioActividadUiState.kt)
- Definir la estructura inmutable para el estado del formulario.

#### [NEW] [FormularioActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/components/FormularioActividad.kt)
- Composable stateless para el formulario.

#### [NEW] [PantallaCrearActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/PantallaCrearActividad.kt)
- Pantalla que contiene el formulario y maneja el `rememberSaveable`.

#### [NEW] [PantallaDetalleActividad.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/PantallaDetalleActividad.kt)
- Pantalla de detalle que recibe un ID.

#### [MODIFY] [PantallaActividades.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/ui/screens/PantallaActividades.kt)
- Añadir FAB para navegar a "Crear".
- Actualizar el callback de clic en tarjeta.

---

### Navegación y MainActivity

#### [MODIFY] [MainActivity.kt](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/app/src/main/java/com/example/miformacionctma/MainActivity.kt)
- Implementar `NavHost` con las 3 rutas: `lista`, `crear`, `detalle/{actividadId}`.
- Manejar la lista de actividades en un estado persistente durante la sesión (en memoria).

---

### Documentación

#### [MODIFY] [README.md](file:///C:/Users/santi/StudioProjects/MiFormacionCTMA/README.md)
- Actualizar con los detalles de la Semana 4.

## Verification Plan

### Automated Tests
- Adaptar o crear pruebas en `ReglasActividadTest` (o similar) para las nuevas validaciones.
- Verificar el estado del formulario en pruebas unitarias si es posible.

### Manual Verification
1. Abrir la app y ver la lista.
2. Pulsar FAB para ir a Crear.
3. Verificar validaciones en tiempo real (Título vacío, corto, largo, etc.).
4. Verificar que el botón Guardar se habilita solo cuando es válido.
5. Rotar la pantalla para verificar que `rememberSaveable` mantiene el borrador.
6. Guardar y verificar que vuelve a la lista y la actividad aparece.
7. Pulsar una actividad para ir al detalle.
8. Verificar que el detalle muestra los datos correctos.
9. Regresar atrás y verificar la lista.
10. Intentar navegar a un ID inexistente manualmente (si es posible) o verificar el manejo de error.
11. Pulsar Guardar varias veces rápidamente.
