<%-- 
    Document   : update_material
    Created on : Aug 29, 2018, 9:04:55 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Izmena materijala</h2>
                <p>Unesite nove informacije o materijalu</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/NJProjekatFED/material/update_material/${material.sifraMaterijala}" method="post" modelAttribute="material">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="sifraMaterijala">Sifra materijala</form:label>
                        <form:input disabled="true" path="sifraMaterijala" class="form-control" id="sifraMaterijala" placeholder="sifraMaterijala" value= "${material.sifraMaterijala}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="nazivMaterijala">Naziv materijala</form:label>
                        <form:input path="nazivMaterijala" class="form-control" id="nazivMaterijala" placeholder="nazivMaterijala" value= "${material.nazivMaterijala}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="jm">Jedinica mere</form:label>
                    <form:select path="jm" class="form-control">
                        <form:options items="${jediniceMere}" />
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label path="pdv">PDV</form:label>
                    <form:input path="pdv" class="form-control" id="pdv" placeholder="pdv" value= "${material.pdv}"/>
                </div>
                <div class="form-group">
                    <form:label path="cena">Cena</form:label>
                    <form:input path="cena" class="form-control" id="cena" placeholder="cena" value= "${material.cena}"/>
                </div>
                <div class="form-group">
                    <form:label path="cena">Opis</form:label>
                    <form:input path="opis" class="form-control" id="opis" placeholder="opis" value= "${material.opis}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>                    </div>
                    <div class="form-group col-md-6">
                        <a href="/NJProjekatFED/material/all_materials" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>