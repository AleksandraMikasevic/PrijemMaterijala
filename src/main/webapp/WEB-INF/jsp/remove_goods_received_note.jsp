<%-- 
    Document   : add_material
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">
    <br><br><br>
    <div class="row">
        <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
            <form:form action="/NJProjekatFED/goods_received_note/remove_goods_received_note/${grcn.brojPrijemnice}" method="post" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="brojPrijemnice">Broj prijemnice</form:label>
                        <form:input disabled="true" path="brojPrijemnice" class="form-control" id="brojPrijemnice" placeholder="brojPrijemnice"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="datum">Datum</form:label>
                        <form:input disabled="true" path="datum" class="form-control" id="datum" placeholder="datum"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="ukupno">Ukupno</form:label>
                    <form:input disabled="true" path="ukupno" class="form-control" id="ukupno" placeholder="ukupno"/>
                </div>
                <div class="form-group">
                    <form:label path="jmbg">Zaposleni</form:label>
                    <form:input disabled="true" path="jmbg" class="form-control" id="jmbg" placeholder="jmbg"/>
                </div>
                <div class="form-group">
                    <form:label path="brojVagarskePotvrde">Vagarska potvrda</form:label>
                    <form:input disabled="true" path="brojVagarskePotvrde" class="form-control" id="brojVagarskePotvrde" placeholder="brojVagarskePotvrde"/>
                    <br><br>
                    <table  id="stavke" class="table table-hover table-striped table-bordered table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Redni broj</th>
                                <th scope="col">Kolicina</th>
                                <th scope="col">Sifra materijala</th>
                                <th scope="col">Cena</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${grcn.stavkaprijemniceCollection}" var="item">
                                <tr>
                                    <th scope="row">${item.stavkaprijemnicePK.redniBroj}</th>
                                    <td>${item.kolicina}</td>
                                    <td>${item.sifraMaterijala.nazivMaterijala}</td>
                                    <td>${item.sifraMaterijala.cena}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                            <a href="<c:url value='/goods_received_note/all_goods_received_notes'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                        </div>
                    </div>
                </form:form>
                <br><br>
            </div>
        </div>
    </div>
</div>
