<%-- 
    Document   : all_materials
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br><br><br>
        <div class="row">
            <div class="col-xl-8 col-lg-8 col-md-12 col-sm-12 col-12">
                <table  id="prijemnice" class="table table-hover table-striped table-bordered table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Broj prijemnice</th>
                            <th scope="col">Datum</th>
                            <th scope="col">Ukupno</th>
                            <th scope="col">JMBG</th>
                            <th scope="col">BrojVagarskePotvrde</th>
                            <th scope="col">#</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaPrijemnica}" var="grcn">
                            <tr>

                                <th scope="row">${grcn.brojPrijemnice}</th>
                                <td>${grcn.datum}</td>
                                <td>${grcn.ukupno}</td>
                                <td>${grcn.jmbg.jmbg}</td>
                                <td>${grcn.brojVagarskePotvrde.brojVagarskePotvrde}</td>
                                <td>
                                    <a href="<c:url value='/goods_received_note/find_goods_received_note/${grcn.brojPrijemnice}'/>" class="btn btn-primary"><i class="fa fa-info-circle"></i></a>
                                    <a href="<c:url value='/goods_received_note/update_goods_received_note/${grcn.brojPrijemnice}'/>" class="btn btn-primary"><i class="fa fa-cogs"></i></a>
                                    <a href="<c:url value='/goods_received_note/remove_goods_received_note/${grcn.brojPrijemnice}'/>" class="btn btn-primary"><i class="fa fa-remove"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <a href="<c:url value='/goods_received_note/add_goods_received_note/'/>" class="btn btn-primary"style="font-size:24px;"><i class="fa fa-plus-square-o"></i></a>
                <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                </div>
            </div>
        </div>
    </div>
</body>
