import React, { useState } from 'react'
import { useForm } from '../hook/useForm';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export const AddEmpleado = () => {
    let navegacion = useNavigate();

    const { formState ,onInputChange, onResetForm, nombre, departamento, sueldo } = useForm({
        nombre: "",
        departamento: "",
        sueldo: 0
    });


    const onSubmit = async(e) => {
        e.preventDefault();
        const url = 'http://localhost:8080/rh-app/empleados';
        await axios.post(url, formState);
        onResetForm();
        navegacion('/');
    }

    return (
        <>
            <div className='container'>
                <div className='container text-center' style={{ margin: "30px" }}>
                    <h3 className='display-5 fw-bold text-center my-3'>Agregar Empleado</h3>
                </div>

                <div className='container w-50'>
                    <form onSubmit={onSubmit} action='/' method='post'>
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
                                className="btn btn-outline-success btn-sm me-3">Agregar</button>
                            <a href='/' className='btn btn-outline-danger btn-sm me-3'>Regresar</a>
                            
                            <button 
                                type="reset" 
                                className="btn btn-outline-warning btn-sm me-3"
                                onClick={onResetForm}
                            >Limpiar</button>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}
