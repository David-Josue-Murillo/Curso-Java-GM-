import { Link } from "react-router-dom"

export const ListContact = () => {
    return (
        <>
            <tr>
                <th scope='row'>1</th>
                <td>Nombre</td>
                <td>Celular</td>
                <td>Email</td>
                <td className='d-flex justify-content-center gap-3'>
                    <Link
                        to='/editar/1'
                        className='btn btn-outline-warning btn-sm'>
                        Modificar
                    </Link>

                    <button
                        data-id={1}
                        className='btn btn-outline-danger btn-sm'>
                        Eliminar
                    </button>
                </td>
            </tr>
        </>
    )
}
