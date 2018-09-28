<%-- 
    Document   : remove_material
    Created on : Aug 29, 2018, 9:04:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
    <br>
    <div class="errorblock">
        <c:if test="${not empty error}">${error}</c:if>
    </div>
    <br>
    <form:form action="/NJProjekatFED/material/remove_material/${material.sifraMaterijala}" method="post" modelAttribute="material">
        <div class="form-row">
            <div class="form-group col-md-6">
                <form:label path="sifraMaterijala">Sifra materijala</form:label>
                <form:input disabled="true" path="sifraMaterijala" class="form-control" id="sifraMaterijala" placeholder="sifraMaterijala"/>
            </div>
            <div class="form-group col-md-6">
                <form:label path="nazivMaterijala">Naziv materijala</form:label>
                <form:input disabled="true" path="nazivMaterijala" class="form-control" id="nazivMaterijala" placeholder="nazivMaterijala"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="jedinicaMere">Jedinica mere</form:label>
            <form:input disabled="true" path="jedinicaMere" class="form-control" id="jedinicaMere" placeholder="jedinicaMere"/>
        </div>
        <div class="form-group">
            <form:label path="pdv">PDV</form:label>
            <form:input disabled="true" path="pdv" class="form-control" id="pdv" placeholder="pdv"/>
        </div>
        <div class="form-group">
            <form:label path="cena">Cena</form:label>
            <form:input disabled="true" path="cena" class="form-control" id="cena" placeholder="cena"/>
        </div>
        <div class="form-group">
            <form:label path="cena">Cena</form:label>
            <form:input disabled="true" path="cena" class="form-control" id="cena" placeholder="cena"/>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                <a href="/NJProjekatFED/material/all_materials" class="btn btn-primary"><i class="fa fa-reply"></i></a>
            </div>
        </div>
    </form:form>
</div>
