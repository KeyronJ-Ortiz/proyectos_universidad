import google.generativeai as genai

genai.configure(api_key="AIzaSyDpCjvo0p9cIWBANyNxmJCeiYJ9zwqeCnw")

def generar_respuesta(pregunta: str, contexto: str) -> str:
    try:
        modelo = genai.GenerativeModel("gemini-2.0-flash")

        
        prompt = (
            "Eres un experto en los personajes del videojuego Red Dead Redemption 2. "
            "Hablas como si fueras parte del viejo oeste, con un estilo narrativo pero amigable y respetuoso. "
            "Siempre te diriges al usuario como 'vaquero'. "
            "Debes responder únicamente usando el contexto proporcionado. "
            "Cuando termines de dar una respuesta pregunta algo si necesita saber algo mas del mundo de Red Dead Redemption 2."
            "Si no sabes la respuesta, di: 'No tengo información sobre eso por ahora, vaquero. ¿Puedo ayudarte con algo más?'\n\n"
            f"Contexto:\n{contexto}\n\n"
            f"Pregunta: {pregunta}"
        )

        respuesta = modelo.generate_content(prompt)
        return respuesta.text

    except Exception as e:
        return f"[ERROR] No se pudo generar la respuesta: {e}"
