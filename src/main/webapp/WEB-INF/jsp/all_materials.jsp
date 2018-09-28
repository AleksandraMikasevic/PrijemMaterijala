<%-- 
    Document   : all_materials
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.aleksandra.domen.Materijal"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br><br><br>
        <div class="row">
            <div class="col-xl-8 col-lg-8 col-md-12 col-sm-12 col-12">
                <table  id="materijali" class="table table-hover table-striped table-bordered table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Sifra materijala</th>
                            <th scope="col">Naziv materijala</th>
                            <th scope="col">Jedinica mere</th>
                            <th scope="col">PDV</th>
                            <th scope="col">Cena</th>
                            <th scope="col">#</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaMaterijala}" var="material">
                            <tr>

                                <th scope="row">${material.sifraMaterijala}</th>
                                <td>${material.nazivMaterijala}</td>
                                <td>${material.jedinicaMere}</td>
                                <td>${material.pdv}</td>
                                <td>${material.cena}</td>
                                <td>
                                    <a href="<c:url value='/material/find_material/${material.sifraMaterijala}'/>" class="btn btn-primary"><i class="fa fa-info-circle"></i></a>
                                    <a href="<c:url value='/material/update_material/${material.sifraMaterijala}'/>" class="btn btn-primary"><i class="fa fa-cogs"></i></a>
                                    <a href="<c:url value='/material/remove_material/${material.sifraMaterijala}'/>" class="btn btn-primary"><i class="fa fa-remove"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <a href="<c:url value='/material/add_material'/>" class="btn btn-primary" style="font-size:24px;"><i class="fa fa-plus-square-o"></i></a>
                <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                </div>
            </div>
        </div>
    </div>
</body>
