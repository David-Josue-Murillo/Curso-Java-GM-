import { Link } from "react-router-dom"

export const Contact = () => {
   return (
      <>
         <div className='container'>
            <div className='container text-center' style={{ margin: "30px" }}>
               <h3 className='display-5 fw-bold text-center my-3'>Sistema de Contactos</h3>
            </div>

            <div className='container'>
               <table className='table table-striped table-hover align-middle table-border'>
                  <thead className='table-dark text-center'>
                     <tr>
                        <th scope='col'>Id</th>
                        <th scope='col'>Nombre</th>
                        <th scope='col'>Celular</th>
                        <th scope='col'>Email</th>
                        <th scope='col'>Accion</th>
                     </tr>
                  </thead>
                  <tbody className="text-center">
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
                  </tbody>
               </table>
            </div>
         </div>
      </>
   )
}
