<%-- 
    Document   : index
    Created on : Aug 29, 2018, 2:38:24 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
    <div class="errorblock">
        <c:if test="${not empty error}">${error}</c:if>
        </div>
        <br>
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <form:form action="login" method="post" modelAttribute="zaposleni">
                    <div class="form-group">
                        <form:label path="korisnickoIme">Korisnicko ime:</form:label>
                        <form:input path="korisnickoIme" class="form-control" id="korisnickoIme" name="korisnickoIme"/>
                    </div>
                    <div class="form-group">
                        <form:label path="lozinka">Lozinka:</form:label>
                        <form:password showPassword="true" path="lozinka" class="form-control" id="password" name="pwd"/>
                    </div>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                </form:form>
            </div>
        </div>
    </div>
</body>
