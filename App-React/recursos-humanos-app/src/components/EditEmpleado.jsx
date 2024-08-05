import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

export const EditEmpleado = () => {

    const url = 'http://localhost:8080/rh-app/empleados';

    let navegacion = useNavigate();

    const {id} = useParams();

    
    const [empleado, setEmpleado]=useState({
        nombre:"",
        departamento:"",
        sueldo:""
    })

    const{nombre, departamento, sueldo} = empleado

    useEffect(()=>{
        cargarEmpleado();
    },[])

    const cargarEmpleado = async () => {
        const resultado = await axios.get(`${url}/${id}`)
        setEmpleado(resultado.data);
    }

    const onInputChange = (e) => {
        //spread operator ... (expandir los atributos)
        setEmpleado({...empleado, [e.target.name]: e.target.value})
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`${url}/${id}`, empleado);
        // Redirigimos a la pagina de inicio
        navegacion('/');
    }
    
    return (
        <>
            <div className='container'>
                <div className='container text-center' style={{ margin: "30px" }}>
                    <h3 className='display-5 fw-bold text-center my-3'>Editar Empleado</h3>
                </div>

                <div className='container w-50'>
                    <form onSubmit={onSubmit}>
                        <div className="mb-3">
                            <label htmlFor="nombre" className="form-label">Nombre</label>
                            <input
                                type="text"
                                className="form-control"
                                id="nombre"
                                name='nombre'
                                required={true}
                                value={nombre}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="departamento"
                                className="form-label">Departamento</label>
                            <input
                                type="text"
                                className="form-control"
                                id="departamento"
                                name='departamento'
                                value={departamento}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="sueldo"
                                className="form-label">Sueldo</label>
                            <input
                                type="number"
                                step="any"
                                className="form-control"
                                id="sueldo"
                                name='sueldo'
                                value={sueldo}
                                onChange={onInputChange}
                            />
                        </div>

                        <div className='text-center'>
                            <button type="submit"
                                className="btn btn-outline-success btn-sm me-3">Modificar</button>
                            <a href='/' className='btn btn-outline-danger btn-sm me-3'>Regresar</a>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}
