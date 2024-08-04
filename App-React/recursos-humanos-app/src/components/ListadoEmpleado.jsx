import axios from "axios";
import { useEffect, useState } from "react";

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

    

    const onClickEdit = (id) => {
        console.log(id);
    }

    const onClickDelete = (id) => {
        console.log(id);
    }

    return (
        <>
            <h1 className="display-5 fw-bold text-center my-5">Sistema de Empleados</h1>
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
                        {empleados.map(empleados =>
                            <tr key={empleados.idEmpleado}>
                                <th scope="row" className="text-center">{empleados.idEmpleado}</th>
                                <td>{empleados.nombre}</td>
                                <td>{empleados.departamento}</td>
                                <td>{empleados.sueldo}</td>
                                <td className="d-flex justify-content-center gap-3">
                                    <a href="#" className="btn btn-outline-warning btn-sm">
                                        Modificar
                                    </a>
                                    <a href="#" className="btn btn-outline-danger btn-sm">
                                        Eliminar
                                    </a>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </>
    )
}
