import axios from "axios";
import { useEffect, useState } from "react";
import { NumericFormat } from "react-number-format";
import { Link } from "react-router-dom";

export const ListadoEmpleado = () => {

    const url = "http://localhost:8080/rh-app/empleados";

    const [empleados, setEmpleados] = useState([]);

    const cargarEmpleados = async() => {
        const response = await axios.get(url);
        setEmpleados(response.data);
    };

    useEffect(() => {
        cargarEmpleados();
    }, []);

    const onClickDelete = async(e) => {
        e.preventDefault();
        let resp = confirm('¿Estás seguro de eliminar este empleado?');
        if (!resp) return;
        onDeleteEmpledo(e);
    }

    const onDeleteEmpledo = async(e) => {
        const id = e.target.getAttribute('data-id');
        await axios.delete(`${url}/${id}`);
        cargarEmpleados();
    }

    return (
        <>
            <h2 className="display-5 fw-bold text-center my-3">Lista de Empleados</h2>
            <div className="container">
                <table className="table table-striped table-hover align-middle table-border">
                    <thead className="table-dark text-center">
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Departamento</th>
                            <th scope="col">Sueldo</th>
                            <th scope="col">Accion</th>
                        </tr>
                    </thead>
                    <tbody>
                        {empleados.map((empleados, index) =>
                            <tr key={empleados.idEmpleado} className="text-center">
                                <th scope="row">{index + 1}</th>
                                <td>{empleados.nombre}</td>
                                <td>{empleados.departamento}</td>
                                
                                <td><NumericFormat 
                                    value={empleados.sueldo} 
                                    displayType={'text'} 
                                    thousandSeparator={true} 
                                    prefix={'$'} 
                                    decimalScale={2}
                                    fixedDecimalScale={true}/>
                                </td>

                                <td className="d-flex justify-content-center gap-3">
                                    <Link 
                                        to={`/editar/${empleados.idEmpleado}`} 
                                        className="btn btn-outline-warning btn-sm">
                                        Modificar
                                    </Link>
                                    
                                    <button 
                                        onClick={onClickDelete}
                                        data-id={empleados.idEmpleado}
                                        className="btn btn-outline-danger btn-sm">
                                        Eliminar
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </>
    )
}
