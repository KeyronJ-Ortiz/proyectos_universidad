import os
import chromadb
from sentence_transformers import SentenceTransformer
from chromadb.config import Settings


# Inicializar el modelo de embeddings
modelo = SentenceTransformer("all-MiniLM-L6-v2")

# Inicializar el cliente de ChromaDB en memoria
chroma = chromadb.PersistentClient(path="db")
# Eliminar colección previa (si existe)
try:
    chroma.delete_collection("personajes_rdr2")
except:
    pass
coleccion = chroma.create_collection(name="personajes_rdr2")

# Carpeta con los archivos .txt
carpeta = "personajes"

# Leer cada archivo, generar embeddings y guardarlos
for archivo in os.listdir(carpeta):
    if archivo.endswith(".txt"):
        ruta = os.path.join(carpeta, archivo)
        with open(ruta, "r", encoding="utf-8") as f:
            contenido = f.read()
            vector = modelo.encode(contenido).tolist()
            id_unico = archivo.replace(".txt", "")
            coleccion.add(
                documents=[contenido],
                embeddings=[vector],
                ids=[id_unico]
            )
            print(f"✅ Procesado: {archivo}")

print("Embeddings generados y almacenados en ChromaDB")
