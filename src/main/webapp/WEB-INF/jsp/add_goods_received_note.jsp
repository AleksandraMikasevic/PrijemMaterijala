
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container-fluid">
    <br><br><br>
    <div class="row">
        <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
            <form:form action="/NJProjekatFED/goods_received_note/add_goods_received_note_info" method="POST" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="brojPrijemnice">Broj prijemnice</form:label>
                        <form:input disabled="true" path="brojPrijemnice" class="form-control" id="brojPrijemnice" placeholder="brojPrijemnice"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="datum">Datum</form:label>
                        <form:input path="datum" class="form-control" id="datepicker" placeholder="datum"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="jmbg.jmbg">Zaposleni</form:label>
                        <form:select path="jmbg.jmbg" class="form-control">
                            <form:options items="${listaZaposlenih}" itemLabel="ime" itemValue="jmbg" />
                        </form:select>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="brojVagarskePotvrde.brojVagarskePotvrde">Vagarska potvrda</form:label>
                        <form:select path="brojVagarskePotvrde.brojVagarskePotvrde" class="form-control">
                            <form:options items="${listaVagarskihPotvrda}" itemLabel="brojVagarskePotvrde" itemValue="brojVagarskePotvrde" />
                        </form:select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                        <a href="<c:url value='/goods_received_note/all_goods_received_notes'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'});
    });
    $(document).ready(function () {
        $.getJSON("vagarska_potvrda/json", {}, function (data) {
            for (i = 0; i < data.length; i++) {
                $('#primioRadnik').append("<option value='" + data[i].radnikID + "'>" + data[i].imePrezime + "</option>");
            }
        });
    });
    $(document).ready(function () {
        $('form').bootstrapValidator({
            fields: {
                datum: {
                    validators: {
                        notEmpty: {
                            message: 'Izaberite datum prijemnice.'
                        }
                    }
                }
            }
        });
    });
</script>
