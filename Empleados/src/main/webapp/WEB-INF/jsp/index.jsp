<%@ include file="comunes/cabecero.jsp"%>
<%@ include file="comunes/navegacion.jsp"%>

<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>Sistema de Empleados</h3>
    </div>

    <div class="container">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Departamento</th>
                <th scope="col">Sueldo</th>
                <th></th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="empleado" items="${empleados}">
                <tr>
                    <th scope="row">${empleado.idEmpleado}</th>
                    <td>${empleado.nombreEmpleado}</td>
                    <td>${empleado.departamento}</td>
                    <td>
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber type="currency" value="${empleado.sueldo}"/>
                    </td>
                    <td class="text-center">
                        <c:set var="urlEditar">
                            <c:url value="/editar">
                                <c:param name="idEmpleado" value="${empleado.idEmpleado}"/>
                            </c:url>
                        </c:set>
                        <a href="${urlEditar}" class="btn btn-warning btn-sm me-3">Editar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

