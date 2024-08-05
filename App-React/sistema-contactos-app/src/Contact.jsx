import { ListContact } from "./components/ListContact"

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
                     <ListContact />
                  </tbody>
               </table>
            </div>
         </div>
      </>
   )
}
