<%-- 
    Document   : add_material
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br><br><br>
        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <form:form action="/NJProjekatFED/goods_received_note/update_update_goods_received_note_item/${rbr}" method="post" modelAttribute="stavka">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <form:label path="kolicina" for="kolicina">Kolicina</form:label>
                            <form:input path="kolicina" name="kolicina" id = "kolicina" class="form-control"   value= "${stavka.kolicina}"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <form:label path="sifraMaterijala.sifraMaterijala">Materijal</form:label>
                            <form:select path="sifraMaterijala.sifraMaterijala" class="form-control">
                                <form:options items="${listaMaterijala}" itemLabel="nazivMaterijala" itemValue="sifraMaterijala" />
                            </form:select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <button type="sumbit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                            <a href="<c:url value='/goods_received_note/remove_update_goods_received_note_item_l/${rbr}'/>" class="btn btn-primary"><i class="fa fa-remove"></i></a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
