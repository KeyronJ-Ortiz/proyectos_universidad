#backend/chroma_query.py
import chromadb
from chromadb.config import Settings
from chromadb.utils.embedding_functions import SentenceTransformerEmbeddingFunction

chroma = chromadb.PersistentClient(path="db")
embedding_fn = SentenceTransformerEmbeddingFunction(model_name="all-MiniLM-L6-v2")
coleccion = chroma.get_collection(name="personajes_rdr2")

def buscar_documento(query: str):
    resultados = coleccion.query(query_texts=[query], n_results=3)
    if not resultados["documents"] or not resultados["documents"][0]:
        return "No encontré información relacionada con tu pregunta."
    return resultados["documents"][0][0]

def obtener_contexto(pregunta: str) -> str:
    print(f"[INFO] Buscando contexto relevante para la pregunta: {pregunta}")
    resultados = coleccion.query(query_texts=[pregunta], n_results=3)
    documentos = resultados["documents"][0]
    return "\n".join(documentos)

