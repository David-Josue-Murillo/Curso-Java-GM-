import React from 'react'
import ReactDOM from 'react-dom/client'
import { Contact } from './Contact'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Nav } from './components/Nav'
import { NewContact } from './NewContact'

ReactDOM.createRoot(document.getElementById('root')).render(
   <React.StrictMode>
      <BrowserRouter>
         <Nav />

         <Routes>
            <Route path="/" element={<Contact />} />
            <Route path="/agregar" element={<NewContact />} />
            <Route path="/editar/:id" element={<NewContact />} />
         </Routes>
      </BrowserRouter>
   </React.StrictMode>,
)
