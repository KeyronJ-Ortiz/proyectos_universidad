# Librerías necesarias para visión por computadora, detección 3D y cálculo matemático
import cv2                      # OpenCV para capturar y mostrar video
import mediapipe as mp          # Mediapipe para usar el modelo Objectron
import numpy as np              # NumPy para operaciones de vectores y distancias

# Alias para módulos específicos de Mediapipe
mp_drawing = mp.solutions.drawing_utils
mp_objectron = mp.solutions.objectron

# Abrir la cámara (índice 0 = cámara predeterminada)
cap = cv2.VideoCapture(0)

# Iniciar el modelo de Objectron configurado para detectar hasta 2 zapatos
with mp_objectron.Objectron(
    static_image_mode=False,            # Para detección en video (no imágenes fijas)
    max_num_objects=2,                  # Detectar máximo dos objetos
    min_detection_confidence=0.5,       # Confianza mínima aceptada
    model_name='Shoe') as objectron:    # Categoría específica: zapatos

    # Estilo personalizado para las cajas dibujadas (rojo)
    custom_style = mp_drawing.DrawingSpec(color=(0, 0, 255), thickness=2, circle_radius=2)

    # Bucle principal: se ejecuta mientras la cámara esté activa
    while cap.isOpened():
        ret, frame = cap.read()  # Leer un frame de la cámara
        if not ret:
            print("No se pudo leer de la cámara.")
            break

        # Voltear el frame horizontalmente (espejo) y convertir a RGB
        frame = cv2.flip(frame, 1)
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

        # Enviar el frame al modelo para procesamiento
        results = objectron.process(rgb)

        # Si se detectan objetos (zapatos)
        if results.detected_objects:
            for idx, obj in enumerate(results.detected_objects):
                # Dibujar caja 3D (landmarks y conexiones)
                mp_drawing.draw_landmarks(
                    frame,
                    obj.landmarks_2d,
                    mp_objectron.BOX_CONNECTIONS,
                    landmark_drawing_spec=custom_style,
                    connection_drawing_spec=custom_style
                )

                # Dibujar los ejes 3D (X, Y, Z) para representar la orientación del objeto
                mp_drawing.draw_axis(
                    frame,
                    obj.rotation,
                    obj.translation
                )

                # Posición vertical base para los textos, depende del zapato
                base_y = 30 + idx * 50

                # Etiquetar el objeto en pantalla (ej. Zapato 1, Zapato 2)
                cv2.putText(frame, f'Zapato {idx+1}',
                            (30, base_y),
                            cv2.FONT_HERSHEY_DUPLEX, 0.6,
                            (0, 255, 0), 2)

                # Extraer el vector Z (columna 3 de la matriz de rotación) que indica hacia dónde apunta el zapato
                z_vector = [
                    obj.rotation[0][2],  # Componente X del vector
                    obj.rotation[1][2],  # Componente Y del vector
                    obj.rotation[2][2]   # Componente Z del vector
                ]

                # Determinar si apunta a la derecha, izquierda o al frente (según el eje X del vector Z)
                if z_vector[0] > 0.4:
                    sentido_h = "derecha"
                elif z_vector[0] < -0.4:
                    sentido_h = "izquierda"
                else:
                    sentido_h = "frente"

                # Determinar si apunta hacia adelante o hacia atrás (según el eje Z del vector Z)
                if z_vector[2] > 0.5:
                    sentido_v = "(hacia atras)"
                elif z_vector[2] < -0.5:
                    sentido_v = "(hacia adelante)"
                else:
                    sentido_v = ""

                # Texto final con la dirección completa del zapato
                direccion_completa = f"Direccion: {sentido_h} {sentido_v}"

                # Mostrar la dirección en pantalla debajo del número de zapato
                cv2.putText(frame, direccion_completa,
                            (30, base_y + 20),
                            cv2.FONT_HERSHEY_SIMPLEX, 0.6,
                            (255, 255, 255), 2)

            # Si hay al menos dos objetos detectados, comparar entre ellos
            if len(results.detected_objects) >= 2:
                obj1 = results.detected_objects[0]
                obj2 = results.detected_objects[1]

                # Convertir posiciones a arrays NumPy
                pos1 = np.array(obj1.translation)
                pos2 = np.array(obj2.translation)

                # Calcular distancia euclidiana 3D entre las posiciones
                distancia = np.linalg.norm(pos1 - pos2)

                # Extraer vectores de orientación (vector Z) para ambos zapatos
                dir1 = [obj1.rotation[0][2], obj1.rotation[1][2], obj1.rotation[2][2]]
                dir2 = [obj2.rotation[0][2], obj2.rotation[1][2], obj2.rotation[2][2]]

                # Calcular el ángulo entre los vectores (producto punto)
                dot = np.dot(dir1, dir2)
                norm1 = np.linalg.norm(dir1)
                norm2 = np.linalg.norm(dir2)
                angle_rad = np.arccos(dot / (norm1 * norm2 + 1e-6))  # Agregado 1e-6 para evitar divisiones por 0
                angle_deg = np.degrees(angle_rad)  # Convertir a grados

                # Preparar textos para mostrar
                texto_distancia = f"Distancia 3D: {distancia:.2f} m"
                texto_orientacion = f"Dif. orientacion: {angle_deg:.1f}°"

                # Mostrar los textos con sombra para mejor visibilidad
                base_y = 140
                for i, (txt, y_offset) in enumerate([(texto_distancia, 0), (texto_orientacion, 20)]):
                    y = base_y + y_offset
                    cv2.putText(frame, txt, (30, y), cv2.FONT_HERSHEY_COMPLEX, 0.6, (0, 0, 0), 3)     # sombra
                    cv2.putText(frame, txt, (30, y), cv2.FONT_HERSHEY_COMPLEX, 0.6, (255, 255, 0), 1)  # texto

        # Mostrar la ventana con el video en vivo y todos los dibujos
        cv2.imshow('Zapatillas - Objectron', frame)

        # Si se presiona ESC (tecla 27), se sale del bucle
        if cv2.waitKey(5) & 0xFF == 27:
            break

# Cerrar la cámara y destruir la ventana al finalizar
cap.release()
cv2.destroyAllWindows()
