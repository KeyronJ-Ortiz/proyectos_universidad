from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from backend.chroma_query import obtener_contexto
from backend.gemini import generar_respuesta
from fastapi.middleware.cors import CORSMiddleware
#from src.gemini import generar_respuesta  # Asumimos que ya tienes est

app = FastAPI()
# Permitir todas las conexiones desde el frontend
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # O reemplaza "*" por "http://localhost:5173" si usas Vite
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class Pregunta(BaseModel):
    pregunta: str

@app.post("/preguntar")
def preguntar(p: Pregunta):
    try:
        print("[INFO] Obteniendo contexto desde ChromaDB...")
        contexto = obtener_contexto(p.pregunta)

        if not contexto:
            return {"respuesta": "No encontré suficiente información, vaquero. ¿Puedo ayudarte con algo más?"}

        print("[INFO] Enviando pregunta a Gemini...")
        respuesta = generar_respuesta(p.pregunta, contexto)
        return {"respuesta": respuesta}

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Ocurrió un error: {e}")
