// src/App.jsx
import { useState, useEffect, useRef } from 'react'
import './App.css'

function App() {
  const [mensajes, setMensajes] = useState([])
  const [input, setInput] = useState('')
  const chatRef = useRef(null)

  const enviarMensaje = async () => {
    if (!input.trim()) return

    const nuevoMensaje = { texto: input, de: 'user' }
    setMensajes((prev) => [nuevoMensaje, ...prev])
    const pregunta = input
    setInput('')

    try {
      const respuesta = await fetch('http://127.0.0.1:8000/preguntar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ pregunta }),
      })

      const data = await respuesta.json()

      setMensajes((prev) => [
        { texto: data.respuesta, de: 'bot' },
        ...prev,
      ])
    } catch (error) {
      setMensajes((prev) => [
        { texto: 'Hubo un error al contactar a IA-rthur Morgan.', de: 'bot' },
        ...prev,
      ])
    }
  }

  useEffect(() => {
    if (chatRef.current) {
      chatRef.current.scrollTop = chatRef.current.scrollHeight
    }
  }, [mensajes])

  return (
    <div className="layout">
      <header className="header">
        <img src="/logo.png" alt="Logo IA-rthur" className="logo" />
        <h1 className="titulo">IArthur Morgan</h1>
      </header>

      <main className="main-chat">
        <div ref={chatRef} className="chat-container">
          {mensajes.map((msg, index) => (
            <div key={index} className={`message ${msg.de}`}>
              {msg.texto}
            </div>
          ))}
        </div>
      </main>

      <footer className="input-container">
        <input
          type="text"
          placeholder="Escribe tu pregunta..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={(e) => e.key === 'Enter' && enviarMensaje()}
        />
        <button onClick={enviarMensaje}>ENVIAR</button>
      </footer>
    </div>
  )
}

export default App
