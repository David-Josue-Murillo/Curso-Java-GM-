import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Header } from "./components/Header"
import { ListadoEmpleado } from "./components/ListadoEmpleado"
import { AddEmpleado } from "./components/AddEmpleado";
import { EditEmpleado } from "./components/EditEmpleado";


const App = () => {
    return (
        <>
            <BrowserRouter>
                <Header /> 
                <Routes>
                    <Route path="/" element={<ListadoEmpleado />} />
                    <Route path="/agregar" element={<AddEmpleado />} />
                    <Route path="/editar/:id" element={<EditEmpleado />} />
                </Routes>
            </BrowserRouter>
        </>
    )
}


export default App
